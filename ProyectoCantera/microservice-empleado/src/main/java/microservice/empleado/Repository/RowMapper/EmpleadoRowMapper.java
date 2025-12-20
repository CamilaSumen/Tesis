package microservice.empleado.Repository.RowMapper;

import microservice.empleado.Repository.Translator.EmpleadoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoRowMapper implements RowMapper<EmpleadoTranslator> {

    @Override
    public EmpleadoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmpleadoTranslator empleado = new EmpleadoTranslator();

        empleado.setIdEmployer(rs.getInt("nEmpleadoId"));
        empleado.setFirstName(rs.getString("cNombres"));
        empleado.setLastNameFather(rs.getString("cApePaterno"));
        empleado.setLastNameMother(rs.getString("cApeMaterno"));
        empleado.setDni(rs.getString("cDni"));
        empleado.setEmail(rs.getString("cCorreo"));
        empleado.setPhone(rs.getString("cTelefono"));

        // Manejo seguro de fechas nullables
        if (rs.getDate("dFechaNacimiento") != null) {
            empleado.setBirthDate(rs.getDate("dFechaNacimiento").toLocalDate());
        }

        empleado.setAddress(rs.getString("cDireccion"));

        empleado.setChargeName(rs.getString("cNombreCargo"));

        if (rs.getDate("fechaIngreso") != null) {
            empleado.setEntryDate(rs.getDate("fechaIngreso").toLocalDate());
        }

        empleado.setSalary(rs.getDouble("nSueldo"));
        empleado.setStateEmployer(rs.getBoolean("bactivo"));

        return empleado;
    }
}