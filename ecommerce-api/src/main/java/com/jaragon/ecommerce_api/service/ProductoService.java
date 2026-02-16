package com.jaragon.ecommerce_api.service;

import com.jaragon.ecommerce_api.model.dto.ProductoRequest;
import com.jaragon.ecommerce_api.model.dto.ProductoResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {

  Flux<ProductoResponse> listarTodos();

  Mono<ProductoResponse> obtenerPorId(Long id);

  Mono<ProductoResponse> crearProducto(ProductoRequest request);

  Mono<ProductoResponse> actualizarProducto(Long id, ProductoRequest request);

  Mono<Void> eliminarProducto(Long id);
}
