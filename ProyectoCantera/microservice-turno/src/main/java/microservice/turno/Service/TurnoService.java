package microservice.turno.Service;

import microservice.turno.Model.Turno;

import java.util.List;

public interface TurnoService {

    /*SERVICES DE LOS TURNOS*/
    List<Turno> listarTurnos();
    void insertarTurno(Turno turno);
    void eliminarTurnoLogico(int id);
    void modificarTurno(Turno turno);

}
