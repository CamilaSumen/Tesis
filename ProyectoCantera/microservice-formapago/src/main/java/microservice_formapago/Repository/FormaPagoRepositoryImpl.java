package microservice_formapago.Repository;

import microservice_formapago.Model.FormaPago;
import microservice_formapago.Repository.RowMapper.FormaPagoRowMapper;
import microservice_formapago.Repository.StoredProcedure.StoredProcedureC;
import microservice_formapago.Repository.Translator.FormaPagoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FormaPagoRepositoryImpl implements FormaPagoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS TURNOS*/
    @Override
    public List<FormaPago> listarFormaPagos() {
        String sql = StoredProcedureC.SEL_FORMAPAGO;
        List<FormaPagoTranslator> lista = jdbcTemplate.query(sql, new FormaPagoRowMapper());
        return lista.stream()
                .map(FormaPagoTranslator::toFormaPagoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarFormaPago(FormaPago formaPago) {
        jdbcTemplate.update(StoredProcedureC.INS_FORMAPAGONUEVO,
                formaPago.getPaymentName(),
                formaPago.getPaymentImagen());
    }

    @Override
    public void eliminarFormaPagoLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARFORMAPAGOLOGICO, id);
    }

    @Override
    public void modificarFormaPago(FormaPago formaPago) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARFORMAPAGO,
                formaPago.getPaymentId(),
                formaPago.getPaymentName(),
                formaPago.getPaymentImagen());
    }
}

