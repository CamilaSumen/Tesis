package microservice_formapago.Repository.Translator;

import microservice_formapago.Model.FormaPago;

public class FormaPagoTranslator {

    private Integer paymentId;
    private String paymentName;
    private String paymentImagen;
    private Boolean statePayment;

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public void setPaymentImagen(String paymentImagen) {
        this.paymentImagen = paymentImagen;
    }

    public void setStatePayment(Boolean statePayment) {
        this.statePayment = statePayment;
    }

    public FormaPago toFormaPagoDTO() {
        FormaPago formaPago = new FormaPago();
        formaPago.setPaymentId(this.paymentId);
        formaPago.setPaymentName(this.paymentName);
        formaPago.setPaymentImagen(this.paymentImagen);
        formaPago.setStatePayment(this.statePayment);
        return formaPago;
    }
}
