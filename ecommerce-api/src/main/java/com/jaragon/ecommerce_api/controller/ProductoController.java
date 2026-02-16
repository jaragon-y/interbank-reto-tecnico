package com.jaragon.ecommerce_api.controller;

import com.jaragon.ecommerce_api.model.dto.ProductoRequest;
import com.jaragon.ecommerce_api.model.dto.ProductoResponse;
import com.jaragon.ecommerce_api.service.ProductoService;
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

@Tag(name = "Productos", description = "API para gestión de productos")
@RestController
@RequestMapping("/producto")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @Operation(summary = "Listar todos los productos")
  @GetMapping
  public Flux<ProductoResponse> listarProductos() {
    return productoService.listarTodos();
  }

  @Operation(summary = "Crear nuevo producto")
  @ApiResponse(responseCode = "201", description = "Producto creado correctamente")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ProductoResponse> crearProducto(
      @RequestBody(
          description = "Datos necesarios para crear un producto",
          required = true,
          content = @Content(
              schema = @Schema(implementation = ProductoRequest.class)
          )
      )
      @org.springframework.web.bind.annotation.RequestBody ProductoRequest request) {

    return productoService.crearProducto(request);
  }

  @Operation(summary = "Actualizar producto existente")
  @PutMapping("/{id}")
  public Mono<ProductoResponse> actualizarProducto(
      @PathVariable Long id,
      @RequestBody(
          description = "Datos para actualizar el producto",
          required = true,
          content = @Content(
              schema = @Schema(implementation = ProductoRequest.class)
          )
      )
      @org.springframework.web.bind.annotation.RequestBody ProductoRequest request) {

    return productoService.actualizarProducto(id, request);
  }
}
