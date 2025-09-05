package microservice_formapago.Service;

import microservice_formapago.Model.FormaPago;

import java.util.List;

public interface FormaPagoService {

    /*SERVICES DE LAS FORMAPAGO*/
    List<FormaPago> listarFormaPagos();
    void insertarFormaPago(FormaPago formaPago);
    void eliminarFormaPagoLogico(int id);
    void modificarFormaPago(FormaPago formaPago);

    
}
