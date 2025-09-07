package microservice_categoriainsumo.Repository;

import microservice_categoriainsumo.Model.Categoriainsumo;

import java.util.List;

public interface CategoriainsumoRepository {

    List<Categoriainsumo> listarCategoriainsumos();
    void insertarCategoriainsumo(Categoriainsumo categoriainsumo);
    void eliminarCategoriainsumoLogico(int id);
    void modificarCategoriainsumo(Categoriainsumo categoriainsumo);


}
