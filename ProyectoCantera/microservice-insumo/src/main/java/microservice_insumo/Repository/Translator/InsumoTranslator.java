package microservice_insumo.Repository.Translator;

import microservice_insumo.Model.Insumo;

public class InsumoTranslator {

    private Integer supplyId;
    private Integer supplyCategoryId;
    private String supplyCategoryName;
    private String supplyName;
    private String unitOfMeasure;
    private Double currentStock;
    private Boolean status;

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public void setSupplyCategoryId(Integer supplyCategoryId) {
        this.supplyCategoryId = supplyCategoryId;
    }

    public void setSupplyCategoryName(String supplyCategoryName) {
        this.supplyCategoryName = supplyCategoryName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public void setCurrentStock(Double currentStock) {
        this.currentStock = currentStock;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Insumo toInsumoDTO() {
        Insumo insumo = new Insumo();
        insumo.setSupplyId(this.supplyId);
        insumo.setSupplyCategoryName(this.supplyCategoryName);
        insumo.setSupplyName(this.supplyName);
        insumo.setUnitOfMeasure(this.unitOfMeasure);
        insumo.setCurrentStock(this.currentStock);
        insumo.setStatus(this.status);
        return insumo;
    }
}
