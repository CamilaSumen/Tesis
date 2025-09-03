package microservice.turno.Repository;

import microservice.turno.Model.Turno;
import microservice.turno.Repository.RowMapper.TurnoRowMapper;
import microservice.turno.Repository.StoredProcedure.StoredProcedureC;
import microservice.turno.Repository.Translator.TurnoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TurnoRepositoryImpl implements TurnoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS TURNOS*/
    @Override
    public List<Turno> listarTurnos() {
        String sql = StoredProcedureC.SEL_TURNO;
        List<TurnoTranslator> lista = jdbcTemplate.query(sql, new TurnoRowMapper());
        return lista.stream()
                .map(TurnoTranslator::toTurnoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarTurno(Turno turno) {
        jdbcTemplate.update(StoredProcedureC.INS_TURNONUEVO,
                turno.getNameshift(),
                turno.getStartTime(),
                turno.getEndTime());
    }

    @Override
    public void eliminarTurnoLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARTURNOLOGICO, id);
    }

    @Override
    public void modificarTurno(Turno turno) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARTURNO,
                turno.getShiftId(),
                turno.getNameshift(),
                turno.getStartTime(),
                turno.getEndTime());
    }
}
