package com.microservice.mesa.microservice_mesa.Service;

import com.microservice.mesa.microservice_mesa.Model.Mesa;
import com.microservice.mesa.microservice_mesa.Repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {


    @Autowired
    private MesaRepository cargoRepository;

    @Override
    public List<Mesa> listarMesas() {
        return cargoRepository.listarMesas();
    }

    @Override
    public void insertarMesa(Mesa mesa) {
        cargoRepository.insertarMesa(mesa);
    }

    @Override
    public void eliminarMesaLogico(int id) {
        cargoRepository.eliminarMesaLogico(id);
    }

    @Override
    public void modificarMesa(Mesa mesa) {
        cargoRepository.modificarMesa(mesa);
    }

}
