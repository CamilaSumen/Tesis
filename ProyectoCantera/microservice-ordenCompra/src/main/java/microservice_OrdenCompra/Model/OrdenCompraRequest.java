package microservice_OrdenCompra.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenCompraRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer proveedorId;
    private String usuario;
    private String observaciones;
    private List<DetalleOrdenCompraRequest> detalles;
}
