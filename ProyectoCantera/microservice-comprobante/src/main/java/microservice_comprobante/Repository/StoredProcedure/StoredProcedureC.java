package microservice_comprobante.Repository.StoredProcedure;

public class StoredProcedureC {

    // Agregar estas constantes a tu StoredProcedureC existente:
    public static final String SP_REGISTRAR_CLIENTE = "EXEC sp_registrar_cliente ?, ?, ?, ?, ?";
    public static final String SP_GUARDAR_COMPROBANTE = "EXEC sp_guardar_comprobante ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    public static final String SP_OBTENER_COMPROBANTE = "EXEC sp_obtener_comprobante ?";
    public static final String SP_LISTAR_COMPROBANTES = "EXEC sp_listar_comprobantes ?, ?, ?";
}