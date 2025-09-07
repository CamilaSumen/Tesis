package microservice_categoriainsumo.Service;

import microservice_categoriainsumo.Model.Categoriainsumo;
import microservice_categoriainsumo.Repository.CategoriainsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriainsumoServiceImpl implements CategoriainsumoService {


    @Autowired
    private CategoriainsumoRepository categoriainsumoRepository;

    @Override
    public List<Categoriainsumo> listarCategoriainsumos() {
        return categoriainsumoRepository.listarCategoriainsumos();
    }

    @Override
    public void insertarCategoriainsumo(Categoriainsumo categoriainsumo) {
        categoriainsumoRepository.insertarCategoriainsumo(categoriainsumo);
    }

    @Override
    public void eliminarCategoriainsumoLogico(int id) {
        categoriainsumoRepository.eliminarCategoriainsumoLogico(id);
    }

    @Override
    public void modificarCategoriainsumo(Categoriainsumo categoriainsumo) {
        categoriainsumoRepository.modificarCategoriainsumo(categoriainsumo);
    }

}

