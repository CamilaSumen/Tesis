package com.microservice.categoria.Repository;

import com.microservice.categoria.Model.Categoria;

import java.util.List;

public interface CategoriaRepository {

    List<Categoria> listarCategoria();
    void insertarCategoria(Categoria categoria);
    void eliminarCategoriaLogico(int id);
    void modificarCategoria(Categoria categoria);
}
