package microservice_cargo.Repository.Translator;

import microservice_cargo.Model.Cargo;

public class CargoTranslator {

    private int charge;
    private String nameCargue;
    private String descriptionCargue;
    private boolean stateCargue;

    public void setCharge(int charge) { this.charge = charge; }
    public void setNameCargue(String nameCargue) { this.nameCargue = nameCargue; }
    public void setDescriptionCargue(String descriptionCargue) { this.descriptionCargue = descriptionCargue; }
    public void setStateCargue(boolean stateCargue) { this.stateCargue = stateCargue; }

    public Cargo toCargoDTO() {
        Cargo cargo = new Cargo();
        cargo.setCharge(this.charge);
        cargo.setNameCargue(this.nameCargue);
        cargo.setDescriptioncargue(this.descriptionCargue);
        cargo.setStateCargue(this.stateCargue);
        return cargo;
    }
}