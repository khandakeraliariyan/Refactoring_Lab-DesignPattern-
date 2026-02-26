package edu.iutcs.cr.persons;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Buyer extends Person {

    private PaymentMethod paymentMethod;

    public Buyer(String name, String id, String email, PaymentMethod paymentMethod) {
        super(name, id, email);
        this.paymentMethod = paymentMethod;
    }

    public Buyer(String id) {
        super(id);
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", paymentMethod='" + paymentMethod + '\'';
    }
}