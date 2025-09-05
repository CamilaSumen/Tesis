package microservice_adicional.Service;

import microservice_adicional.Model.Adicional;
import microservice_adicional.Repository.AdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdicionalServiceImpl implements AdicionalService {
    
    @Autowired
    private AdicionalRepository adicionalRepository;

    @Override
    public List<Adicional> listarAdicionals() {
        return adicionalRepository.listarAdicionals();
    }

    @Override
    public void insertarAdicional(Adicional adicional) {
        adicionalRepository.insertarAdicional(adicional);
    }

    @Override
    public void eliminarAdicionalLogico(int id) {
        adicionalRepository.eliminarAdicionalLogico(id);
    }

    @Override
    public void modificarAdicional(Adicional adicional) {
        adicionalRepository.modificarAdicional(adicional);
    }
    
}
