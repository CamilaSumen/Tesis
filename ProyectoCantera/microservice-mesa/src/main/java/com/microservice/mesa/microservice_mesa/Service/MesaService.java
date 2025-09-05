package com.microservice.mesa.microservice_mesa.Service;

import com.microservice.mesa.microservice_mesa.Model.Mesa;

import java.util.List;

public interface MesaService {

    /*SERVICES DE LAS MESAS*/
    List<Mesa> listarMesas();
    void insertarMesa(Mesa mesa);
    void eliminarMesaLogico(int id);
    void modificarMesa(Mesa mesa);
    
}
