package microservice_proveedor.Repository;

import microservice_proveedor.Model.Proveedor;
import microservice_proveedor.Repository.RowMapper.ProveedorRowMapper;
import microservice_proveedor.Repository.StoredProcedure.StoredProcedureC;
import microservice_proveedor.Repository.Translator.ProveedorTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProveedorRepositoryImpl  implements ProveedorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS PROVEEDORS*/
    @Override
    public List<Proveedor> listarProveedors() {
        String sql = StoredProcedureC.SEL_PROVEEDOR;
        List<ProveedorTranslator> lista = jdbcTemplate.query(sql, new ProveedorRowMapper());
        return lista.stream()
                .map(ProveedorTranslator::toProveedorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarProveedor(Proveedor proveedor) {
        jdbcTemplate.update(StoredProcedureC.INS_PROVEEDORNUEVO,
                proveedor.getSupplierName(),
                proveedor.getRuc(),
                proveedor.getPhone(),
                proveedor.getAddress());
    }


    @Override
    public void eliminarProveedorLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARPROVEEDORLOGICO, id);
    }

    @Override
    public void modificarProveedor(Proveedor proveedor) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARPROVEEDOR,
                proveedor.getSupplierId(),
                proveedor.getSupplierName(),
                proveedor.getRuc(),
                proveedor.getPhone(),
                proveedor.getAddress());
    }
}