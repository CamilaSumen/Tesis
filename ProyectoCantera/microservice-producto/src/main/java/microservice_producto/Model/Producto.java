package microservice_producto.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;
    private Integer categoryId;
    private String categoryname;
    private String productName;
    private String productDescription;
    private String productimg;
    private Double productPryce;
    private LocalTime productRegisterDate;
    private Boolean stateproduct;

}