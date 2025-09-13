package com.microservice.mesa.microservice_mesa.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pedidoID;
    private String mesa;
    private String mozo;
    private Integer numeroPersonas;
    private LocalDateTime fechaPedido;
    private String estado;
    private String observaciones;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}