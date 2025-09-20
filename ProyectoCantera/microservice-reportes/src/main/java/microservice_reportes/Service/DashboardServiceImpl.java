package microservice_reportes.Service;

import microservice_reportes.Repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microservice_reportes.Model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Override
    public DashboardData obtenerDatosDashboard(LocalDate fecha) {
        DashboardData dashboard = new DashboardData();

        try {
            // Obtener datos básicos
            dashboard.setVentasDelDia(dashboardRepository.obtenerResumenVentasDia(fecha));
            dashboard.setPedidosActivos(dashboardRepository.obtenerEstadosPedidos());
            dashboard.setStockCritico(dashboardRepository.obtenerStockCritico());
            dashboard.setProductoMasVendido(dashboardRepository.obtenerProductoMasVendido(fecha));
            dashboard.setDeliveryInfo(dashboardRepository.obtenerResumenDelivery());

            // Obtener datos semanales (últimos 7 días)
            LocalDate fechaDesde = fecha.minusDays(6);
            dashboard.setVentasSemanales(dashboardRepository.obtenerVentasSemanales(fechaDesde, fecha));

            // Obtener ventas por categoría
            dashboard.setVentasPorCategoria(dashboardRepository.obtenerVentasPorCategoria(fecha));

            // Procesar caja actual
            dashboard.setCajaActual(procesarCajaActual(fecha));

        } catch (Exception e) {
            // En caso de error, devolver dashboard con datos vacíos
            dashboard = crearDashboardVacio();
        }

        return dashboard;
    }

    private CajaActual procesarCajaActual(LocalDate fecha) {
        try {
            List<DesgloseCaja> desglose = dashboardRepository.obtenerDesgloseCaja(fecha);
            CajaActual caja = new CajaActual();

            // Mapear tipos de pago a campos específicos
            Map<String, BigDecimal> montosPorTipo = desglose.stream()
                    .collect(Collectors.toMap(
                            DesgloseCaja::getTipoPago,
                            DesgloseCaja::getMonto,
                            BigDecimal::add
                    ));

            caja.setEfectivo(montosPorTipo.getOrDefault("Efectivo", BigDecimal.ZERO));
            caja.setTarjeta(montosPorTipo.getOrDefault("Tarjeta", BigDecimal.ZERO));
            caja.setDelivery(montosPorTipo.getOrDefault("Delivery", BigDecimal.ZERO));
            caja.setYape(montosPorTipo.getOrDefault("Yape", BigDecimal.ZERO));
            caja.setPlin(montosPorTipo.getOrDefault("Plin", BigDecimal.ZERO));
            caja.setTransferencia(montosPorTipo.getOrDefault("Transferencia", BigDecimal.ZERO));

            // Calcular total
            BigDecimal total = desglose.stream()
                    .map(DesgloseCaja::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            caja.setTotal(total);

            return caja;
        } catch (Exception e) {
            return new CajaActual();
        }
    }

    private DashboardData crearDashboardVacio() {
        DashboardData dashboard = new DashboardData();
        dashboard.setVentasDelDia(new VentasDelDia());
        dashboard.setCajaActual(new CajaActual());
        dashboard.setPedidosActivos(new PedidosActivos());
        dashboard.setStockCritico(List.of());
        dashboard.setProductoMasVendido(new ProductoMasVendido());
        dashboard.setDeliveryInfo(new DeliveryInfo());
        dashboard.setVentasSemanales(List.of());
        dashboard.setVentasPorCategoria(List.of());
        return dashboard;
    }
}
