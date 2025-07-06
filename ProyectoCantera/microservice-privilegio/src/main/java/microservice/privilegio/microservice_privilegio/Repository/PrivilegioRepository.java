package microservice.privilegio.microservice_privilegio.Repository;

import microservice.privilegio.microservice_privilegio.Model.Privilegio;

import java.util.List;

public interface PrivilegioRepository {

    /*REPOSITPRY PARA LOS PRIVILEGIOS*/
    List<Privilegio> listarPrivilegios();
    void insertarPrivilegio(Privilegio privilegio);
    void eliminarPrivilegioLogico(int id);
    void modificarPrivilegio(Privilegio privilegio);


}
