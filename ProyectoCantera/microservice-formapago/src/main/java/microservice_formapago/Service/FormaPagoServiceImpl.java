package microservice_formapago.Service;

import microservice_formapago.Model.FormaPago;
import microservice_formapago.Repository.FormaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagoServiceImpl implements FormaPagoService {


    @Autowired
    private FormaPagoRepository formaPagoRepository;

    @Override
    public List<FormaPago> listarFormaPagos() {
        return formaPagoRepository.listarFormaPagos();
    }

    @Override
    public void insertarFormaPago(FormaPago formaPago) {
        formaPagoRepository.insertarFormaPago(formaPago);
    }

    @Override
    public void eliminarFormaPagoLogico(int id) {
        formaPagoRepository.eliminarFormaPagoLogico(id);
    }

    @Override
    public void modificarFormaPago(FormaPago formaPago) {
        formaPagoRepository.modificarFormaPago(formaPago);
    }

}
