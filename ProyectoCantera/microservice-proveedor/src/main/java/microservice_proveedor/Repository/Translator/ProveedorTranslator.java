package microservice_proveedor.Repository.Translator;

import microservice_proveedor.Model.Proveedor;

public class ProveedorTranslator {

    private Integer supplierId;
    private String supplierName;
    private String ruc;
    private String phone;
    private String address;
    private Boolean status;

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Proveedor toProveedorDTO() {
        Proveedor proveedor = new Proveedor();
        proveedor.setSupplierId(this.supplierId);
        proveedor.setSupplierName(this.supplierName);
        proveedor.setRuc(this.ruc);
        proveedor.setPhone(this.phone);
        proveedor.setAddress(this.address);
        proveedor.setStatus(this.status);
        return proveedor;
    }
}
