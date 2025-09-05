package microservice_delivery.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LAS DELIVERYS*/
    public static final String SEL_DELIVERY= "{call PA_Mesa_Sel_ListarDelivery}";
    public static final String INS_DELIVERYNUEVO = "{call PA_Mesa_Ins_NuevoDelivery(?,?)}";
    public static final String UPD_ELIMARDELIVERYLOGICO = "{call PA_Delivery_Upd_EliminarDelivery(?)}";
    public static final String UPD_MODIFICARDELIVERY = "{call PA_Delivery_Upd_Delivery(?,?,?)}";

}