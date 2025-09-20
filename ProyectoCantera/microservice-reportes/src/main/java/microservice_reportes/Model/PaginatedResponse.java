package microservice_reportes.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<T> data;
    private Integer totalRegistros;
    private Integer paginaActual;
    private Integer tamanioPagina;
    private Integer totalPaginas;
    private Boolean success;
    private String mensaje;

    public PaginatedResponse(List<T> data, Integer totalRegistros, Integer pageNumber, Integer pageSize) {
        this.data = data;
        this.totalRegistros = totalRegistros;
        this.paginaActual = pageNumber;
        this.tamanioPagina = pageSize;
        this.totalPaginas = (int) Math.ceil((double) totalRegistros / pageSize);
        this.success = true;
    }

    public PaginatedResponse() {
    }

    public static <T> PaginatedResponse<T> error(String mensaje) {
        PaginatedResponse<T> response = new PaginatedResponse<>();
        response.success = false;
        response.mensaje = mensaje;
        return response;
    }
}
