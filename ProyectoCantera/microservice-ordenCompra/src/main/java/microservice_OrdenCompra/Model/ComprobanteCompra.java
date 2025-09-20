package microservice_OrdenCompra.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ComprobanteCompra {
    private Integer comprobanteCompraId;
    private Integer ordenCompraId;
    private Integer proveedorId;
    private String tipoComprobante;
    private String numeroComprobante;
    private LocalDateTime fechaPago;
    private String tipoPago;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal totalFinal;
    private String estado;
    private String observaciones;

    // Datos del proveedor
    private String proveedorNombre;
    private String proveedorRuc;
    private String proveedorDireccion;
    private String proveedorTelefono;

    // Detalles del comprobante
    private List<DetalleComprobanteCompra> detalles;
}
