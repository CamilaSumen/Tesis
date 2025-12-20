package microservice.privilegio.microservice_privilegio.Repository.Translator;


import microservice.privilegio.microservice_privilegio.Model.ElementoxCargo;

public class ElementoxCargoTranslator {

    private Integer elementChargeId;
    private Integer elementId;
    private Integer chargeId;
    private String elementCode;
    private String module;
    private String elementName;
    private String elementCommand;

    // Getters
    public Integer getElementChargeId() { return elementChargeId; }
    public Integer getElementId() { return elementId; }
    public Integer getChargeId() { return chargeId; }
    public String getElementCode() { return elementCode; }
    public String getModule() { return module; }
    public String getElementName() { return elementName; }
    public String getElementCommand() { return elementCommand; }

    // Setters
    public void setElementChargeId(Integer elementChargeId) { this.elementChargeId = elementChargeId; }
    public void setElementId(Integer elementId) { this.elementId = elementId; }
    public void setChargeId(Integer chargeId) { this.chargeId = chargeId; }
    public void setElementCode(String elementCode) { this.elementCode = elementCode; }
    public void setModule(String module) { this.module = module; }
    public void setElementName(String elementName) { this.elementName = elementName; }
    public void setElementCommand(String elementCommand) { this.elementCommand = elementCommand; }

    public ElementoxCargo toElementoxCargoDTO() {
        ElementoxCargo elementoxCargo = new ElementoxCargo();
        elementoxCargo.setElementChargeId(this.elementChargeId);
        elementoxCargo.setElementId(this.elementId);
        elementoxCargo.setChargeId(this.chargeId);
        elementoxCargo.setElementCode(this.elementCode);
        elementoxCargo.setModule(this.module);
        elementoxCargo.setElementName(this.elementName);
        elementoxCargo.setElementCommand(this.elementCommand);
        return elementoxCargo;
    }
}