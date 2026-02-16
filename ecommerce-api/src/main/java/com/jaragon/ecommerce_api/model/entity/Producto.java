package com.jaragon.ecommerce_api.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("productos")
public class Producto {

  @Id
  @Column("id")
  private Long id;

  // Se deberia usar un UNIQUE para esta columna
  @Column("nombre")
  private String nombre;
  @Column("precio")
  private BigDecimal precio;
  @Column("stock")
  private Integer stock;

  @Version
  @Column("version")
  private Long version; // Para optimistic locking

  public Producto() {
  }

  public Producto(Long id, String nombre, BigDecimal precio, Integer stock, Long version) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
    this.version = version;
  }

  // Getters y Setters

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public Integer getStock() {
    return stock;
  }

  public Long getVersion() {
    return version;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}
