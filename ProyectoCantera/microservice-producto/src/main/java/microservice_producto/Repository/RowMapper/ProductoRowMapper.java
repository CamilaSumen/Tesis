package microservice_producto.Repository.RowMapper;

import microservice_producto.Model.Producto;
import microservice_producto.Repository.Translator.ProductoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoRowMapper implements RowMapper<ProductoTranslator> {

    @Override
    public ProductoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductoTranslator producto = new ProductoTranslator();

        producto.setProductId(rs.getInt("nProductoId"));
        producto.setProductName(rs.getString("cNombreProducto"));
        producto.setCategoryId(rs.getInt("nCategoriaProductoId"));
        producto.setCategoryname(rs.getString("cNombreCategoria"));
        producto.setProductDescription(rs.getString("cDescripcionProducto"));
        producto.setProductimg(rs.getString("cImagen"));
        producto.setProductPryce(rs.getDouble("nPrecio"));
        producto.setProductRegisterDate(rs.getTime("dFechaRegistro").toLocalTime());
        producto.setStateproduct(rs.getBoolean("bEstado"));


        return producto;
    }
}
