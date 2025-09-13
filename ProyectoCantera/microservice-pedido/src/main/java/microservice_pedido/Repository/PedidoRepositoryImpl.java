package microservice_pedido.Repository;


import microservice_pedido.ModelDTO.PedidoActualizarRequest;
import microservice_pedido.ModelDTO.PedidoRequest;
import microservice_pedido.Repository.RowMapper.PedidoRowMapper;
import microservice_pedido.Repository.StoredProcedure.StoredProcedureC;
import microservice_pedido.Repository.Translator.PedidoTranslator;
import microservice_pedido.Response.PedidoResponse;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.mesa.microservice_mesa.Model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;



    @Override
    public List<Pedido> listarPedidos() {
        String sql = StoredProcedureC.SEL_PEDIDO;
        List<PedidoTranslator> lista = jdbcTemplate.query(sql, new PedidoRowMapper());
        return lista.stream()
                .map(PedidoTranslator::toPedidoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponse guardarPedido(PedidoRequest pedidoRequest) {
        try {
            String detalleJSON = objectMapper.writeValueAsString(pedidoRequest.getDetalleJSON());

            System.out.println("JSON generado: " + detalleJSON);

            List<PedidoResponse> resultado = jdbcTemplate.query(StoredProcedureC.SP_GUARDAR_PEDIDO,
                    new Object[]{
                            pedidoRequest.getMesa(),
                            pedidoRequest.getMozo(),
                            pedidoRequest.getNumeroPersonas(),
                            pedidoRequest.getObservaciones(),
                            detalleJSON
                    },
                    (rs, rowNum) -> {
                        PedidoResponse response = new PedidoResponse();

                        try {
                            if (rs.findColumn("ErrorNumber") > 0) {
                                response.setMensaje("Error: " + rs.getString("ErrorMessage"));
                                return response;
                            }
                        } catch (SQLException ignored) {
                        }

                        response.setPedidoID(rs.getInt("PedidoID"));
                        response.setTotal(rs.getBigDecimal("Total"));
                        response.setMensaje(rs.getString("Mensaje"));

                        return response;
                    });

            return resultado.isEmpty() ? null : resultado.get(0);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON del pedido", e);
        }
    }


    @Override
    public void modificarEstadoPedido(int pedidoId, String estado) {
        jdbcTemplate.update(StoredProcedureC.UPD_ESTADO_PEDIDO,
                pedidoId,
                estado);
    }

    @Override
    public Pedido obtenerPedidoPorId(int pedidoId) {
        List<PedidoTranslator> lista = jdbcTemplate.query(StoredProcedureC.SEL_PEDIDO_BY_ID,
                new PedidoRowMapper(), pedidoId);
        return lista.stream()
                .map(PedidoTranslator::toPedidoDTO)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Map<String, Object>> obtenerPedidoParaEditar(int pedidoId) {
        String sql = StoredProcedureC.SP_OBTENER_PEDIDO_PARA_EDITAR;

        return jdbcTemplate.query(sql, new Object[]{pedidoId}, (rs, rowNum) -> {
            Map<String, Object> row = new HashMap<>();

            // Datos del pedido
            row.put("PedidoID", rs.getInt("PedidoID"));
            row.put("Mesa", rs.getString("Mesa"));
            row.put("Mozo", rs.getString("Mozo"));
            row.put("NumeroPersonas", rs.getInt("NumeroPersonas"));
            row.put("FechaPedido", rs.getTimestamp("FechaPedido"));
            row.put("Estado", rs.getString("Estado"));
            row.put("Observaciones", rs.getString("Observaciones"));
            row.put("Total", rs.getBigDecimal("Total"));

            // Datos del detalle
            row.put("DetalleID", rs.getInt("DetalleID"));
            row.put("productoID", rs.getInt("productoID"));
            row.put("nombreProducto", rs.getString("nombreProducto"));
            row.put("cantidad", rs.getInt("cantidad"));
            row.put("precioUnitario", rs.getBigDecimal("precioUnitario"));
            row.put("subtotal", rs.getBigDecimal("subtotal"));

            return row;
        });
    }

    @Override
    public PedidoResponse actualizarPedido(PedidoActualizarRequest request) {
        try {
            String detalleJSON = objectMapper.writeValueAsString(request.getDetalleJSON());

            List<PedidoResponse> resultado = jdbcTemplate.query(StoredProcedureC.SP_ACTUALIZAR_PEDIDO,
                    new Object[]{
                            request.getPedidoID(),
                            request.getMesa(),
                            request.getMozo(),
                            request.getNumeroPersonas(),
                            request.getObservaciones(),
                            detalleJSON
                    },
                    (rs, rowNum) -> {
                        PedidoResponse response = new PedidoResponse();

                        try {
                            if (rs.findColumn("ErrorNumber") > 0) {
                                response.setMensaje("Error: " + rs.getString("ErrorMessage"));
                                return response;
                            }
                        } catch (SQLException ignored) {
                        }

                        // Respuesta exitosa
                        response.setPedidoID(rs.getInt("PedidoID"));
                        response.setTotal(rs.getBigDecimal("Total"));
                        response.setMensaje(rs.getString("Mensaje"));

                        return response;
                    });

            return resultado.isEmpty() ? null : resultado.get(0);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar JSON del pedido", e);
        }
    }
}