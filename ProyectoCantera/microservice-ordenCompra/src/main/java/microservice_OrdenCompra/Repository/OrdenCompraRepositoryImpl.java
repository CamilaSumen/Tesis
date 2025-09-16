package microservice_OrdenCompra.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservice_OrdenCompra.Model.*;
import microservice_OrdenCompra.Repository.StoredProcedure.StoredProcedureC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrdenCompraRepositoryImpl implements OrdenCompraRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OrdenCompraResponse crearOrdenCompra(OrdenCompraRequest request) {
        try {
            // Convertir detalles a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String detalleJson = objectMapper.writeValueAsString(request.getDetalles());

            List<OrdenCompraResponse> resultado = jdbcTemplate.query(
                    StoredProcedureC.SP_CREAR_ORDEN_COMPRA,
                    new Object[]{
                            request.getProveedorId(),
                            request.getUsuario(),
                            request.getObservaciones(),
                            detalleJson
                    },
                    (rs, rowNum) -> {
                        OrdenCompraResponse response = new OrdenCompraResponse();
                        response.setOrdenCompraId(rs.getInt("OrdenCompraId"));
                        response.setTotal(rs.getBigDecimal("Total"));
                        response.setMensaje(rs.getString("Mensaje"));
                        response.setSuccess(rs.getBoolean("Success"));
                        return response;
                    }
            );

            return resultado.isEmpty() ?
                    createErrorResponse("Error al crear orden de compra") :
                    resultado.get(0);

        } catch (Exception e) {
            return createErrorResponse("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> recibirOrdenCompra(Integer ordenCompraId, String usuario, String observaciones) {
        List<Map<String, Object>> resultado = jdbcTemplate.query(
                StoredProcedureC.SP_RECIBIR_ORDEN_COMPRA,
                new Object[]{ordenCompraId, usuario, observaciones},
                (rs, rowNum) -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", rs.getBoolean("Success"));
                    response.put("mensaje", rs.getString("Mensaje"));
                    return response;
                }
        );

        return resultado.isEmpty() ?
                Map.of("success", false, "mensaje", "Error al recibir orden de compra") :
                resultado.get(0);
    }

    @Override
    public Map<String, Object> registrarPagoOrden(PagoOrdenCompraRequest request) {
        List<Map<String, Object>> resultado = jdbcTemplate.query(
                StoredProcedureC.SP_REGISTRAR_PAGO_ORDEN,
                new Object[]{
                        request.getOrdenCompraId(),
                        request.getTipoPago(),
                        request.getMontoPagado(),
                        request.getUsuario(),
                        request.getObservaciones()
                },
                (rs, rowNum) -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", rs.getBoolean("Success"));
                    response.put("mensaje", rs.getString("Mensaje"));
                    return response;
                }
        );

        return resultado.isEmpty() ?
                Map.of("success", false, "mensaje", "Error al registrar pago") :
                resultado.get(0);
    }

    @Override
    public List<OrdenCompra> listarOrdenesCompra(String fechaInicio, String fechaFin,
                                                 Integer proveedorId, Integer estadoId) {
        return jdbcTemplate.query(
                StoredProcedureC.SP_LISTAR_ORDENES_COMPRA,
                new Object[]{fechaInicio, fechaFin, proveedorId, estadoId},
                (rs, rowNum) -> {
                    OrdenCompra orden = new OrdenCompra();
                    orden.setOrdenCompraId(rs.getInt("OrdenCompraId"));
                    orden.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                    orden.setProveedorNombre(rs.getString("ProveedorNombre"));
                    orden.setProveedorRuc(rs.getString("ProveedorRuc"));
                    orden.setEstado(rs.getString("Estado"));
                    orden.setTotal(rs.getBigDecimal("Total"));
                    orden.setTotalItems(rs.getInt("TotalItems"));
                    return orden;
                }
        );
    }

    @Override
    public OrdenCompra obtenerDetalleOrden(Integer ordenCompraId) {
        List<OrdenCompra> resultado = jdbcTemplate.query(
                StoredProcedureC.SP_OBTENER_DETALLE_ORDEN,
                new Object[]{ordenCompraId},
                (rs, rowNum) -> {
                    OrdenCompra orden = new OrdenCompra();
                    orden.setOrdenCompraId(rs.getInt("OrdenCompraId"));
                    orden.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                    orden.setTotal(rs.getBigDecimal("Total"));
                    orden.setProveedorId(rs.getInt("ProveedorId"));
                    orden.setProveedorNombre(rs.getString("ProveedorNombre"));
                    orden.setProveedorRuc(rs.getString("ProveedorRuc"));
                    orden.setProveedorTelefono(rs.getString("ProveedorTelefono"));
                    orden.setProveedorDireccion(rs.getString("ProveedorDireccion"));
                    orden.setEstadoId(rs.getInt("EstadoId"));
                    orden.setEstado(rs.getString("Estado"));
                    return orden;
                }
        );

        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public List<DetalleOrdenCompra> obtenerDetallesOrden(Integer ordenCompraId) {
        // Usar una consulta SQL directa en lugar del SP problemático
        String sql = "SELECT " +
                "doc.nDetalleOrdenesCompraId as DetalleId, " +
                "doc.nInsumoId as InsumoId, " +
                "i.cNombreInsumo as NombreInsumo, " +
                "i.cUnidadMedida as UnidadMedida, " +
                "ci.cNombre as Categoria, " +
                "doc.nCantidad as Cantidad, " +
                "doc.nPrecioUnitario as PrecioUnitario, " +
                "(doc.nCantidad * doc.nPrecioUnitario) as Subtotal " +
                "FROM DetalleOrdenesCompra doc " +
                "INNER JOIN Insumo i ON doc.nInsumoId = i.nInsumoId " +
                "INNER JOIN CategoriaInsumo ci ON i.nCategoriaInsumoId = ci.nCategoriaInsumoId " +
                "WHERE doc.nOrdenesCompraId = ? " +
                "ORDER BY ci.cNombre, i.cNombreInsumo";

        return jdbcTemplate.query(sql,
                new Object[]{ordenCompraId},
                (rs, rowNum) -> {
                    DetalleOrdenCompra detalle = new DetalleOrdenCompra();
                    detalle.setDetalleId(rs.getInt("DetalleId"));
                    detalle.setInsumoId(rs.getInt("InsumoId"));
                    detalle.setNombreInsumo(rs.getString("NombreInsumo"));
                    detalle.setUnidadMedida(rs.getString("UnidadMedida"));
                    detalle.setCategoria(rs.getString("Categoria"));
                    detalle.setCantidad(rs.getBigDecimal("Cantidad"));
                    detalle.setPrecioUnitario(rs.getBigDecimal("PrecioUnitario"));
                    detalle.setSubtotal(rs.getBigDecimal("Subtotal"));
                    return detalle;
                }
        );
    }
    @Override
    public Map<String, Object> anularOrdenCompra(Integer ordenCompraId, String usuario, String motivoAnulacion) {
        List<Map<String, Object>> resultado = jdbcTemplate.query(
                StoredProcedureC.SP_ANULAR_ORDEN_COMPRA,
                new Object[]{ordenCompraId, usuario, motivoAnulacion},
                (rs, rowNum) -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", rs.getBoolean("Success"));
                    response.put("mensaje", rs.getString("Mensaje"));
                    return response;
                }
        );

        return resultado.isEmpty() ?
                Map.of("success", false, "mensaje", "Error al anular orden de compra") :
                resultado.get(0);
    }

    private OrdenCompraResponse createErrorResponse(String mensaje) {
        OrdenCompraResponse response = new OrdenCompraResponse();
        response.setSuccess(false);
        response.setMensaje(mensaje);
        return response;
    }
}