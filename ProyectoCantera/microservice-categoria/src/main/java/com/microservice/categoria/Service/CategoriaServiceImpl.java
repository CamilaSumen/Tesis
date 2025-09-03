package com.microservice.categoria.Service;

import com.microservice.categoria.Model.Categoria;
import com.microservice.categoria.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements  CategoriaService{


    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategoria() {
        return categoriaRepository.listarCategoria();
    }

    @Override
    public void insertarCategoria(Categoria categoria) {
        categoriaRepository.insertarCategoria(categoria);
    }

    @Override
    public void eliminarCategoriaLogico(int id) {
        categoriaRepository.eliminarCategoriaLogico(id);
    }

    @Override
    public void modificarCategoria(Categoria categoria) {
        categoriaRepository.modificarCategoria(categoria);
    }


}
