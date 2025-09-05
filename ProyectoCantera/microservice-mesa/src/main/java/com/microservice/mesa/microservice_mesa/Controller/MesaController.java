package com.microservice.mesa.microservice_mesa.Controller;

import com.microservice.mesa.microservice_mesa.Model.Mesa;
import com.microservice.mesa.microservice_mesa.Service.MesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/listar")
    public List<Mesa> listar() {
        return mesaService.listarMesas();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Mesa mesa) {
        mesaService.insertarMesa(mesa);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Mesa mesa) {
        mesaService.modificarMesa(mesa);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        mesaService.eliminarMesaLogico(id);
    }
    
}
