package microservice_OrdenCompra.Repository;


import microservice_OrdenCompra.Model.*;

import java.util.List;
import java.util.Map;

public interface OrdenCompraRepository {
    OrdenCompraResponse crearOrdenCompra(OrdenCompraRequest request);
    Map<String, Object> recibirOrdenCompra(Integer ordenCompraId, String usuario, String observaciones);
    Map<String, Object> registrarPagoOrden(PagoOrdenCompraRequest request);
    List<OrdenCompra> listarOrdenesCompra(String fechaInicio, String fechaFin, Integer proveedorId, Integer estadoId);
    OrdenCompra obtenerDetalleOrden(Integer ordenCompraId);
    List<DetalleOrdenCompra> obtenerDetallesOrden(Integer ordenCompraId);
    Map<String, Object> anularOrdenCompra(Integer ordenCompraId, String usuario, String motivoAnulacion);
}