package microservice_delivery.Repository.Translator;

import microservice_delivery.Model.Delivery;

public class DeliveryTranslator {

    private Integer deliveryId;
    private String deliveryName;
    private String deliveryPhone;
    private Boolean stateDelivery;


    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public void setStateDelivery(Boolean stateDelivery) {
        this.stateDelivery = stateDelivery;
    }

    public Delivery toDeliveryDTO() {
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(this.deliveryId);
        delivery.setDeliveryName(this.deliveryName);
        delivery.setDeliveryPhone(this.deliveryPhone);
        delivery.setStateDelivery(this.stateDelivery);
        return delivery;
    }
}
