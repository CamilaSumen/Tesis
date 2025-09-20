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
            // Obtener datos para la fecha específica
            dashboard.setVentasDelDia(dashboardRepository.obtenerResumenVentasDia(fecha));

            // Solo obtener pedidos activos si es hoy
            if (fecha.equals(LocalDate.now())) {
                dashboard.setPedidosActivos(dashboardRepository.obtenerEstadosPedidos());
                dashboard.setDeliveryInfo(dashboardRepository.obtenerResumenDelivery());
            } else {
                // Para fechas pasadas, mostrar datos vacíos
                dashboard.setPedidosActivos(new PedidosActivos());
                dashboard.setDeliveryInfo(new DeliveryInfo());
            }

            // Stock crítico siempre se muestra (es actual)
            dashboard.setStockCritico(dashboardRepository.obtenerStockCritico());

            // Producto más vendido para la fecha seleccionada
            dashboard.setProductoMasVendido(dashboardRepository.obtenerProductoMasVendido(fecha));

            // Obtener datos semanales desde la fecha seleccionada hacia atrás
            LocalDate fechaDesde = fecha.minusDays(6);
            dashboard.setVentasSemanales(dashboardRepository.obtenerVentasSemanales(fechaDesde, fecha));

            // Obtener ventas por categoría para la fecha seleccionada
            dashboard.setVentasPorCategoria(dashboardRepository.obtenerVentasPorCategoria(fecha));

            // Procesar caja para la fecha seleccionada
            dashboard.setCajaActual(procesarCajaActual(fecha));

        } catch (Exception e) {
            // En caso de error, devolver dashboard con datos vacíos
            dashboard = crearDashboardVacio();
        }

        return dashboard;
    }


    public DashboardData obtenerDatosDashboardRango(LocalDate fechaInicio, LocalDate fechaFin) {
        DashboardData dashboard = new DashboardData();

        try {
            // Para rangos, necesitarías crear SPs adicionales que sumen datos de múltiples días
            // Por simplicidad, uso la fecha fin como referencia
            dashboard.setVentasDelDia(obtenerResumenVentasRango(fechaInicio, fechaFin));
            dashboard.setPedidosActivos(new PedidosActivos()); // No aplica para rangos históricos
            dashboard.setStockCritico(dashboardRepository.obtenerStockCritico());
            dashboard.setProductoMasVendido(obtenerProductoMasVendidoRango(fechaInicio, fechaFin));
            dashboard.setDeliveryInfo(new DeliveryInfo()); // No aplica para rangos históricos
            dashboard.setVentasSemanales(dashboardRepository.obtenerVentasSemanales(fechaInicio, fechaFin));
            dashboard.setVentasPorCategoria(obtenerVentasPorCategoriaRango(fechaInicio, fechaFin));
            dashboard.setCajaActual(procesarCajaActualRango(fechaInicio, fechaFin));

        } catch (Exception e) {
            dashboard = crearDashboardVacio();
        }

        return dashboard;
    }

    private VentasDelDia obtenerResumenVentasRango(LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementar SP que sume ventas en un rango de fechas
        // Por ahora, usar la lógica existente para un día
        return dashboardRepository.obtenerResumenVentasDia(fechaFin);
    }

    private ProductoMasVendido obtenerProductoMasVendidoRango(LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementar SP que obtenga producto más vendido en un rango
        return dashboardRepository.obtenerProductoMasVendido(fechaFin);
    }

    private List<VentasPorCategoria> obtenerVentasPorCategoriaRango(LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementar SP que sume categorías en un rango
        return dashboardRepository.obtenerVentasPorCategoria(fechaFin);
    }

    private CajaActual procesarCajaActualRango(LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementar lógica para sumar caja en un rango
        return procesarCajaActual(fechaFin);
    }

    private CajaActual procesarCajaActual(LocalDate fecha) {
        try {
            List<DesgloseCaja> desglose = dashboardRepository.obtenerDesgloseCaja(fecha);
            CajaActual caja = new CajaActual();

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