package com.jaragon.ecommerce_api.controller;

import com.jaragon.ecommerce_api.model.dto.PedidoRequest;
import com.jaragon.ecommerce_api.model.dto.PedidoResponse;
import com.jaragon.ecommerce_api.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Pedidos", description = "API para gestión de pedidos")
@RestController
@RequestMapping("/pedido")
public class PedidoController {

  private final PedidoService pedidoService;

  public PedidoController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @Operation(summary = "Listar todos los pedidos")
  @GetMapping
  public Flux<PedidoResponse> listarPedidos() {
    return pedidoService.listarTodos();
  }

  @Operation(summary = "Crear nuevo pedido")
  @ApiResponse(responseCode = "201", description = "Pedido creado correctamente")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<PedidoResponse> crearPedido(
      @RequestBody(
          description = "Lista de productos que formarán el pedido",
          required = true,
          content = @Content(
              schema = @Schema(implementation = PedidoRequest.class)
          )
      )
      @org.springframework.web.bind.annotation.RequestBody PedidoRequest request) {

    return pedidoService.crearPedido(request);
  }

  @Operation(summary = "Confirmar pedido y aplicar descuentos")
  @PostMapping("/{id}/confirmar")
  public Mono<PedidoResponse> confirmarPedido(@PathVariable Long id) {
    return pedidoService.confirmarPedido(id);
  }
}
