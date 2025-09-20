package microservice_OrdenCompra.Service;

import microservice_OrdenCompra.Model.*;
import microservice_OrdenCompra.Repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Override
    public OrdenCompraResponse crearOrdenCompra(OrdenCompraRequest request) {
        return ordenCompraRepository.crearOrdenCompra(request);
    }

    @Override
    public Map<String, Object> recibirOrdenCompra(Integer ordenCompraId, String usuario, String observaciones) {
        return ordenCompraRepository.recibirOrdenCompra(ordenCompraId, usuario, observaciones);
    }

    @Override
    public Map<String, Object> registrarPagoOrden(PagoOrdenCompraRequest request) {
        return ordenCompraRepository.registrarPagoOrden(request);
    }

    @Override
    public List<OrdenCompra> listarOrdenesCompra(String fechaInicio, String fechaFin, Integer proveedorId, Integer estadoId) {
        return ordenCompraRepository.listarOrdenesCompra(fechaInicio, fechaFin, proveedorId, estadoId);
    }

    @Override
    public OrdenCompra obtenerDetalleOrden(Integer ordenCompraId) {
        return ordenCompraRepository.obtenerDetalleOrden(ordenCompraId);
    }

    @Override
    public List<DetalleOrdenCompra> obtenerDetallesOrden(Integer ordenCompraId) {
        return ordenCompraRepository.obtenerDetallesOrden(ordenCompraId);
    }

    @Override
    public Map<String, Object> anularOrdenCompra(Integer ordenCompraId, String usuario, String motivoAnulacion) {
        return ordenCompraRepository.anularOrdenCompra(ordenCompraId, usuario, motivoAnulacion);
    }

    @Override
    public ComprobanteCompra obtenerComprobanteCompra(Integer ordenCompraId) {
        return ordenCompraRepository.obtenerComprobanteCompra(ordenCompraId);
    }
}
