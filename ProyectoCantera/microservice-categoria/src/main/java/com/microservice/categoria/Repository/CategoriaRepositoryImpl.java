package com.microservice.categoria.Repository;

import com.microservice.categoria.Model.Categoria;
import com.microservice.categoria.Repository.RowMapper.CategoriaRowMapper;
import com.microservice.categoria.Repository.StoredProcedure.StoredProcedureC;
import com.microservice.categoria.Repository.Translator.CategoriaTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS TURNOS*/
    @Override
    public List<Categoria> listarCategoria() {
        String sql = StoredProcedureC.SEL_CATEGORIA;
        List<CategoriaTranslator> lista = jdbcTemplate.query(sql, new CategoriaRowMapper());
        return lista.stream()
                .map(CategoriaTranslator::toCategoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarCategoria(Categoria categoria) {
        jdbcTemplate.update(StoredProcedureC.INS_CATEGORIANUEVO,
                categoria.getNameCategory(),
                categoria.getDescriptionCategory(),
                categoria.getImageCategory());
    }

    @Override
    public void eliminarCategoriaLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARCATEGORIALOGICO, id);
    }

    @Override
    public void modificarCategoria(Categoria categoria) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARCATEGORIA,
                categoria.getCategoryId(),
                categoria.getNameCategory(),
                categoria.getDescriptionCategory(),
                categoria.getImageCategory());
    }
}
