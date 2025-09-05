package microservice_adicional.Repository.Translator;

import microservice_adicional.Model.Adicional;

import java.time.LocalTime;

public class AdicionalTranslator {

    private Integer adicionalId;
    private String adicionalName;
    private String adicionalDescription;
    private Double adicionalPrecio;
    private LocalTime adicionalfechaRegistro;
    private Boolean stateAdicional;

    public void setAdicionalId(Integer adicionalId) {
        this.adicionalId = adicionalId;
    }

    public void setAdicionalName(String adicionalName) {
        this.adicionalName = adicionalName;
    }

    public void setAdicionalDescription(String adicionalDescription) {
        this.adicionalDescription = adicionalDescription;
    }

    public void setAdicionalPrecio(Double adicionalPrecio) {
        this.adicionalPrecio = adicionalPrecio;
    }

    public void setAdicionalfechaRegistro(LocalTime adicionalfechaRegistro) {
        this.adicionalfechaRegistro = adicionalfechaRegistro;
    }

    public void setStateAdicional(Boolean stateAdicional) {
        this.stateAdicional = stateAdicional;
    }

    public Adicional toAdicionalDTO() {
        Adicional adicional = new Adicional();
        adicional.setAdicionalId(this.adicionalId);
        adicional.setAdicionalName(this.adicionalName);
        adicional.setAdicionalDescription(this.adicionalDescription);
        adicional.setAdicionalPrecio(this.adicionalPrecio);
        adicional.setAdicionalfechaRegistro(this.adicionalfechaRegistro);
        adicional.setStateAdicional(this.stateAdicional);
        return adicional;
    }
}
