package microservice_delivery.Repository;

import microservice_delivery.Model.Delivery;
import microservice_delivery.Repository.RowMapper.DeliveryRowMapper;
import microservice_delivery.Repository.StoredProcedure.StoredProcedureC;
import microservice_delivery.Repository.Translator.DeliveryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*IMPLEMENTACION PARA LOS TURNOS*/
    @Override
    public List<Delivery> listarDeliverys() {
        String sql = StoredProcedureC.SEL_DELIVERY;
        List<DeliveryTranslator> lista = jdbcTemplate.query(sql, new DeliveryRowMapper());
        return lista.stream()
                .map(DeliveryTranslator::toDeliveryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insertarDelivery(Delivery delivery) {
        jdbcTemplate.update(StoredProcedureC.INS_DELIVERYNUEVO,
                delivery.getDeliveryName(),
                delivery.getDeliveryPhone());
    }

    @Override
    public void eliminarDeliveryLogico(int id) {
        jdbcTemplate.update(StoredProcedureC.UPD_ELIMARDELIVERYLOGICO, id);
    }

    @Override
    public void modificarDelivery(Delivery delivery) {
        jdbcTemplate.update(StoredProcedureC.UPD_MODIFICARDELIVERY,
                delivery.getDeliveryId(),
                delivery.getDeliveryName(),
                delivery.getDeliveryPhone());
    }
}