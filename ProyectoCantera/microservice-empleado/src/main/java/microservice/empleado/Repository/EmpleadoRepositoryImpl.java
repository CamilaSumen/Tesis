package microservice.empleado.Repository;

import microservice.empleado.Model.Cliente;
import microservice.empleado.Model.Empleado;
import microservice.empleado.Repository.RowMapper.ClienteRowMapper;
import microservice.empleado.Repository.RowMapper.EmpleadoRowMapper;
import microservice.empleado.Repository.StoredProcedure.StoredProcedureC;
import microservice.empleado.Repository.Translator.EmpleadoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder; // INYECTAR BCryptPasswordEncoder

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
        // ENCRIPTAR la contraseña antes de insertar
        String encryptedPassword = passwordEncoder.encode(empleado.getPassword());

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
                empleado.getEntryDate(),
                encryptedPassword);
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

    @Override
    public List<Cliente> listarClientes(Cliente cliente) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PA_Cliente_Sel_Listar")
                .declareParameters(new SqlParameter("dni", Types.VARCHAR))
                .returningResultSet("clientes", new ClienteRowMapper());

        Map<String, Object> params = new HashMap<>();
        params.put("dni", cliente.getDni());

        Map<String, Object> result = jdbcCall.execute(params);

        List<Cliente> lista = (List<Cliente>) result.get("clientes");

        return lista;
    }
}