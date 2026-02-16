package com.jaragon.ecommerce_api.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("pedidos")
public class Pedido {

  @Id
  @Column("id")
  private Long id;

  @Column("fecha")
  private LocalDateTime fecha;
  @Column("total")
  private BigDecimal total;
  @Column("estado")
  private String estado;
  @Column("descuento")
  private BigDecimal descuento;

  public Pedido() {
  }

  public Pedido(Long id, LocalDateTime fecha, BigDecimal total, String estado, Integer cantidadProductos) {
    this.id = id;
    this.fecha = fecha;
    this.total = total;
    this.estado = estado;
  }

  // Getters y Setters

  public Long getId() {
    return id;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public String getEstado() {
    return estado;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public BigDecimal getDescuento() {
    return descuento;
  }

  public void setDescuento(BigDecimal descuento) {
    this.descuento = descuento;
  }
}
