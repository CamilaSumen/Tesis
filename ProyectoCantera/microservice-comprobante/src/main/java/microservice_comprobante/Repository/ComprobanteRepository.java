package microservice_comprobante.Repository;


import microservice_comprobante.Model.Comprobante;
import microservice_comprobante.Model.ComprobanteRequest;
import microservice_comprobante.Model.ComprobanteResponse;

import java.util.List;

public interface ComprobanteRepository {
    ComprobanteResponse guardarComprobante(ComprobanteRequest request);
    Comprobante obtenerComprobante(int comprobanteId);
    List<Comprobante> listarComprobantes(String fechaInicio, String fechaFin, String mozo);
}
