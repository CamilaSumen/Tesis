package microservice_adicional.Repository.RowMapper;

import microservice_adicional.Repository.Translator.AdicionalTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdicionalRowMapper implements RowMapper<AdicionalTranslator> {

    @Override
    public AdicionalTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        AdicionalTranslator adicional = new AdicionalTranslator();

        adicional.setAdicionalId(rs.getInt("nAdicionalId"));
        adicional.setAdicionalName(rs.getString("cNombreAdicional"));
        adicional.setAdicionalDescription(rs.getString("cDescripcionAdicional"));
        adicional.setAdicionalPrecio(rs.getDouble("nPrecio"));
        adicional.setAdicionalfechaRegistro(rs.getTime("dFechaRegistro").toLocalTime());
        adicional.setStateAdicional(rs.getBoolean("bEstado"));

        return adicional;
    }
}