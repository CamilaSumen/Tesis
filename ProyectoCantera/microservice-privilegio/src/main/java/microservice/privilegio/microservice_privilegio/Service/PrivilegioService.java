package microservice.privilegio.microservice_privilegio.Service;


import microservice.privilegio.microservice_privilegio.Model.Elemento;
import microservice.privilegio.microservice_privilegio.Model.ElementoxCargo;

import java.util.List;

public interface PrivilegioService {

    // Elementos
    List<Elemento> listarElementos();
    List<Elemento> listarElementosDisponibles(int cargoId);

    // ElementoxCargo
    List<ElementoxCargo> listarElementosPorCargo(int cargoId);
    void asignarElementoACargo(int elementoId, int cargoId);
    void eliminarElementoDeCargo(int elementoxCargoId);
    void eliminarElementoPorElementoCargo(int elementoId, int cargoId);
    void guardarAsignacionesBatch(int cargoId, List<Integer> elementosIds);
}