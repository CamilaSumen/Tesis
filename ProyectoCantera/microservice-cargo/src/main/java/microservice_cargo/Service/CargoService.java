package microservice_cargo.Service;
import microservice_cargo.Model.*;

import java.util.List;

public interface CargoService {

    List<Cargo> listarCargos();
    void insertarCargo(Cargo cargo);
    void eliminarCargoLogico(int id);
    void modificarCargo(Cargo cargo);

}