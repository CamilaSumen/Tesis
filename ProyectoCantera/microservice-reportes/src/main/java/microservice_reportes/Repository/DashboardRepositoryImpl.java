package microservice_reportes.Repository;

import microservice_reportes.Model.PedidosActivos;
import microservice_reportes.Model.VentasDelDia;
import microservice_reportes.Repository.StoredProcedure.StoredProcedureD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice_reportes.Model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public VentasDelDia obtenerResumenVentasDia(LocalDate fecha) {
        try {
            List<VentasDelDia> resultado = jdbcTemplate.query(
                    StoredProcedureD.SP_RESUMEN_VENTAS_DIA,
                    new Object[]{Date.valueOf(fecha)},
                    (rs, rowNum) -> {
                        VentasDelDia ventas = new VentasDelDia();
                        ventas.setTotalVentas(rs.getBigDecimal("TotalVentas"));
                        ventas.setCantidadPedidos(rs.getInt("CantidadPedidos"));
                        ventas.setPromedioTicket(rs.getBigDecimal("PromedioTicket"));
                        return ventas;
                    }
            );
            return resultado.isEmpty() ? new VentasDelDia() : resultado.get(0);
        } catch (Exception e) {
            return new VentasDelDia();
        }
    }

    @Override
    public PedidosActivos obtenerEstadosPedidos() {
        try {
            List<PedidosActivos> resultado = jdbcTemplate.query(
                    StoredProcedureD.SP_ESTADOS_PEDIDOS,
                    (rs, rowNum) -> {
                        PedidosActivos pedidos = new PedidosActivos();
                        pedidos.setEnCocina(rs.getInt("EnCocina"));
                        pedidos.setPendientes(rs.getInt("Pendientes"));
                        pedidos.setCompletados(rs.getInt("Completados"));
                        pedidos.setTotal(rs.getInt("Total"));
                        return pedidos;
                    }
            );
            return resultado.isEmpty() ? new PedidosActivos() : resultado.get(0);
        } catch (Exception e) {
            return new PedidosActivos();
        }
    }

    @Override
    public List<StockCritico> obtenerStockCritico() {
        return jdbcTemplate.query(
                StoredProcedureD.SP_STOCK_CRITICO,
                (rs, rowNum) -> {
                    StockCritico stock = new StockCritico();
                    stock.setInsumoId(rs.getInt("InsumoId"));
                    stock.setNombreInsumo(rs.getString("NombreInsumo"));
                    stock.setStockActual(rs.getBigDecimal("StockActual"));
                    stock.setStockMinimo(rs.getBigDecimal("StockMinimo"));
                    stock.setUnidadMedida(rs.getString("UnidadMedida"));
                    stock.setCategoria(rs.getString("Categoria"));
                    return stock;
                }
        );
    }

    @Override
    public ProductoMasVendido obtenerProductoMasVendido(LocalDate fecha) {
        try {
            List<ProductoMasVendido> resultado = jdbcTemplate.query(
                    StoredProcedureD.SP_PRODUCTO_MAS_VENDIDO,
                    new Object[]{Date.valueOf(fecha)},
                    (rs, rowNum) -> {
                        ProductoMasVendido producto = new ProductoMasVendido();
                        producto.setProductoId(rs.getInt("ProductoID"));
                        producto.setNombreProducto(rs.getString("NombreProducto"));
                        producto.setCantidadVendida(rs.getInt("CantidadVendida"));
                        producto.setTotalIngresos(rs.getBigDecimal("TotalIngresos"));
                        return producto;
                    }
            );
            return resultado.isEmpty() ? new ProductoMasVendido() : resultado.get(0);
        } catch (Exception e) {
            return new ProductoMasVendido();
        }
    }

    @Override
    public DeliveryInfo obtenerResumenDelivery() {
        try {
            List<DeliveryInfo> resultado = jdbcTemplate.query(
                    StoredProcedureD.SP_RESUMEN_DELIVERY,
                    (rs, rowNum) -> {
                        DeliveryInfo delivery = new DeliveryInfo();
                        delivery.setEnCamino(rs.getInt("EnCamino"));
                        delivery.setSinAsignar(rs.getInt("SinAsignar"));
                        delivery.setCompletados(rs.getInt("Completados"));
                        delivery.setTotal(delivery.getEnCamino() + delivery.getSinAsignar() + delivery.getCompletados());
                        return delivery;
                    }
            );
            return resultado.isEmpty() ? new DeliveryInfo() : resultado.get(0);
        } catch (Exception e) {
            return new DeliveryInfo();
        }
    }

    @Override
    public List<VentasSemanal> obtenerVentasSemanales(LocalDate fechaDesde, LocalDate fechaHasta) {
        return jdbcTemplate.query(
                StoredProcedureD.SP_VENTAS_SEMANALES,
                new Object[]{Date.valueOf(fechaDesde), Date.valueOf(fechaHasta)},
                (rs, rowNum) -> {
                    VentasSemanal venta = new VentasSemanal();
                    venta.setFecha(rs.getDate("Fecha").toLocalDate());
                    venta.setTotal(rs.getBigDecimal("Total"));
                    venta.setCantidadPedidos(rs.getInt("CantidadPedidos"));
                    return venta;
                }
        );
    }

    @Override
    public List<VentasPorCategoria> obtenerVentasPorCategoria(LocalDate fecha) {
        return jdbcTemplate.query(
                StoredProcedureD.SP_VENTAS_POR_CATEGORIA,
                new Object[]{Date.valueOf(fecha)},
                (rs, rowNum) -> {
                    VentasPorCategoria categoria = new VentasPorCategoria();
                    categoria.setCategoria(rs.getString("Categoria"));
                    categoria.setTotal(rs.getBigDecimal("Total"));
                    return categoria;
                }
        );
    }

    @Override
    public List<DesgloseCaja> obtenerDesgloseCaja(LocalDate fecha) {
        return jdbcTemplate.query(
                StoredProcedureD.SP_DESGLOSE_CAJA,
                new Object[]{Date.valueOf(fecha)},
                (rs, rowNum) -> {
                    DesgloseCaja desglose = new DesgloseCaja();
                    desglose.setTipoPago(rs.getString("TipoPago"));
                    desglose.setMonto(rs.getBigDecimal("Monto"));
                    return desglose;
                }
        );
    }
}