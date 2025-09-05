package microservice_formapago.Repository;

import microservice_formapago.Model.FormaPago;

import java.util.List;

public interface FormaPagoRepository {
    
    List<FormaPago> listarFormaPagos();
    void insertarFormaPago(FormaPago formaPago);
    void eliminarFormaPagoLogico(int id);
    void modificarFormaPago(FormaPago formaPago);

}
