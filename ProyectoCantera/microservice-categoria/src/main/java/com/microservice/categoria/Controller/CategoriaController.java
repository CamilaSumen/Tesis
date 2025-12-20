package com.microservice.categoria.Controller;

import com.microservice.categoria.Model.Categoria;
import com.microservice.categoria.Service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoriaproducto")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping("/listar")
    public List<Categoria> listar() {
        return categoriaService.listarCategoria();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Categoria categoria) {
        categoriaService.insertarCategoria(categoria);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Categoria categoria) {
        categoriaService.modificarCategoria(categoria);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        categoriaService.eliminarCategoriaLogico(id);
    }
}
