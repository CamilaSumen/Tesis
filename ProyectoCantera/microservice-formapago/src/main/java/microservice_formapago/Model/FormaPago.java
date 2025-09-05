package microservice_formapago.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormaPago implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer paymentId;
    private String paymentName;
    private String paymentImagen;
    private Boolean statePayment;
}
