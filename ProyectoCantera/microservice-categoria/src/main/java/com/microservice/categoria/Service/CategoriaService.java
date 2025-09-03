package com.microservice.categoria.Service;

import com.microservice.categoria.Model.Categoria;

import java.util.List;

public interface CategoriaService {

    /*SERVICES DE LAS CATEGORIAS*/
    List<Categoria> listarCategoria();
    void insertarCategoria(Categoria categoria);
    void eliminarCategoriaLogico(int id);
    void modificarCategoria(Categoria categoria);

}
