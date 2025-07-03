package com.microservice.categoria.Repository.RowMapper;

import com.microservice.categoria.Model.Categoria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaRowMapper implements RowMapper<Categoria> {
    @Override
    public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
       Categoria categoria = new Categoria();
       categoria.setNCategoriaProductoId(rs.getInt(1));
       categoria.setCDescripcion(rs.getString(2));
       categoria.setBEstado(rs.getBoolean(3));
        return categoria;
    }
}
