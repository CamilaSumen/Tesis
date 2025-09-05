package microservice_delivery.Repository.RowMapper;

import microservice_delivery.Repository.Translator.DeliveryTranslator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryRowMapper  implements RowMapper<DeliveryTranslator> {

    @Override
    public DeliveryTranslator mapRow(ResultSet rs, int rowNum) throws SQLException {
        DeliveryTranslator delivery = new DeliveryTranslator();

        delivery.setDeliveryId(rs.getInt("nDeliveryId"));
        delivery.setDeliveryName(rs.getString("cNombre"));
        delivery.setDeliveryPhone(rs.getString("cTelefono"));
        delivery.setStateDelivery(rs.getBoolean("bEstado"));

        return delivery;
    }
}
