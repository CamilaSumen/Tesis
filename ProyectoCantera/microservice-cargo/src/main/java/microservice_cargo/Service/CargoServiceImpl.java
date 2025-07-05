package microservice_cargo.Service;

import microservice_cargo.Model.Cargo;
import microservice_cargo.Model.Empleado;
import microservice_cargo.Repository.CargoRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

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




}