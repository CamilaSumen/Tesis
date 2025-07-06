package microservice_cargo.Repository;
import microservice_cargo.Model.*;

import java.util.List;

public interface CargoRepository {


    /*REPOSITORY PARA LOS CARGOS*/
    List<Cargo> listarCargos();
    void insertarCargo(Cargo cargo);
    void eliminarCargoLogico(int id);
    void modificarCargo(Cargo cargo);

    /*REPOSITPRY PARA LOS USUARIOS*/
    List<Usuario> listarUsuarios();
    void insertarUsuario(Usuario usuario);
    void eliminarUsuarioLogico(int id);
    void modificarUsuario(Usuario usuario);

}