package com.jaragon.ecommerce_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Pedido con lista de productos")
public class PedidoRequest {

  @Schema(description = "Lista de productos incluidos en el pedido")
  private List<PedidoItemRequest> items;

  public PedidoRequest() {
  }

  public PedidoRequest(List<PedidoItemRequest> items) {
    this.items = items;
  }

  public List<PedidoItemRequest> getItems() {
    return items;
  }

  public void setItems(List<PedidoItemRequest> items) {
    this.items = items;
  }
}
