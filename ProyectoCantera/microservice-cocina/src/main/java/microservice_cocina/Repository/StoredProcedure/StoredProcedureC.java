package microservice_cocina.Repository.StoredProcedure;

public class StoredProcedureC {

    // Agregar estas constantes a tu StoredProcedureC existente:
    public static final String SP_OBTENER_PEDIDOS_COCINA_POR_ESTADO = "EXEC sp_obtener_pedidos_cocina_por_estado ?";
    public static final String SP_CAMBIAR_ESTADO_PEDIDO_COCINA = "EXEC sp_cambiar_estado_pedido_cocina ?, ?";
    public static final String SP_OBTENER_DETALLE_PEDIDO_COCINA = "EXEC sp_obtener_detalle_pedido_cocina ?";
    public static final String SP_OBTENER_ESTADISTICAS_COCINA = "EXEC sp_obtener_estadisticas_cocina";
}