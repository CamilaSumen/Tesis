package com.microservice.categoria.Service;

import com.microservice.categoria.Model.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria findById(int id);
    List<Categoria> findAll();
    void save(Categoria sede);
    void update(Categoria sede);
    void deleteById(int id);
}
