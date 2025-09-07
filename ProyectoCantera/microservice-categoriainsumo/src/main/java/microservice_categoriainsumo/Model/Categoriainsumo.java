package microservice_categoriainsumo.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoriainsumo {

    private static final long serialVersionUID = 1L;

    private Integer inputcategoryId;
    private String inputcategoryname;
    private String inputcategorydescription;
    private Boolean stateinputcategory;
}
