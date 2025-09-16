package microservice_comprobante.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComprobanteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pedidoID;
    private String clienteDNI;
    private String clienteNombre;
    private String clienteApellido;
    private String tipoComprobante = "BOLETA";
    private String mesa;
    private String mozo;
    private String tipoPago;
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal propina;
    private BigDecimal totalFinal;
    private BigDecimal montoRecibido;
    private BigDecimal vuelto;
}
