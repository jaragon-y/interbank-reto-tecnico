package com.jaragon.ecommerce_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Detalle de un producto dentro del pedido")
public class PedidoItemResponse {

  @Schema(description = "ID del producto", example = "1")
  private Long productoId;

  @Schema(description = "Cantidad solicitada", example = "2")
  private Integer cantidad;

  @Schema(description = "Precio unitario del producto", example = "2500.00")
  private BigDecimal precioUnitario;

  @Schema(description = "Subtotal del item (precio * cantidad)", example = "5000.00")
  private BigDecimal subtotal;

  public PedidoItemResponse() {
  }

  public PedidoItemResponse(Long productoId, Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
    this.productoId = productoId;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
  }

  public Long getProductoId() {
    return productoId;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public BigDecimal getPrecioUnitario() {
    return precioUnitario;
  }

  public BigDecimal getSubtotal() {
    return subtotal;
  }

  public void setProductoId(Long productoId) {
    this.productoId = productoId;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public void setPrecioUnitario(BigDecimal precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }
}
