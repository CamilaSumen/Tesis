package microservice_usuario.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LOS USUARIOS*/
    public static final String SEL_USUARIO= "{call PA_Usuario_ListarUsuario}";
    public static final String INS_USUARIONUEVO = "{call PA_Usuario_Ins_NuevoUsuario(?,?,?,?,?)}";
    public static final String UPD_ELIMARTUSUARIOLOGICO = "{call PA_Usuario_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICARUSUARIO = "{call PA_Usuario_Upd_Modificar(?,?,?,?,?)}";

    public static final String UPD_VERIFICARUSUARIO = "{call PA_Usuario_VerificarUsuario(?,?)}";


}