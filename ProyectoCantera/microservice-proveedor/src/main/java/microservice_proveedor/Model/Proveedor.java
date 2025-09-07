package microservice_proveedor.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer supplierId;
    private String supplierName;
    private String ruc;
    private String phone;
    private String address;
    private Boolean status;

}
