package com.jaragon.ecommerce_api.service.impl;

import com.jaragon.ecommerce_api.model.dto.PedidoItemRequest;
import com.jaragon.ecommerce_api.model.dto.PedidoItemResponse;
import com.jaragon.ecommerce_api.model.dto.PedidoRequest;
import com.jaragon.ecommerce_api.model.dto.PedidoResponse;
import com.jaragon.ecommerce_api.model.entity.Pedido;
import com.jaragon.ecommerce_api.model.entity.PedidoItem;
import com.jaragon.ecommerce_api.model.entity.Producto;
import com.jaragon.ecommerce_api.repository.PedidoItemRepository;
import com.jaragon.ecommerce_api.repository.PedidoRepository;
import com.jaragon.ecommerce_api.repository.ProductoRepository;
import com.jaragon.ecommerce_api.service.PedidoService;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PedidoServiceImpl implements PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ProductoRepository productoRepository;
  private final PedidoItemRepository pedidoItemRepository;

  public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository,
                           PedidoItemRepository pedidoItemRepository) {
    this.pedidoRepository = pedidoRepository;
    this.productoRepository = productoRepository;
    this.pedidoItemRepository = pedidoItemRepository;
  }

  @Override
  public Flux<PedidoResponse> listarTodos() {

    return pedidoRepository.findAll()
        .flatMap(this::buildPedidoResponse);
  }

  @Override
  public Mono<PedidoResponse> obtenerPorId(Long id) {

    return pedidoRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
        .flatMap(this::buildPedidoResponse);
  }

  @Override
  public Mono<PedidoResponse> crearPedido(PedidoRequest request) {

    return crearPedidoBase()
        .flatMap(pedido ->
            guardarItems(request, pedido)
              .thenReturn(pedido)
        )
        .flatMap(this::buildPedidoResponse);
  }

  @Override
  public Mono<PedidoResponse> confirmarPedido(Long id) {

    return pedidoRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
        .flatMap(this::validarEstadoCreado)
        .flatMap(this::procesarConfirmacion)
        .flatMap(this::buildPedidoResponse);
  }

  private Mono<PedidoResponse> buildPedidoResponse(Pedido pedido) {

    return pedidoItemRepository.findByPedidoId(pedido.getId())
        .map(this::toItemResponse)
        .collectList()
        .map(itemsResponse -> toResponse(pedido, itemsResponse));
  }
  private PedidoItemResponse toItemResponse(PedidoItem item) {

    return new PedidoItemResponse(
        item.getProductoId(),
        item.getCantidad(),
        item.getPrecioUnitario(),
        item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad()))
    );
  }
  private PedidoResponse toResponse(Pedido pedido, List<PedidoItemResponse> items) {

    return new PedidoResponse(
        pedido.getId(),
        pedido.getFecha(),
        pedido.getTotal(),
        pedido.getEstado(),
        pedido.getDescuento(),
        items
    );
  }

  private Mono<Pedido> crearPedidoBase() {

    Pedido pedido = new Pedido();
    pedido.setFecha(LocalDateTime.now());
    pedido.setEstado("CREADO");

    return pedidoRepository.save(pedido);
  }

  private Mono<List<PedidoItem>> guardarItems(PedidoRequest request, Pedido pedido) {

    return Flux.fromIterable(request.getItems())
        .flatMap(itemRequest ->
            productoRepository.findById(itemRequest.getProductoId())
                .switchIfEmpty(Mono.error(
                    new RuntimeException("Producto no encontrado")))
                .flatMap(producto -> guardarItem(pedido, producto, itemRequest))
        )
        .collectList();
  }

  private Mono<PedidoItem> guardarItem(
      Pedido pedido,
      Producto producto,
      PedidoItemRequest itemRequest) {

    PedidoItem item = new PedidoItem();
    item.setPedidoId(pedido.getId());
    item.setProductoId(producto.getId());
    item.setCantidad(itemRequest.getCantidad());
    item.setPrecioUnitario(producto.getPrecio());

    return pedidoItemRepository.save(item);
  }

  private Mono<Pedido> actualizarTotales(
      Pedido pedido,
      List<PedidoItem> items) {

    BigDecimal total = calcularTotal(items);
    BigDecimal descuento = calcularDescuento(total, items.size());
    BigDecimal totalFinal = total.subtract(descuento);

    pedido.setTotal(totalFinal);
    pedido.setDescuento(descuento);

    return pedidoRepository.save(pedido);
  }

  private BigDecimal calcularTotal(List<PedidoItem> items) {

    return items.stream()
        .map(item -> BigDecimal
            .valueOf(item.getCantidad())
            .multiply(item.getPrecioUnitario()))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calcularDescuento(BigDecimal total, int cantidadProductos) {

    BigDecimal descuento = BigDecimal.ZERO;

    if (total.compareTo(new BigDecimal("1000")) > 0) {
      descuento = descuento.add(total.multiply(new BigDecimal("0.10")));
    }

    if (cantidadProductos > 5) {
      descuento = descuento.add(total.multiply(new BigDecimal("0.05")));
    }

    return descuento.setScale(2, RoundingMode.HALF_UP);
  }

  private Mono<Pedido> validarEstadoCreado(Pedido pedido) {

    if (!"CREADO".equals(pedido.getEstado())) {
      return Mono.error(new RuntimeException("El pedido no puede confirmarse"));
    }

    return Mono.just(pedido);
  }

  private Mono<Pedido> procesarConfirmacion(Pedido pedido) {

    return pedidoItemRepository.findByPedidoId(pedido.getId())
        .collectList()
        .flatMap(items ->
            actualizarTotales(pedido,items)
                .then(validarYDescontarStock(items)
                  .then(confirmarPedidoEstado(pedido)))
        );
  }

  private Mono<Void> validarYDescontarStock(List<PedidoItem> items) {

    return Flux.fromIterable(items)
        .flatMap(item ->
            productoRepository.findById(item.getProductoId())
                .switchIfEmpty(Mono.error(
                    new RuntimeException("Producto no encontrado")))
                .flatMap(producto -> {

                  if (producto.getStock() < item.getCantidad()) {
                    return Mono.error(
                        new RuntimeException("Stock insuficiente"));
                  }

                  producto.setStock(
                      producto.getStock() - item.getCantidad());

                  return productoRepository.save(producto);
                })
        )
        .then();
  }

  private Mono<Pedido> confirmarPedidoEstado(Pedido pedido) {

    pedido.setEstado("CONFIRMADO");
    return pedidoRepository.save(pedido);
  }

}
