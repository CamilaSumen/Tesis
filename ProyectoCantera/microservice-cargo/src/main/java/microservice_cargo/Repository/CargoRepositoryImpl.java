package microservice_cargo.Repository;

import microservice_cargo.Model.Cargo;
import microservice_cargo.Model.Empleado;
import microservice_cargo.Model.Privilegio;
import microservice_cargo.Repository.RowMapper.CargoRowMapper;
import microservice_cargo.Repository.RowMapper.EmpleadoRowMapper;
import microservice_cargo.Repository.RowMapper.PrivilegioRowMapper;
import microservice_cargo.Repository.StoredProcedure.StoredProcedureC;
import microservice_cargo.Repository.Translator.CargoTranslator;
import microservice_cargo.Repository.Translator.EmpleadoTranslator;
import microservice_cargo.Repository.Translator.PrivilegioTranslator;
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


    /*IMPLEMENTACION PARA LOS PRIVILEGIOS*/

    @Override
    public List<Privilegio> listarPrivilegios() {
        String sql = StoredProcedureC.SEL_PRIVILEGIO;
        List<PrivilegioTranslator> lista = jdbcTemplate.query(sql, new PrivilegioRowMapper());
        return lista.stream()
                .map(PrivilegioTranslator::toPrivilegioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarPrivilegio(Privilegio privilegio) {
        jdbcTemplate.update(StoredProcedureC.INS_PRIVILEGIONUEVO,
                privilegio.getPrivilegeName(),
                privilegio.getDescription(),
                privilegio.getObservation());
    }

    @Override
    public void eliminarPrivilegioLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARPRIVILEGIOLOGICO, id);
    }

    @Override
    public void modificarPrivilegio(Privilegio privilegio) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARPRIVILEGIO,
                privilegio.getPrivilegeId(),
                privilegio.getPrivilegeName(),
                privilegio.getDescription(),
                privilegio.getObservation());
    }
}