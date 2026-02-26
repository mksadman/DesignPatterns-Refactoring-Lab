package edu.iutcs.cr.persons;

import java.io.Serializable;
import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Buyer extends Person implements Serializable {

    private String paymentMethod;

    public Buyer() {
        super();
        promptPaymentMethod();
    }

    public Buyer(String id) {
        super(id);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    /** Prompts the user and assigns the payment method. */
    public void promptPaymentMethod() {
        setPaymentMethod(InputHelper.readNonBlank("Enter new payment method: ", "Payment method is mandatory!"));
    }

    /** Pure setter — assigns payment method without any I/O. */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", paymentMethod='" + paymentMethod + '\'';
    }
}
