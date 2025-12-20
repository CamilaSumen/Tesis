package microservice.privilegio.microservice_privilegio.Service;

import microservice.privilegio.microservice_privilegio.Model.Elemento;
import microservice.privilegio.microservice_privilegio.Model.ElementoxCargo;
import microservice.privilegio.microservice_privilegio.Repository.PrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivilegioServiceImpl implements PrivilegioService {

    @Autowired
    private PrivilegioRepository privilegioRepository;

    @Override
    public List<Elemento> listarElementos() {
        return privilegioRepository.listarElementos();
    }

    @Override
    public List<Elemento> listarElementosDisponibles(int cargoId) {
        return privilegioRepository.listarElementosDisponibles(cargoId);
    }

    @Override
    public List<ElementoxCargo> listarElementosPorCargo(int cargoId) {
        return privilegioRepository.listarElementosPorCargo(cargoId);
    }

    @Override
    public void asignarElementoACargo(int elementoId, int cargoId) {
        privilegioRepository.asignarElementoACargo(elementoId, cargoId);
    }

    @Override
    public void eliminarElementoDeCargo(int elementoxCargoId) {
        privilegioRepository.eliminarElementoDeCargo(elementoxCargoId);
    }

    @Override
    public void eliminarElementoPorElementoCargo(int elementoId, int cargoId) {
        privilegioRepository.eliminarElementoPorElementoCargo(elementoId, cargoId);
    }

    @Override
    public void guardarAsignacionesBatch(int cargoId, List<Integer> elementosIds) {
        // Convertir la lista de IDs a String separado por comas
        String elementosIdsStr = elementosIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        privilegioRepository.guardarAsignacionesBatch(cargoId, elementosIdsStr);
    }
}