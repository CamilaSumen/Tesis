package microservice_proveedor.Repository.RowMapper;

import microservice_proveedor.Repository.Translator.ProveedorTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorRowMapper implements RowMapper<ProveedorTranslator> {

    @Override
    public ProveedorTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProveedorTranslator proveedor = new ProveedorTranslator();

        proveedor.setSupplierId(rs.getInt("nProveedorId"));
        proveedor.setSupplierName(rs.getString("nNombreProveedor"));
        proveedor.setRuc(rs.getString("nRuc"));
        proveedor.setPhone(rs.getString("nTelefono"));
        proveedor.setAddress(rs.getString("nDireccion"));
        proveedor.setStatus(rs.getBoolean("bEstado"));

        return proveedor;
    }
}
