package microservice.empleado.Repository;

import microservice.empleado.Model.Empleado;
import microservice.empleado.Repository.RowMapper.EmpleadoRowMapper;
import microservice.empleado.Repository.StoredProcedure.StoredProcedureC;
import microservice.empleado.Repository.Translator.EmpleadoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS EMPLEADOS*/

    @Override
    public List<Empleado> listarEmpleados() {
        String sql = StoredProcedureC.SEL_EMPLEADO;
        List<EmpleadoTranslator> lista = jdbcTemplate.query(sql, new EmpleadoRowMapper());
        return lista.stream()
                .map(EmpleadoTranslator::toEmpleadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarEmpleado(Empleado empleado) {
        jdbcTemplate.update(StoredProcedureC.INS_EMPLEADONUEVO,
                empleado.getFirstName(),
                empleado.getLastNameFather(),
                empleado.getLastNameMother(),
                empleado.getBirthDate(),
                empleado.getAddress(),
                empleado.getPhone(),
                empleado.getDni(),
                empleado.getEmail(),
                empleado.getChargeId(),
                empleado.getEntryDate());
    }

    @Override
    public void eliminarEmpleadoLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMAREMPLEADOLOGICO, id);
    }

    @Override
    public void modificarEmpleado(Empleado empleado) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICAREMPLEADO,
                empleado.getIdEmployer(),
                empleado.getFirstName(),
                empleado.getLastNameFather(),
                empleado.getLastNameMother(),
                empleado.getBirthDate(),
                empleado.getAddress(),
                empleado.getPhone(),
                empleado.getDni(),
                empleado.getEmail(),
                empleado.getChargeId());
    }

}
