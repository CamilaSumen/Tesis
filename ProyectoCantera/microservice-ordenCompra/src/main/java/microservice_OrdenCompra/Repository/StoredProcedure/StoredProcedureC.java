package microservice_OrdenCompra.Repository.StoredProcedure;

public class StoredProcedureC {

    // Procedimientos almacenados para órdenes de compra
    public static final String SP_CREAR_ORDEN_COMPRA = "EXEC sp_crear_orden_compra ?, ?, ?, ?";
    public static final String SP_RECIBIR_ORDEN_COMPRA = "EXEC sp_recibir_orden_compra ?, ?, ?";
    public static final String SP_REGISTRAR_PAGO_ORDEN = "EXEC sp_registrar_pago_orden_compra ?, ?, ?, ?, ?";
    public static final String SP_LISTAR_ORDENES_COMPRA = "EXEC sp_listar_ordenes_compra ?, ?, ?, ?";
    public static final String SP_OBTENER_DETALLE_ORDEN = "EXEC sp_obtener_detalle_orden_compra ?";
    public static final String SP_ANULAR_ORDEN_COMPRA = "EXEC sp_anular_orden_compra ?, ?, ?";
}
