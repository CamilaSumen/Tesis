package microservice_comprobante.Service;

import microservice_comprobante.Model.Comprobante;
import microservice_comprobante.Model.ComprobanteRequest;
import microservice_comprobante.Model.ComprobanteResponse;
import microservice_comprobante.Repository.ComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Override
    public ComprobanteResponse guardarComprobante(ComprobanteRequest request) {
        return comprobanteRepository.guardarComprobante(request);
    }

    @Override
    public Comprobante obtenerComprobante(int comprobanteId) {
        return comprobanteRepository.obtenerComprobante(comprobanteId);
    }

    @Override
    public List<Comprobante> listarComprobantes(String fechaInicio, String fechaFin, String mozo) {
        return comprobanteRepository.listarComprobantes(fechaInicio, fechaFin, mozo);
    }
}