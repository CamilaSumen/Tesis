package microservice_reportes.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstadisticasMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;

    // Inventario
    private Integer totalMovimientosInventario;
    private Integer totalEntradasInventario;
    private Integer totalSalidasInventario;

    // Caja
    private Integer totalMovimientosCaja;
    private BigDecimal totalIngresosCaja;
    private BigDecimal totalEgresosCaja;
    private Integer totalVentas;

    // Balance
    private BigDecimal balanceNeto;
}