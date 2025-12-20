package microservice.privilegio.microservice_privilegio.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Elemento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer elementId;
    private String elementCode;
    private String module;
    private String elementName;
    private String elementCommand;
}