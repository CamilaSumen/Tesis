package microservice_pedido.ModelDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mesa;
    private String mozo;
    private Integer numeroPersonas;
    private String observaciones;
    private List<DetalleProductoDTO> detalleJSON;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetalleProductoDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer ProductoID;
        private String NombreProducto;
        private Integer Cantidad;
        private BigDecimal PrecioUnitario;
    }
}