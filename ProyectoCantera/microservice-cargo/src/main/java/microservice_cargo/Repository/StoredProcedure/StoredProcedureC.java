package microservice_cargo.Repository.StoredProcedure;

public class StoredProcedureC {
    /*PA'S PARA LOS CARGOS*/
    public static final String SEL_CARGO= "{call PA_Cargo_Sel_ListarCargo}";
    public static final String INS_CARGONUEVO = "{call PA_Cargo_Ins_NuevoCargo(?,?,?)}";
    public static final String UPD_ELIMARCARGOLOGICO = "{call PA_Cargo_Upd_EliminarCargo(?)}";
    public static final String UPD_MODIFICARCARGO = "{call PA_Cargo_Upd_ModificarCargo(?,?,?,?)}";

    /*PA'S PARA LOS EMPLEADOS*/
    public static final String SEL_EMPLEADO= "{call PA_Empleado_Sel_Listar}";
    public static final String INS_EMPLEADONUEVO = "{call PA_Empleado_Ins_Nuevo(?,?,?,?,?,?,?,?,?,?)}";
    public static final String UPD_ELIMAREMPLEADOLOGICO = "{call PA_Empleado_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICAREMPLEADO = "{call PA_Empleado_Upd_Modificar(?,?,?,?,?,?,?,?,?,?)}";

    /*PA'S PARA LOS PRIVILEGIOS*/
    public static final String SEL_PRIVILEGIO= "{call PA_Privilegios_Sel_Listar}";
    public static final String INS_PRIVILEGIONUEVO = "{call PA_Privilegios_Ins_Nuevo(?,?,?)}";
    public static final String UPD_ELIMARPRIVILEGIOLOGICO = "{call PA_Privilegios_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICARPRIVILEGIO = "{call PA_Privilegios_Upd_Modificar(?,?,?,?)}";

    /*PA'S PARA LOS TURNOS*/
    public static final String SEL_TURNO= "{call PA_Turno_Sel_Listar}";
    public static final String INS_TURNONUEVO = "{call PA_Turno_Ins_Nuevo(?,?,?)}";
    public static final String UPD_ELIMARTURNOLOGICO = "{call PA_Turno_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICARTURNO = "{call PA_Turno_Upd_Modificar(?,?,?,?)}";

    /*PA'S PARA LOS USUARIOS*/
    public static final String SEL_USUARIO= "{call PA_Usuario_ListarUsuario}";
    public static final String INS_USUARIONUEVO = "{call PA_Usuario_Ins_NuevoUsuario(?,?,?,?,?)}";
    public static final String UPD_ELIMARTUSUARIOLOGICO = "{call PA_Usuario_Upd_Eliminar(?)}";
    public static final String UPD_MODIFICARUSUARIO = "{call PA_Usuario_Upd_Modificar(?,?,?,?,?)}";

}