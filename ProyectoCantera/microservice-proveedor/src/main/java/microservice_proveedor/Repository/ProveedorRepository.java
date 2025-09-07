package microservice_proveedor.Repository;

import microservice_proveedor.Model.Proveedor;

import java.util.List;

public interface ProveedorRepository {
   
    List<Proveedor> listarProveedors();
    void insertarProveedor(Proveedor proveedor);
    void eliminarProveedorLogico(int id);
    void modificarProveedor(Proveedor proveedor);


}
