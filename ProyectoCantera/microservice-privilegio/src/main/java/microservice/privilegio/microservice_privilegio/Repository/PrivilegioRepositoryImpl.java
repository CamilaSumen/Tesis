package microservice.privilegio.microservice_privilegio.Repository;

import microservice.privilegio.microservice_privilegio.Model.Elemento;
import microservice.privilegio.microservice_privilegio.Model.ElementoxCargo;
import microservice.privilegio.microservice_privilegio.Repository.RowMapper.ElementoRowMapper;
import microservice.privilegio.microservice_privilegio.Repository.RowMapper.ElementoxCargoRowMapper;
import microservice.privilegio.microservice_privilegio.Repository.StoredProcedure.StoredProcedureC;
import microservice.privilegio.microservice_privilegio.Repository.Translator.ElementoTranslator;
import microservice.privilegio.microservice_privilegio.Repository.Translator.ElementoxCargoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PrivilegioRepositoryImpl implements PrivilegioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Elemento> listarElementos() {
        String sql = StoredProcedureC.SEL_ELEMENTO_LISTAR;
        List<ElementoTranslator> lista = jdbcTemplate.query(sql, new ElementoRowMapper());
        return lista.stream()
                .map(ElementoTranslator::toElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Elemento> listarElementosDisponibles(int cargoId) {
        String sql = StoredProcedureC.SEL_ELEMENTO_DISPONIBLES;
        List<ElementoTranslator> lista = jdbcTemplate.query(sql, new ElementoRowMapper(), cargoId);
        return lista.stream()
                .map(ElementoTranslator::toElementoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ElementoxCargo> listarElementosPorCargo(int cargoId) {
        String sql = StoredProcedureC.SEL_ELEMENTOXCARGO_PORCARGO;
        List<ElementoxCargoTranslator> lista = jdbcTemplate.query(sql, new ElementoxCargoRowMapper(), cargoId);
        return lista.stream()
                .map(ElementoxCargoTranslator::toElementoxCargoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void asignarElementoACargo(int elementoId, int cargoId) {
        jdbcTemplate.update(StoredProcedureC.INS_ELEMENTOXCARGO, elementoId, cargoId);
    }

    @Override
    public void eliminarElementoDeCargo(int elementoxCargoId) {
        jdbcTemplate.update(StoredProcedureC.DEL_ELEMENTOXCARGO, elementoxCargoId);
    }

    @Override
    public void eliminarElementoPorElementoCargo(int elementoId, int cargoId) {
        jdbcTemplate.update(StoredProcedureC.DEL_ELEMENTOXCARGO_PORELEMENTO, elementoId, cargoId);
    }

    @Override
    public void guardarAsignacionesBatch(int cargoId, String elementosIds) {
        jdbcTemplate.update(StoredProcedureC.INS_ELEMENTOXCARGO_BATCH, cargoId, elementosIds);
    }
}