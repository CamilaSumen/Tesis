package com.microservice.categoria.Service;

import com.microservice.categoria.Model.Categoria;
import com.microservice.categoria.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements  CategoriaService{

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria findById(int id) {

        return categoriaRepository.findById(id).orElseThrow(()-> new RuntimeException("Sede no disponible con ID: " + id));
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
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
