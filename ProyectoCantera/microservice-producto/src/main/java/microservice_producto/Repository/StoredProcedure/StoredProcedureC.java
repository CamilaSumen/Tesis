package microservice_producto.Repository.StoredProcedure;

public class StoredProcedureC {

    /*PA'S PARA LAS PRODUCTOS*/
    public static final String SEL_PRODUCTO= "{call PA_Producto_Sel_ListarProducto}";
    public static final String INS_PRODUCTONUEVO = "{call PA_Producto_Ins_NuevaProducto(?,?,?,?,?)}";
    public static final String UPD_ELIMARPRODUCTOLOGICO = "{call PA_Producto_Upd_EliminarProducto(?)}";
    public static final String UPD_MODIFICARPRODUCTO = "{call PA_Producto_Upd_Producto(?,?,?,?,?,?)}";

}