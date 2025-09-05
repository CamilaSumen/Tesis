package microservice_adicional.Service;

import microservice_adicional.Model.Adicional;

import java.util.List;

public interface AdicionalService {
    
    /*SERVICES DE LAS MESAS*/
    List<Adicional> listarAdicionals();
    void insertarAdicional(Adicional adicional);
    void eliminarAdicionalLogico(int id);
    void modificarAdicional(Adicional adicional);

}