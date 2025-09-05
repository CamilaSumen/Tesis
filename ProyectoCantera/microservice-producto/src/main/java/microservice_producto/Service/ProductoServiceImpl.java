package microservice_producto.Service;

import microservice_producto.Model.Producto;
import microservice_producto.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements  ProductoService{


    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.listarProductos();
    }

    @Override
    public void insertarProducto(Producto producto) {
        productoRepository.insertarProducto(producto);
    }

    @Override
    public void eliminarProductoLogico(int id) {
        productoRepository.eliminarProductoLogico(id);
    }

    @Override
    public void modificarProducto(Producto producto) {
        productoRepository.modificarProducto(producto);
    }

}
