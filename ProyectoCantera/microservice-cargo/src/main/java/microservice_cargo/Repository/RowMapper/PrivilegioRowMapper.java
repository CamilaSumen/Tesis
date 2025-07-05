package microservice_cargo.Repository.RowMapper;

import microservice_cargo.Repository.Translator.PrivilegioTranslator;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PrivilegioRowMapper implements RowMapper<PrivilegioTranslator> {

    @Override
    public PrivilegioTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        PrivilegioTranslator privilegio = new PrivilegioTranslator();

        privilegio.setPrivilegeId(rs.getInt("nPrivilegiosId"));
        privilegio.setPrivilegeName(rs.getString("cNombrePrivilegio"));
        privilegio.setDescription(rs.getString("cDescripcion"));
        privilegio.setObservation(rs.getString("cObservacion"));
        privilegio.setStateprivilege(rs.getBoolean("bEstado"));

        return privilegio;
    }
}
