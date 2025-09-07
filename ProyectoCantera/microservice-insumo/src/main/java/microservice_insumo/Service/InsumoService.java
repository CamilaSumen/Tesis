package microservice_insumo.Service;

import microservice_insumo.Model.Insumo;

import java.util.List;

public interface InsumoService {

    /*SERVICES DE LAS INSUMOS*/
    List<Insumo> listarInsumos();
    void insertarInsumo(Insumo insumo);
    void eliminarInsumoLogico(int id);
    void modificarInsumo(Insumo insumo);

    
}
