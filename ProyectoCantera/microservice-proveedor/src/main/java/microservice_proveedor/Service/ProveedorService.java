package microservice_proveedor.Service;

import microservice_proveedor.Model.Proveedor;

import java.util.List;

public interface ProveedorService {

    List<Proveedor> listarProveedors();
    void insertarProveedor(Proveedor proveedor);
    void eliminarProveedorLogico(int id);
    void modificarProveedor(Proveedor proveedor);

}
