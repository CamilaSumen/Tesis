package microservice_reportes.Repository.StoredProcedure;

public class StoredProcedureD {

    // Procedimientos almacenados para dashboard
    public static final String SP_RESUMEN_VENTAS_DIA = "EXEC sp_obtener_resumen_ventas_dia ?";
    public static final String SP_ESTADOS_PEDIDOS = "EXEC sp_obtener_estados_pedidos_resumen";
    public static final String SP_STOCK_CRITICO = "EXEC sp_obtener_stock_critico";
    public static final String SP_PRODUCTO_MAS_VENDIDO = "EXEC sp_obtener_producto_mas_vendido ?";
    public static final String SP_RESUMEN_DELIVERY = "EXEC sp_obtener_resumen_delivery";
    public static final String SP_VENTAS_SEMANALES = "EXEC sp_obtener_ventas_semanales ?, ?";
    public static final String SP_VENTAS_POR_CATEGORIA = "EXEC sp_obtener_ventas_por_categoria ?";
    public static final String SP_DESGLOSE_CAJA = "EXEC sp_obtener_desglose_caja_dia ?";
}