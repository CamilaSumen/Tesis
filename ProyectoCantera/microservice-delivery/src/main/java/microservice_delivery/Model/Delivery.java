package microservice_delivery.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer deliveryId;
    private String deliveryName;
    private String deliveryPhone;
    private Boolean stateDelivery;
}
