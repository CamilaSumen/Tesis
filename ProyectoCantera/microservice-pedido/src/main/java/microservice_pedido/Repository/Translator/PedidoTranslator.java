package microservice_pedido.Repository.Translator;

import com.microservice.mesa.microservice_mesa.Model.Pedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoTranslator {

    private Integer pedidoID;
    private String mesa;
    private String mozo;
    private Integer numeroPersonas;
    private LocalDateTime fechaPedido;
    private String estado;
    private String observaciones;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public static Pedido toPedidoDTO(PedidoTranslator translator) {
        Pedido pedido = new Pedido();

        pedido.setPedidoID(translator.getPedidoID());
        pedido.setMesa(translator.getMesa());
        pedido.setMozo(translator.getMozo());
        pedido.setNumeroPersonas(translator.getNumeroPersonas());
        pedido.setFechaPedido(translator.getFechaPedido());
        pedido.setEstado(translator.getEstado());
        pedido.setObservaciones(translator.getObservaciones());
        pedido.setTotal(translator.getTotal());
        pedido.setFechaCreacion(translator.getFechaCreacion());
        pedido.setFechaActualizacion(translator.getFechaActualizacion());

        return pedido;
    }
}