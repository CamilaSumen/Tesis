package microservice_reportes.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer enCamino;
    private Integer sinAsignar;
    private Integer completados;
    private Integer total;
}