package microservice_reportes.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimientoCaja implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer movimientoId;
    private LocalDateTime fechaMovimiento;
    private Integer sesionId;
    private String usuarioSesion;
    private String tipoMovimiento;
    private Boolean esIngreso;
    private String descripcion;
    private BigDecimal monto;
    private String tipoPago;
    private Integer pedidoId;
    private Integer comprobanteId;
    private String usuario;
    private String observaciones;
    private String mesaPedido;
    private String numeroComprobante;
    private String tipoOperacion;
    private Integer totalRegistros;
}