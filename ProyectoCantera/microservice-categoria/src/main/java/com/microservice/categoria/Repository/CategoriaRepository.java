package com.microservice.categoria.Repository;

import com.microservice.categoria.Model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Optional<Categoria> findById(int id);

    List<Categoria> findAll();

    void save(Categoria categoria);

    void update(Categoria categoria);

    void deleteById(int id);
}
