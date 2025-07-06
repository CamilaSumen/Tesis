package microservice_cargo.Repository;

import microservice_cargo.Model.*;
import microservice_cargo.Repository.RowMapper.*;
import microservice_cargo.Repository.StoredProcedure.StoredProcedureC;
import microservice_cargo.Repository.Translator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CargoRepositoryImpl implements CargoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS CARGOS*/
    @Override
    public List<Cargo> listarCargos() {
        String sql = StoredProcedureC.SEL_CARGO;
        List<CargoTranslator> lista = jdbcTemplate.query(sql, new CargoRowMapper());
        return lista.stream()
                .map(CargoTranslator::toCargoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarCargo(Cargo cargo) {
        jdbcTemplate.update(StoredProcedureC.INS_CARGONUEVO,
                cargo.getNameCargue(),
                cargo.getDescriptioncargue(),
                cargo.getSalary());
    }

    @Override
    public void eliminarCargoLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARCARGOLOGICO, id);
    }

    @Override
    public void modificarCargo(Cargo cargo) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARCARGO,
                cargo.getIdcharge(),
                cargo.getNameCargue(),
                cargo.getDescriptioncargue(),
                cargo.getSalary());
    }

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


    /*IMPLEMENTACION PARA LOS USUARIOS*/
    @Override
    public List<Usuario> listarUsuarios() {
        String sql = StoredProcedureC.SEL_USUARIO;
        List<UsuarioTranslator> lista = jdbcTemplate.query(sql, new UsuarioRowMapper());
        return lista.stream()
                .map(UsuarioTranslator::toUsuarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        jdbcTemplate.update(StoredProcedureC.INS_USUARIONUEVO,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPrivilegeId(),
                usuario.getShiftId(),
                usuario.getEmployeeId());
    }

    @Override
    public void eliminarUsuarioLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARTUSUARIOLOGICO, id);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARUSUARIO,
                usuario.getUserId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPrivilegeId(),
                usuario.getShiftId());
    }
}