package com.microservice.mesa.microservice_mesa.Repository.Translator;

import com.microservice.mesa.microservice_mesa.Model.Mesa;

public class MesaTranslator {

    private Integer tableId;
    private Integer pedidoID;
    private String tableCode;
    private Boolean tableBusy;
    private Boolean stateTable;
    private String username;

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public void setPedidoID(Integer pedidoID) {
        this.pedidoID = pedidoID;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public Mesa toMesaDTO() {
        Mesa mesa = new Mesa();
        mesa.setTableId(this.tableId);
        mesa.setPedidoID(this.pedidoID);
        mesa.setTableCode(this.tableCode);
        mesa.setTableBusy(this.tableBusy);
        mesa.setUsername(this.username);
        mesa.setStateTable(this.stateTable);
        return mesa;
    }
}
