package microservice_adicional.Repository;

import microservice_adicional.Model.Adicional;

import java.util.List;

public interface AdicionalRepository {

    List<Adicional> listarAdicionals();
    void insertarAdicional(Adicional adicional);
    void eliminarAdicionalLogico(int id);
    void modificarAdicional(Adicional adicional);

    
}
