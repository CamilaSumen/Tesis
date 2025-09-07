package microservice_proveedor.Service;

import microservice_proveedor.Model.Proveedor;
import microservice_proveedor.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {


    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> listarProveedors() {
        return proveedorRepository.listarProveedors();
    }

    @Override
    public void insertarProveedor(Proveedor proveedor) {
        proveedorRepository.insertarProveedor(proveedor);
    }

    @Override
    public void eliminarProveedorLogico(int id) {
        proveedorRepository.eliminarProveedorLogico(id);
    }

    @Override
    public void modificarProveedor(Proveedor proveedor) {
        proveedorRepository.modificarProveedor(proveedor);
    }
    
}
