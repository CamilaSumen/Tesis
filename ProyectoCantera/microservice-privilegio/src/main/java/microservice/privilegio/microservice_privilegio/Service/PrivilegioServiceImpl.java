package microservice.privilegio.microservice_privilegio.Service;

import microservice.privilegio.microservice_privilegio.Model.Privilegio;
import microservice.privilegio.microservice_privilegio.Repository.PrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegioServiceImpl implements PrivilegioService {

    @Autowired
    private PrivilegioRepository privilegioRepository;


    /* IMPLEMENTACION PARA LOS PRIVILEGIOS*/
    @Override
    public List<Privilegio> listarPrivilegios() {
        return privilegioRepository.listarPrivilegios();
    }

    @Override
    public void insertarPrivilegio(Privilegio privilegio) {
        privilegioRepository.insertarPrivilegio(privilegio);
    }

    @Override
    public void eliminarPrivilegioLogico(int id) {
        privilegioRepository.eliminarPrivilegioLogico(id);
    }

    @Override
    public void modificarPrivilegio(Privilegio privilegio) {
        privilegioRepository.modificarPrivilegio(privilegio);
    }

}
