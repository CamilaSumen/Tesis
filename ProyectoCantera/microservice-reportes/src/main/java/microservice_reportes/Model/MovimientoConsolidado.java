package microservice_reportes.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimientoConsolidado implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoSistema;
    private LocalDateTime fechaMovimiento;
    private String usuario;
    private String descripcion;
    private String detalle;
    private BigDecimal montoAfectado;
    private String tipoOperacion;
    private String referencia;
    private Integer totalRegistros;
}