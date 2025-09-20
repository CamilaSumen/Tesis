package microservice_reportes.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimientoInventario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer movimientoId;
    private LocalDateTime fechaMovimiento;
    private String tipoMovimiento;
    private Integer insumoId;
    private String nombreInsumo;
    private String unidadMedida;
    private String categoriaInsumo;
    private BigDecimal cantidadMovimiento;
    private BigDecimal stockAnterior;
    private BigDecimal stockNuevo;
    private String observaciones;
    private Integer pedidoId;
    private String usuario;
    private String mesaPedido;
    private String tipoOperacion;
    private Integer totalRegistros;
}
