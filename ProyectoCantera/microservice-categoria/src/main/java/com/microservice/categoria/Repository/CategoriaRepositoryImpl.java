package com.microservice.categoria.Repository;

import com.microservice.categoria.Model.Categoria;
import com.microservice.categoria.Repository.RowMapper.CategoriaRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepositoryImpl implements  CategoriaRepository{

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Categoria> rowMapperCategoria = new CategoriaRowMapper();

    public CategoriaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Categoria> findById(int id){
        String sql = StoredProceduresC.SEL_CATEGORIA_ID;
        return jdbcTemplate.query(sql, rowMapperCategoria, id).stream().findFirst();
    }

    @Override
    public List<Categoria> findAll() {
        String sql = StoredProceduresC.SEL_CATEGORIA;
        return jdbcTemplate.query(sql, rowMapperCategoria);
    }

    @Override
    public void save(Categoria categoria) {

    }

    @Override
    public void update(Categoria categoria) {

    }

    @Override
    public void deleteById(int id) {

    }
}
