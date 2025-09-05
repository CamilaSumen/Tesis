package microservice_producto.Controller;

import microservice_producto.Model.Producto;
import microservice_producto.Service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.listarProductos();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Producto producto) {
        productoService.insertarProducto(producto);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Producto producto) {
        productoService.modificarProducto(producto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        productoService.eliminarProductoLogico(id);
    }

}
