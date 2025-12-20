package microservice.privilegio.microservice_privilegio.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementoxCargo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer elementChargeId;
    private Integer elementId;
    private Integer chargeId;

    // Datos del elemento (cuando se lista)
    private String elementCode;
    private String module;
    private String elementName;
    private String elementCommand;
}