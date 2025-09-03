package microservice.turno.Service;

import microservice.turno.Model.Turno;
import microservice.turno.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository cargoRepository;

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

}
