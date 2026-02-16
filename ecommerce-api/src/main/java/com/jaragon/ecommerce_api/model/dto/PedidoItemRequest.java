package com.jaragon.ecommerce_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Producto incluido en el pedido")
public class PedidoItemRequest {

  @Schema(description = "ID del producto", example = "1")
  private Long productoId;

  @Schema(description = "Cantidad solicitada", example = "2")
  private Integer cantidad;

  public PedidoItemRequest() {
  }

  public PedidoItemRequest(Long productoId, Integer cantidad) {
    this.productoId = productoId;
    this.cantidad = cantidad;
  }

  public Long getProductoId() {
    return productoId;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setProductoId(Long productoId) {
    this.productoId = productoId;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }
}
