package microservice_insumo.Repository.RowMapper;

import microservice_insumo.Repository.Translator.InsumoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsumoRowMapper implements RowMapper<InsumoTranslator> {

    @Override
    public InsumoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        InsumoTranslator insumo = new InsumoTranslator();

        insumo.setSupplyId(rs.getInt("nInsumoId"));
        insumo.setSupplyCategoryId(rs.getInt("nCategoriaInsumoId"));
        insumo.setSupplyName(rs.getString("cNombreInsumo"));
        insumo.setSupplyCategoryName(rs.getString("cNombreCategoriaInsumo"));
        insumo.setUnitOfMeasure(rs.getString("cUnidadMedida"));
        insumo.setCurrentStock(rs.getDouble("nStockActual"));
        insumo.setSupplyImg(rs.getString("cImagen"));
        insumo.setStatus(rs.getBoolean("bEstado"));

        return insumo;
    }
}