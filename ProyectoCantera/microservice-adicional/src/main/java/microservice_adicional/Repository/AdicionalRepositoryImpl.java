package microservice_adicional.Repository;

import microservice_adicional.Model.Adicional;
import microservice_adicional.Repository.RowMapper.AdicionalRowMapper;
import microservice_adicional.Repository.StoredProcedure.StoredProcedureC;
import microservice_adicional.Repository.Translator.AdicionalTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdicionalRepositoryImpl implements AdicionalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS ADICIONAL*/
    @Override
    public List<Adicional> listarAdicionals() {
        String sql = StoredProcedureC.SEL_ADICIONAL;
        List<AdicionalTranslator> lista = jdbcTemplate.query(sql, new AdicionalRowMapper());
        return lista.stream()
                .map(AdicionalTranslator::toAdicionalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarAdicional(Adicional adicional) {
        jdbcTemplate.update(StoredProcedureC.INS_ADICIONALNUEVO,
                adicional.getAdicionalName(),
                adicional.getAdicionalDescription(),
                adicional.getAdicionalPrecio());
    }

    @Override
    public void eliminarAdicionalLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARADICIONALLOGICO, id);
    }

    @Override
    public void modificarAdicional(Adicional adicional) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARADICIONAL,
                adicional.getAdicionalId(),
                adicional.getAdicionalName(),
                adicional.getAdicionalDescription(),
                adicional.getAdicionalPrecio());
    }
}
