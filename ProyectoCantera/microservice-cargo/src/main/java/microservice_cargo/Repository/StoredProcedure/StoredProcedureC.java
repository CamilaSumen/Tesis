package microservice_cargo.Repository.StoredProcedure;

public class StoredProcedureC {
    /*PA'S PARA LOS CARGOS*/
    public static final String SEL_CARGO= "{call PA_Cargo_Sel_ListarCargo}";
    public static final String INS_CARGONUEVO = "{call PA_Cargo_Ins_NuevoCargo(?,?,?)}";
    public static final String UPD_ELIMARCARGOLOGICO = "{call PA_Cargo_Upd_EliminarCargo(?)}";
    public static final String UPD_MODIFICARCARGO = "{call PA_Cargo_Upd_ModificarCargo(?,?,?,?)}";

    /*PA'S PARA LOS USUARIOS*/
    public static final String SEL_USUARIO= "{call PA_Usuario_ListarUsuario}";
    public static final String INS_USUARIONUEVO = "{call PA_Usuario_Ins_NuevoUsuario(?,?,?,?,?)}";
    public static final String UPD_ELIMARTUSUARIOLOGICO = "{call PA_Usuario_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICARUSUARIO = "{call PA_Usuario_Upd_Modificar(?,?,?,?,?)}";

}