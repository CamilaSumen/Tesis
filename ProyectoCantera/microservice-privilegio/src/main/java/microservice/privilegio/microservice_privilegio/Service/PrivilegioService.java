package microservice.privilegio.microservice_privilegio.Service;

import microservice.privilegio.microservice_privilegio.Model.Privilegio;

import java.util.List;

public interface PrivilegioService {

    List<Privilegio> listarPrivilegios();
    void insertarPrivilegio(Privilegio privilegio);
    void eliminarPrivilegioLogico(int id);
    void modificarPrivilegio(Privilegio privilegio);



}
