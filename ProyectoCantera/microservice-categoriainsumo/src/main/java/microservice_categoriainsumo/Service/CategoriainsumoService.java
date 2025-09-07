package microservice_categoriainsumo.Service;

import microservice_categoriainsumo.Model.Categoriainsumo;

import java.util.List;

public interface CategoriainsumoService {

    /*SERVICES DE LAS CATEGORIAINSUMO*/
    List<Categoriainsumo> listarCategoriainsumos();
    void insertarCategoriainsumo(Categoriainsumo categoriaInsumo);
    void eliminarCategoriainsumoLogico(int id);
    void modificarCategoriainsumo(Categoriainsumo categoriaInsumo);

    
    
}
