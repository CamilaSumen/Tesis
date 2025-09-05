package microservice_producto.Service;

import microservice_producto.Model.Producto;

import java.util.List;

public interface ProductoService {

    /*SERVICES DE LAS PROIDUCTOS*/
    List<Producto> listarProductos();
    void insertarProducto(Producto producto);
    void eliminarProductoLogico(int id);
    void modificarProducto(Producto producto);

}
