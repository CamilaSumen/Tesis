package microservice_cargo.Repository;
import microservice_cargo.Model.Cargo;

import java.util.List;

public interface CargoRepository {
    List<Cargo> listarCargos();
    void insertarCargo(Cargo cargo);
    void eliminarCargoLogico(int id);
    void modificarCargo(Cargo cargo);

}