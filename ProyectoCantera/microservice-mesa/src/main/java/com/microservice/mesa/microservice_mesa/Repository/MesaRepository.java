package com.microservice.mesa.microservice_mesa.Repository;

import com.microservice.mesa.microservice_mesa.Model.Mesa;

import java.util.List;

public interface MesaRepository {

    List<Mesa> listarMesas();
    void insertarMesa(Mesa mesa);
    void eliminarMesaLogico(int id);
    void modificarMesa(Mesa mesa);
    void ocupadaMesa(Mesa mesa);
    void desocupadaMesa(Mesa mesa);

    
}
