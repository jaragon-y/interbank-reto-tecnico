package com.jaragon.ecommerce_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Datos necesarios para crear o actualizar un producto")
public class ProductoRequest {

  @Schema(description = "Nombre del producto", example = "Laptop Gamer")
  private String nombre;

  @Schema(description = "Precio del producto", example = "2500.00")
  private BigDecimal precio;

  @Schema(description = "Cantidad disponible en stock", example = "10")
  private Integer stock;

  public ProductoRequest() {
  }

  public ProductoRequest(String nombre, BigDecimal precio, Integer stock) {
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
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
