package microservice_pedido.Repository.RowMapper;

import microservice_pedido.Repository.Translator.PedidoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoRowMapper implements RowMapper<PedidoTranslator> {

    @Override
    public PedidoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        PedidoTranslator pedido = new PedidoTranslator();

        pedido.setPedidoID(rs.getInt("PedidoID"));
        pedido.setMesa(rs.getString("Mesa"));
        pedido.setMozo(rs.getString("Mozo"));
        pedido.setNumeroPersonas(rs.getInt("NumeroPersonas"));
        pedido.setFechaPedido(rs.getTimestamp("FechaPedido") != null ?
                rs.getTimestamp("FechaPedido").toLocalDateTime() : null);
        pedido.setEstado(rs.getString("Estado"));
        pedido.setObservaciones(rs.getString("Observaciones"));
        pedido.setTotal(rs.getBigDecimal("Total"));
        pedido.setFechaCreacion(rs.getTimestamp("FechaCreacion") != null ?
                rs.getTimestamp("FechaCreacion").toLocalDateTime() : null);
        pedido.setFechaActualizacion(rs.getTimestamp("FechaActualizacion") != null ?
                rs.getTimestamp("FechaActualizacion").toLocalDateTime() : null);

        return pedido;
    }
}
