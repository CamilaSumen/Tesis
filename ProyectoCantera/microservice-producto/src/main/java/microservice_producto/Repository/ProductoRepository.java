package microservice_producto.Repository;

import microservice_producto.Model.Producto;

import java.util.List;

public interface ProductoRepository {
    
    List<Producto> listarProductos();
    void insertarProducto(Producto producto);
    void eliminarProductoLogico(int id);
    void modificarProducto(Producto producto);
    
}