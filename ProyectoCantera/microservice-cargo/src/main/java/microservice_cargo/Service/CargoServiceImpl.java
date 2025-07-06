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