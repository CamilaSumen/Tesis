package microservice_reportes.Repository;

import microservice_reportes.Model.*;

import java.time.LocalDate;
import java.util.List;

public interface DashboardRepository {
    VentasDelDia obtenerResumenVentasDia(LocalDate fecha);
    PedidosActivos obtenerEstadosPedidos();
    List<StockCritico> obtenerStockCritico();
    ProductoMasVendido obtenerProductoMasVendido(LocalDate fecha);
    DeliveryInfo obtenerResumenDelivery();
    List<VentasSemanal> obtenerVentasSemanales(LocalDate fechaDesde, LocalDate fechaHasta);
    List<VentasPorCategoria> obtenerVentasPorCategoria(LocalDate fecha);
    List<DesgloseCaja> obtenerDesgloseCaja(LocalDate fecha);
}
