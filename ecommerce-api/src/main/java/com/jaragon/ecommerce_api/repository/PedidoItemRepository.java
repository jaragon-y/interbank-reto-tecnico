package com.jaragon.ecommerce_api.repository;

import com.jaragon.ecommerce_api.model.entity.PedidoItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PedidoItemRepository extends ReactiveCrudRepository<PedidoItem, Long> {

  Flux<PedidoItem> findByPedidoId(Long pedidoId);
}
