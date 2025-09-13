package microservice_pedido.Repository.StoredProcedure;

public class StoredProcedureC {


    /*PEDIDOS STORED PROCEDURES*/
    public static final String SEL_PEDIDO = "EXEC sp_listar_pedidos";
    public static final String SP_GUARDAR_PEDIDO = "EXEC sp_guardar_pedido ?, ?, ?, ?, ?";
    public static final String UPD_ESTADO_PEDIDO = "EXEC sp_actualizar_estado_pedido ?, ?";
    public static final String SEL_PEDIDO_BY_ID = "EXEC sp_obtener_pedido_por_id ?";

    public static final String SP_OBTENER_PEDIDO_PARA_EDITAR = "EXEC sp_obtener_pedido_para_editar ?";
    public static final String SP_ACTUALIZAR_PEDIDO = "EXEC sp_actualizar_pedido ?, ?, ?, ?, ?, ?";
}