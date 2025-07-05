package microservice_cargo.Repository.RowMapper;
import microservice_cargo.Repository.Translator.PrivilegioTranslator;

import microservice_cargo.Repository.Translator.TurnoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TurnoRowMapper implements RowMapper<TurnoTranslator> {

    @Override
    public TurnoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        TurnoTranslator turno = new TurnoTranslator();

        turno.setShiftId(rs.getInt("nTurnoId"));
        turno.setNameshift(rs.getString("cNombre"));
        turno.setStartTime(rs.getTime("tHoraInicio").toLocalTime());
        turno.setEndTime(rs.getTime("tHoraFin").toLocalTime());
        turno.setStateshift(rs.getBoolean("bEstado"));

        return turno;
    }
}
