package microservice.turno.Repository;

import microservice.turno.Model.Turno;
import java.util.List;

public interface TurnoRepository {

    List<Turno> listarTurnos();
    void insertarTurno(Turno turno);
    void eliminarTurnoLogico(int id);
    void modificarTurno(Turno turno);

}
