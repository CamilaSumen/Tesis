package microservice_insumo.Repository;

import microservice_insumo.Model.Insumo;
import microservice_insumo.Repository.RowMapper.InsumoRowMapper;
import microservice_insumo.Repository.StoredProcedure.StoredProcedureC;
import microservice_insumo.Repository.Translator.InsumoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InsumoRepositoryImpl implements InsumoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS INSUMOS*/
    @Override
    public List<Insumo> listarInsumos() {
        String sql = StoredProcedureC.SEL_INSUMO;
        List<InsumoTranslator> lista = jdbcTemplate.query(sql, new InsumoRowMapper());
        return lista.stream()
                .map(InsumoTranslator::toInsumoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarInsumo(Insumo insumo) {
        jdbcTemplate.update(StoredProcedureC.INS_INSUMONUEVO,
                insumo.getSupplyCategoryId(),
                insumo.getSupplyName(),
                insumo.getUnitOfMeasure(),
                insumo.getCurrentStock(),
                insumo.getSupplyImg());
    }

    @Override
    public void eliminarInsumoLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARINSUMOLOGICO, id);
    }

    @Override
    public void modificarInsumo(Insumo insumo) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARINSUMO,
                insumo.getSupplyId(),
                insumo.getSupplyCategoryId(),
                insumo.getSupplyName(),
                insumo.getUnitOfMeasure(),
                insumo.getCurrentStock(),
                insumo.getSupplyImg());
    }
}
