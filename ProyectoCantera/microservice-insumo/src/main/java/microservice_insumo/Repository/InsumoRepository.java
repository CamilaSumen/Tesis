package microservice_insumo.Repository;

import microservice_insumo.Model.Insumo;

import java.util.List;

public interface InsumoRepository {

    List<Insumo> listarInsumos();
    void insertarInsumo(Insumo insumo);
    void eliminarInsumoLogico(int id);
    void modificarInsumo(Insumo insumo);

    
}
