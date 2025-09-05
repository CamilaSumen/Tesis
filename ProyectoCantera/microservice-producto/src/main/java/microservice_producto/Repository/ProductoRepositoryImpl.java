package microservice_producto.Repository;

import microservice_producto.Model.Producto;
import microservice_producto.Repository.RowMapper.ProductoRowMapper;
import microservice_producto.Repository.StoredProcedure.StoredProcedureC;
import microservice_producto.Repository.Translator.ProductoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS PRODUCTOS*/
    @Override
    public List<Producto> listarProductos() {
        String sql = StoredProcedureC.SEL_PRODUCTO;
        List<ProductoTranslator> lista = jdbcTemplate.query(sql, new ProductoRowMapper());
        return lista.stream()
                .map(ProductoTranslator::toProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarProducto(Producto producto) {
        jdbcTemplate.update(StoredProcedureC.INS_PRODUCTONUEVO,
                producto.getCategoryId(),
                producto.getProductName(),
                producto.getProductDescription(),
                producto.getProductimg(),
                producto.getProductPryce());
    }

    @Override
    public void eliminarProductoLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARPRODUCTOLOGICO, id);
    }

    @Override
    public void modificarProducto(Producto producto) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARPRODUCTO,
                producto.getProductId(),
                producto.getCategoryId(),
                producto.getProductName(),
                producto.getProductDescription(),
                producto.getProductimg(),
                producto.getProductPryce());
    }
}
