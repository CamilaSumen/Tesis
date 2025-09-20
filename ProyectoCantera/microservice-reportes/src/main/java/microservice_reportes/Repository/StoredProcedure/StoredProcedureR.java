package microservice_reportes.Repository.StoredProcedure;

public class StoredProcedureR {

    // Procedimientos almacenados para reportes
    public static final String SP_OBTENER_MOVIMIENTOS_INVENTARIO = "EXEC sp_obtener_movimientos_inventario_completo ?, ?, ?, ?, ?, ?, ?";
    public static final String SP_OBTENER_MOVIMIENTOS_CAJA = "EXEC sp_obtener_movimientos_caja_completo ?, ?, ?, ?, ?, ?, ?, ?";
    public static final String SP_OBTENER_MOVIMIENTOS_CONSOLIDADOS = "EXEC sp_obtener_movimientos_consolidados ?, ?, ?, ?, ?, ?";
    public static final String SP_OBTENER_ESTADISTICAS = "EXEC sp_obtener_estadisticas_movimientos ?, ?";
    public static final String SP_OBTENER_TIPOS_MOVIMIENTO = "EXEC sp_obtener_tipos_movimiento";
    public static final String SP_OBTENER_USUARIOS_MOVIMIENTOS = "EXEC sp_obtener_usuarios_movimientos";
}