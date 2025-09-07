package microservice_categoriainsumo.Repository.RowMapper;

import microservice_categoriainsumo.Repository.Translator.CategoriainsumoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriainsumoRowMapper implements RowMapper<CategoriainsumoTranslator> {

    @Override
    public CategoriainsumoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoriainsumoTranslator categoriainsumo = new CategoriainsumoTranslator();

        categoriainsumo.setInputcategoryId(rs.getInt("nCategoriaInsumoId"));
        categoriainsumo.setInputcategoryname(rs.getString("cNombre"));
        categoriainsumo.setInputcategorydescription(rs.getString("cDescripcion"));
        categoriainsumo.setStateinputcategory(rs.getBoolean("bEstado"));

        return categoriainsumo;
    }
}
