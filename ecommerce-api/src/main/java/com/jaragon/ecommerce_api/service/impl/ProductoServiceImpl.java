package com.jaragon.ecommerce_api.service.impl;

import com.jaragon.ecommerce_api.model.dto.ProductoRequest;
import com.jaragon.ecommerce_api.model.dto.ProductoResponse;
import com.jaragon.ecommerce_api.model.entity.Producto;
import com.jaragon.ecommerce_api.repository.ProductoRepository;
import com.jaragon.ecommerce_api.service.ProductoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;

  public ProductoServiceImpl(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  @Override
  public Flux<ProductoResponse> listarTodos() {
    return productoRepository.findAll()
        .map(this::toResponse);
  }

  @Override
  public Mono<ProductoResponse> obtenerPorId(Long id) {
    return productoRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")))
        .map(this::toResponse);
  }

  @Override
  public Mono<ProductoResponse> crearProducto(ProductoRequest request) {
    Producto producto = new Producto();
    producto.setNombre(request.getNombre());
    producto.setPrecio(request.getPrecio());
    producto.setStock(request.getStock());

    return productoRepository.save(producto)
        .map(this::toResponse);
  }

  @Override
  public Mono<ProductoResponse> actualizarProducto(Long id, ProductoRequest request) {
    return productoRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")))
        .flatMap(producto -> {
          producto.setNombre(request.getNombre());
          producto.setPrecio(request.getPrecio());
          producto.setStock(request.getStock());
          return productoRepository.save(producto);
        })
        .map(this::toResponse);
  }

  @Override
  public Mono<Void> eliminarProducto(Long id) {
    return productoRepository.deleteById(id);
  }

  private ProductoResponse toResponse(Producto producto) {
    ProductoResponse response = new ProductoResponse();
    response.setId(producto.getId());
    response.setNombre(producto.getNombre());
    response.setPrecio(producto.getPrecio());
    response.setStock(producto.getStock());
    return response;
  }
}
