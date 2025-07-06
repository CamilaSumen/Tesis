package microservice_cargo.Service;
import microservice_cargo.Model.*;

import java.util.List;

public interface CargoService {

    /*SERVICES DE LOS CARGOS*/
    List<Cargo> listarCargos();
    void insertarCargo(Cargo cargo);
    void eliminarCargoLogico(int id);
    void modificarCargo(Cargo cargo);


    /*SERVICES DE LOS USUARIOS*/
    List<Usuario> listarUsuarios();
    void insertarUsuario(Usuario usuario);
    void eliminarUsuarioLogico(int id);
    void modificarUsuario(Usuario usuario);

}