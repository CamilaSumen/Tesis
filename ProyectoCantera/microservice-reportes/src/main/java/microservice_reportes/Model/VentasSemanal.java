package microservice_reportes.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VentasSemanal implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private BigDecimal total;
    private Integer cantidadPedidos;
}