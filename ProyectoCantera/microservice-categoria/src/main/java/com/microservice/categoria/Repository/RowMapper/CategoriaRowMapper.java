package com.microservice.categoria.Repository.RowMapper;

import com.microservice.categoria.Model.Categoria;
import com.microservice.categoria.Repository.Translator.CategoriaTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaRowMapper implements RowMapper<CategoriaTranslator> {


    @Override
    public CategoriaTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoriaTranslator categoria = new CategoriaTranslator();

        categoria.setCategoryId(rs.getInt("nCategoriaProductoId"));
        categoria.setNameCategory(rs.getString("cNombreCategoria"));
        categoria.setDescriptionCategory(rs.getString("cDescripcion"));
        categoria.setImageCategory(rs.getString("bImagen"));
        categoria.setStateCategory(rs.getBoolean("bEstado"));

        return categoria;
    }
}
