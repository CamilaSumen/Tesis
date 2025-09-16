package microservice_comprobante.Repository;


import microservice_comprobante.Model.Comprobante;
import microservice_comprobante.Model.ComprobanteRequest;
import microservice_comprobante.Model.ComprobanteResponse;
import microservice_comprobante.Repository.StoredProcedure.StoredProcedureC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class ComprobanteRepositoryImpl implements ComprobanteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public ComprobanteResponse guardarComprobante(ComprobanteRequest request) {
        List<ComprobanteResponse> resultado = jdbcTemplate.query(
                StoredProcedureC.SP_GUARDAR_COMPROBANTE,
                new Object[]{
                        request.getPedidoID(),
                        request.getClienteDNI(),
                        request.getClienteNombre(),
                        request.getClienteApellido(),
                        request.getTipoComprobante(),
                        request.getMesa(),
                        request.getMozo(),
                        request.getTipoPago(),
                        request.getSubtotal(),
                        request.getDescuento(),
                        request.getPropina(),
                        request.getTotalFinal(),
                        request.getMontoRecibido(),
                        request.getVuelto()
                },
                (rs, rowNum) -> {
                    ComprobanteResponse response = new ComprobanteResponse();

                    // Debug: imprimir nombres de columnas
                    try {
                        int columnCount = rs.getMetaData().getColumnCount();
                        System.out.println("Número de columnas: " + columnCount);

                        for (int i = 1; i <= columnCount; i++) {
                            String columnName = rs.getMetaData().getColumnName(i);
                            System.out.println("Columna " + i + ": " + columnName);
                        }


                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    return response;
                }
        );

        return resultado.isEmpty() ? null : resultado.get(0);
    }
    @Override
    public Comprobante obtenerComprobante(int comprobanteId) {
        List<Comprobante> resultado = jdbcTemplate.query(
                StoredProcedureC.SP_OBTENER_COMPROBANTE,
                new Object[]{comprobanteId},
                (rs, rowNum) -> {
                    Comprobante comprobante = new Comprobante();

                    comprobante.setComprobanteID(rs.getInt("ComprobanteID"));
                    comprobante.setPedidoID(rs.getInt("PedidoID"));
                    comprobante.setTipoComprobante(rs.getString("TipoComprobante"));
                    comprobante.setNumeroComprobante(rs.getString("NumeroComprobante"));
                    comprobante.setMesa(rs.getString("Mesa"));
                    comprobante.setMozo(rs.getString("Mozo"));
                    comprobante.setFechaPago(rs.getTimestamp("FechaPago").toLocalDateTime());
                    comprobante.setTipoPago(rs.getString("TipoPago"));
                    comprobante.setSubtotal(rs.getBigDecimal("Subtotal"));
                    comprobante.setDescuento(rs.getBigDecimal("Descuento"));
                    comprobante.setPropina(rs.getBigDecimal("Propina"));
                    comprobante.setTotalFinal(rs.getBigDecimal("TotalFinal"));
                    comprobante.setMontoRecibido(rs.getBigDecimal("MontoRecibido"));
                    comprobante.setVuelto(rs.getBigDecimal("Vuelto"));
                    comprobante.setEstado(rs.getString("Estado"));

                    // Datos del cliente
                    comprobante.setClienteDNI(rs.getString("ClienteDNI"));
                    comprobante.setClienteNombre(rs.getString("ClienteNombre"));
                    comprobante.setClienteApellido(rs.getString("ClienteApellido"));
                    comprobante.setClienteEmail(rs.getString("ClienteEmail"));
                    comprobante.setClienteTelefono(rs.getString("ClienteTelefono"));

                    return comprobante;
                }
        );

        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public List<Comprobante> listarComprobantes(String fechaInicio, String fechaFin, String mozo) {
        return jdbcTemplate.query(
                StoredProcedureC.SP_LISTAR_COMPROBANTES,
                new Object[]{fechaInicio, fechaFin, mozo},
                (rs, rowNum) -> {
                    Comprobante comprobante = new Comprobante();

                    comprobante.setComprobanteID(rs.getInt("ComprobanteID"));
                    comprobante.setPedidoID(rs.getInt("PedidoID"));
                    comprobante.setNumeroComprobante(rs.getString("NumeroComprobante"));
                    comprobante.setTipoComprobante(rs.getString("TipoComprobante"));
                    comprobante.setMesa(rs.getString("Mesa"));
                    comprobante.setMozo(rs.getString("Mozo"));
                    comprobante.setFechaPago(rs.getTimestamp("FechaPago").toLocalDateTime());
                    comprobante.setTipoPago(rs.getString("TipoPago"));
                    comprobante.setTotalFinal(rs.getBigDecimal("TotalFinal"));
                    comprobante.setEstado(rs.getString("Estado"));
                    comprobante.setClienteNombre(rs.getString("NombreCliente"));

                    return comprobante;
                }
        );
    }
}