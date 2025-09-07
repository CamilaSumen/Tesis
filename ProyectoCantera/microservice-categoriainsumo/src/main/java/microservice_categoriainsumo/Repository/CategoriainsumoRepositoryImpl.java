package microservice_categoriainsumo.Repository;

import microservice_categoriainsumo.Model.Categoriainsumo;
import microservice_categoriainsumo.Repository.RowMapper.CategoriainsumoRowMapper;
import microservice_categoriainsumo.Repository.StoredProcedure.StoredProcedureC;
import microservice_categoriainsumo.Repository.Translator.CategoriainsumoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoriainsumoRepositoryImpl implements CategoriainsumoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS CATEGORIAINSUMOS*/
    @Override
    public List<Categoriainsumo> listarCategoriainsumos() {
        String sql = StoredProcedureC.SEL_CATEGORIAINSUMO;
        List<CategoriainsumoTranslator> lista = jdbcTemplate.query(sql, new CategoriainsumoRowMapper());
        return lista.stream()
                .map(CategoriainsumoTranslator::toCategoriainsumoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarCategoriainsumo(Categoriainsumo categoriainsumo) {
        jdbcTemplate.update(StoredProcedureC.INS_CATEGORIAINSUMONUEVO,
                categoriainsumo.getInputcategoryname(),
                categoriainsumo.getInputcategorydescription());
    }

    @Override
    public void eliminarCategoriainsumoLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARCATEGORIAINSUMOLOGICO, id);
    }

    @Override
    public void modificarCategoriainsumo(Categoriainsumo categoriainsumo) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARCATEGORIAINSUMO,
                categoriainsumo.getInputcategoryId(),
                categoriainsumo.getInputcategoryname(),
                categoriainsumo.getInputcategorydescription());
    }
}
