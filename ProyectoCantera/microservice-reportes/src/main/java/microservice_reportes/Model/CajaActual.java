package microservice_reportes.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CajaActual implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal efectivo;
    private BigDecimal tarjeta;
    private BigDecimal delivery;
    private BigDecimal yape;
    private BigDecimal plin;
    private BigDecimal transferencia;
    private BigDecimal total;
}