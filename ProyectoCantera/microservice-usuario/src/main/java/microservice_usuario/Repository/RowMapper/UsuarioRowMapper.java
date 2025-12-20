package microservice_usuario.Repository.RowMapper;

import microservice_usuario.Repository.Translator.UsuarioTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<UsuarioTranslator> {

    @Override
    public UsuarioTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuarioTranslator usuario = new UsuarioTranslator();

        //usuario.setUserId(rs.getInt("nUsuarioId"));
        usuario.setUsername(rs.getString("cCodUsuario"));
        usuario.setPassword(rs.getString("cPassword"));
        usuario.setCargoCod(rs.getString("cCargoCod"));
        usuario.setCargoId(rs.getInt("nCargoId"));

/*
        usuario.setPrivilegeId(rs.getInt("nPrivilegioId"));
        usuario.setPrivilegeName(rs.getString("cNombrePrivilegio"));

        usuario.setShiftId(rs.getInt("nTurnoId"));
        usuario.setShiftName(rs.getString("nombreTurno"));

        usuario.setEmployeeId(rs.getInt("nEmpleadoId"));
        usuario.setFirstName(rs.getString("cNombres"));
        usuario.setLastNameFather(rs.getString("cApePaterno"));
        usuario.setLastNameMother(rs.getString("cApeMaterno"));

        usuario.setDni(rs.getString("cDni"));
        usuario.setEmail(rs.getString("cCorreo"));
        usuario.setPhone(rs.getString("cTelefono"));

        usuario.setStatus(rs.getBoolean("nEstado"));
*/
        return usuario;
    }
}