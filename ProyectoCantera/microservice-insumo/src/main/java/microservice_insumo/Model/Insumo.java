package microservice_insumo.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Insumo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer supplyId;
    private Integer supplyCategoryId;
    private String supplyCategoryName;
    private String supplyName;
    private String unitOfMeasure;
    private Double currentStock;
    private String supplyImg;
    private Boolean status;
}
