package com.microservice.categoria.Controller;

import com.microservice.categoria.Model.Categoria;
import com.microservice.categoria.Service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoriaproducto")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Categoria> getSedeById(@PathVariable int id){
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> getAllCategoria() {
        List<Categoria> categoria = categoriaService.findAll();
        return ResponseEntity.ok(categoria);
    }
}
