package microservice_pedido.ModelDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoActualizarRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pedidoID;
    private String mesa;
    private String mozo;
    private Integer numeroPersonas;
    private String observaciones;
    private List<PedidoRequest.DetalleProductoDTO> detalleJSON;
}
