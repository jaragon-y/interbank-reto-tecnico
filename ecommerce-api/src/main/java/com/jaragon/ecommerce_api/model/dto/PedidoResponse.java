package com.jaragon.ecommerce_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Información completa de un pedido")
public class PedidoResponse {

  @Schema(description = "ID único del pedido", example = "100")
  private Long id;

  @Schema(description = "Fecha de creación del pedido",
      example = "2026-02-14T15:30:00")
  private LocalDateTime fecha;

  @Schema(description = "Lista de productos incluidos en el pedido")
  private List<PedidoItemResponse> items;

  @Schema(description = "Total bruto sin descuentos", example = "5000.00")
  private BigDecimal total;

  @Schema(description = "Estado actual del pedido",
      example = "CONFIRMADO")
  private String estado;

  @Schema(description = "Descuentos", example = "500.00")
  private BigDecimal descuento;

  public PedidoResponse() {
  }

  public PedidoResponse(Long id, LocalDateTime fecha, BigDecimal total, String estado,
                        BigDecimal descuento ,List<PedidoItemResponse> items) {
    this.id = id;
    this.fecha = fecha;
    this.total = total;
    this.estado = estado;
    this.descuento = descuento;
    this.items = items;
  }

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

  public List<PedidoItemResponse> getItems() {
    return items;
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

  public void setItems(List<PedidoItemResponse> items) {
    this.items = items;
  }

  public BigDecimal getDescuento() {
    return descuento;
  }

  public void setDescuento(BigDecimal descuento) {
    this.descuento = descuento;
  }
}
