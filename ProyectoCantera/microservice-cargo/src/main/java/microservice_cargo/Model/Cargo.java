package microservice_cargo.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int charge;
    private String nameCargue;
    private String descriptioncargue;
    private boolean stateCargue;

}
