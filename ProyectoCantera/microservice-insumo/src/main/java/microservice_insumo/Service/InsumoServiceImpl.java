package microservice_insumo.Service;

import microservice_insumo.Model.Insumo;
import microservice_insumo.Repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoServiceImpl  implements InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    @Override
    public List<Insumo> listarInsumos() {
        return insumoRepository.listarInsumos();
    }

    @Override
    public void insertarInsumo(Insumo insumo) {
        insumoRepository.insertarInsumo(insumo);
    }

    @Override
    public void eliminarInsumoLogico(int id) {
        insumoRepository.eliminarInsumoLogico(id);
    }

    @Override
    public void modificarInsumo(Insumo insumo) {
        insumoRepository.modificarInsumo(insumo);
    }

}
