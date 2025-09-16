package microservice_comprobante.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comprobante implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer comprobanteID;
    private Integer pedidoID;
    private Integer clienteID;
    private String tipoComprobante;
    private String numeroComprobante;
    private String mesa;
    private String mozo;
    private LocalDateTime fechaPago;
    private String tipoPago;
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal propina;
    private BigDecimal totalFinal;
    private BigDecimal montoRecibido;
    private BigDecimal vuelto;
    private String estado;
    private LocalDateTime fechaCreacion;

    // Datos del cliente (para consultas con JOIN)
    private String clienteDNI;
    private String clienteNombre;
    private String clienteApellido;
    private String clienteEmail;
    private String clienteTelefono;
}