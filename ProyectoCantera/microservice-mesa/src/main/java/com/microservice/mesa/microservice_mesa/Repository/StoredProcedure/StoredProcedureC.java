package com.microservice.mesa.microservice_mesa.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LAS MESAS*/
    public static final String SEL_MESA= "{call PA_Mesa_Sel_ListarMesas}";
    public static final String INS_MESANUEVO = "{call PA_Mesa_Ins_NuevaMesa(?)}";
    public static final String UPD_ELIMARMESALOGICO = "{call PA_Mesa_Upd_EliminarMesa(?)}";
    public static final String UPD_MODIFICARMESA = "{call PA_Mesa_Upd_NuevaMesa(?,?)}";
    public static final String UPD_OCUPADAMESA = "{call PA_Mesa_Upd_OcupadoMesa(?)}";
    public static final String UPD_DESOCUPARDAMESA = "{call PA_Mesa_Upd_DesocuparMesa(?)}";

}