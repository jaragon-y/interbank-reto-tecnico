package com.jaragon.ecommerce_api.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("pedido_items")
public class PedidoItem {

  @Id
  @Column("id")
  private Long id;
  @Column("pedido_id")
  private Long pedidoId;
  @Column("producto_id")
  private Long productoId;
  @Column("cantidad")
  private Integer cantidad;
  @Column("precio_unitario")
  private BigDecimal precioUnitario;

  public PedidoItem() {
  }

  public PedidoItem(Long id, Long pedidoId, Long productoId, Integer cantidad, BigDecimal precioUnitario) {
    this.id = id;
    this.pedidoId = pedidoId;
    this.productoId = productoId;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
  }

  // Getters y Setters

  public Long getId() {
    return id;
  }

  public Long getPedidoId() {
    return pedidoId;
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

  public void setId(Long id) {
    this.id = id;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
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
}
