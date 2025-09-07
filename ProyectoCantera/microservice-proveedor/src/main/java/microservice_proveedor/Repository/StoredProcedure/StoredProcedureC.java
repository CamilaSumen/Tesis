package microservice_proveedor.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LAS PROVEEDORS*/
    public static final String SEL_PROVEEDOR= "{call PA_Proveedores_Sel_ListarProveedores}";
    public static final String INS_PROVEEDORNUEVO = "{call PA_Proveedores_Ins_NuevaProveedores(?,?,?,?)}";
    public static final String UPD_ELIMARPROVEEDORLOGICO = "{call PA_Proveedor_Upd_EliminarProveedor(?)}";
    public static final String UPD_MODIFICARPROVEEDOR = "{call PA_Proveedores_Upd_ActualizarProveedores(?,?,?,?,?)}";

}