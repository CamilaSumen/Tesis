package microservice_reportes.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardData implements Serializable {
    private static final long serialVersionUID = 1L;

    private VentasDelDia ventasDelDia;
    private CajaActual cajaActual;
    private PedidosActivos pedidosActivos;
    private List<StockCritico> stockCritico;
    private ProductoMasVendido productoMasVendido;
    private DeliveryInfo deliveryInfo;
    private List<VentasSemanal> ventasSemanales;
    private List<VentasPorCategoria> ventasPorCategoria;
}
