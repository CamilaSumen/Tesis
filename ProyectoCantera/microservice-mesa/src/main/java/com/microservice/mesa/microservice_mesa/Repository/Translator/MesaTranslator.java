package com.microservice.mesa.microservice_mesa.Repository.Translator;

import com.microservice.mesa.microservice_mesa.Model.Mesa;

public class MesaTranslator {

    private Integer tableId;
    private String tableCode;
    private Boolean tableBusy;
    private Boolean stateTable;

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public void setTableBusy(Boolean tableBusy) {
        this.tableBusy = tableBusy;
    }

    public void setStateTable(Boolean stateTable) {
        this.stateTable = stateTable;
    }


    public Mesa toMesaDTO() {
        Mesa mesa = new Mesa();
        mesa.setTableId(this.tableId);
        mesa.setTableCode(this.tableCode);
        mesa.setTableBusy(this.tableBusy);
        mesa.setStateTable(this.stateTable);
        return mesa;
    }
}
