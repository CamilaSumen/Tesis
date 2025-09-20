package microservice_OrdenCompra.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ordenCompraId;
    private Integer proveedorId;
    private Integer estadoId;
    private LocalDateTime fecha;
    private BigDecimal total;
    private Boolean pagado;

    // Datos del proveedor (para consultas con JOIN)
    private String proveedorNombre;
    private String proveedorRuc;
    private String proveedorTelefono;
    private String proveedorDireccion;

    // Estado
    private String estado;

    // Para listados
    private Integer totalItems;
}