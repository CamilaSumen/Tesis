package microservice_formapago.Repository.RowMapper;

import microservice_formapago.Repository.Translator.FormaPagoTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FormaPagoRowMapper implements RowMapper<FormaPagoTranslator> {

    @Override
    public FormaPagoTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        FormaPagoTranslator formaPago = new FormaPagoTranslator();

        formaPago.setPaymentId(rs.getInt("nFormaPagoId"));
        formaPago.setPaymentName(rs.getString("cNombreFormaPago"));
        formaPago.setPaymentImagen(rs.getString("cImagen"));
        formaPago.setStatePayment(rs.getBoolean("bEstado"));

        return formaPago;
    }
}
