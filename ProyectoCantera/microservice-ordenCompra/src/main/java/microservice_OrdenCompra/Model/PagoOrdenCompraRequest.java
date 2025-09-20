package microservice_OrdenCompra.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagoOrdenCompraRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ordenCompraId;
    private String tipoPago;
    private BigDecimal montoPagado;
    private String usuario;
    private String observaciones;
    private String tipoComprobante = "FACTURA";

    // getter y setter
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
}