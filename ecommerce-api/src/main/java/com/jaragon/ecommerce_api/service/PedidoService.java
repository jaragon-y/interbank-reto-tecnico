package com.jaragon.ecommerce_api.service;

import com.jaragon.ecommerce_api.model.dto.PedidoRequest;
import com.jaragon.ecommerce_api.model.dto.PedidoResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PedidoService {

  Flux<PedidoResponse> listarTodos();

  Mono<PedidoResponse> obtenerPorId(Long id);

  Mono<PedidoResponse> crearPedido(PedidoRequest request);

  Mono<PedidoResponse> confirmarPedido(Long id);
}
