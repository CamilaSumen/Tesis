package microservice_cargo.Repository.StoredProcedure;

public class StoredProcedureC {
    public static final String SEL_CARGO= "{call Cargo_Sel_ListarCargo}";
    public static final String INS_CARGONUEVO = "{call Cargo_Ins_NuevoCargo(?,?)}";
    public static final String UPD_ELIMARCARGOLOGICO = "{call Cargo_Upd_EliminarCargo(?)}";
    public static final String UPD_MODIFICARCARGO = "{call Cargo_Upd_ModificarCargo(?,?,?)}";
}