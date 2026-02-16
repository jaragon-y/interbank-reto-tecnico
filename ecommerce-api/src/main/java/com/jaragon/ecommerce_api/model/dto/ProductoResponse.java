package com.jaragon.ecommerce_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Información devuelta de un producto")
public class ProductoResponse {

  @Schema(description = "ID único del producto", example = "1")
  private Long id;

  @Schema(description = "Nombre del producto", example = "Laptop Gamer")
  private String nombre;

  @Schema(description = "Precio actual del producto", example = "2500.00")
  private BigDecimal precio;

  @Schema(description = "Cantidad disponible en stock", example = "10")
  private Integer stock;

  public ProductoResponse() {
  }

  public ProductoResponse(Long id, String nombre, BigDecimal precio, Integer stock) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
  }

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
}
