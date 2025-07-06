package microservice_cargo.Service;

import microservice_cargo.Model.*;
import microservice_cargo.Repository.CargoRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    /* IMPLEMENTACION PARA LOS CARGOS*/
    @Override
    public List<Cargo> listarCargos() {
        return cargoRepository.listarCargos();
    }

    @Override
    public void insertarCargo(Cargo cargo) {
        cargoRepository.insertarCargo(cargo);
    }

    @Override
    public void eliminarCargoLogico(int id) {
        cargoRepository.eliminarCargoLogico(id);
    }

    @Override
    public void modificarCargo(Cargo cargo) {
        cargoRepository.modificarCargo(cargo);
    }

    /* IMPLEMENTACION PARA LOS EMPLEADOS*/
    @Override
    public List<Empleado> listarEmpleados() {
        return cargoRepository.listarEmpleados();
    }

    @Override
    public void insertarEmpleado(Empleado empleado) {
        cargoRepository.insertarEmpleado(empleado);
    }

    @Override
    public void eliminarEmpleadoLogico(int id) {
        cargoRepository.eliminarEmpleadoLogico(id);
    }

    @Override
    public void modificarEmpleado(Empleado empleado) {
        cargoRepository.modificarEmpleado(empleado);
    }

    /* IMPLEMENTACION PARA LOS PRIVILEGIOS*/
    @Override
    public List<Privilegio> listarPrivilegios() {
        return cargoRepository.listarPrivilegios();
    }

    @Override
    public void insertarPrivilegio(Privilegio privilegio) {
        cargoRepository.insertarPrivilegio(privilegio);
    }

    @Override
    public void eliminarPrivilegioLogico(int id) {
        cargoRepository.eliminarPrivilegioLogico(id);
    }

    @Override
    public void modificarPrivilegio(Privilegio privilegio) {
        cargoRepository.modificarPrivilegio(privilegio);
    }

    /* IMPLEMENTACION PARA LOS TURNOS*/
    @Override
    public List<Turno> listarTurnos() {
        return cargoRepository.listarTurnos();
    }

    @Override
    public void insertarTurno(Turno turno) {
        cargoRepository.insertarTurno(turno);
    }

    @Override
    public void eliminarTurnoLogico(int id) {
        cargoRepository.eliminarTurnoLogico(id);
    }

    @Override
    public void modificarTurno(Turno turno) {
        cargoRepository.modificarTurno(turno);
    }


    /* IMPLEMENTACION PARA LOS TURNOS*/
    @Override
    public List<Usuario> listarUsuarios() {
        return cargoRepository.listarUsuarios();
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        cargoRepository.insertarUsuario(usuario);
    }

    @Override
    public void eliminarUsuarioLogico(int id) {
        cargoRepository.eliminarUsuarioLogico(id);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        cargoRepository.modificarUsuario(usuario);
    }

}