package edu.iutcs.cr.persons;

import java.io.Serializable;

/**
 * Represents the available payment methods for a buyer.
 */
public enum PaymentMethod implements Serializable {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    BANK_TRANSFER;

    @Override
    public String toString() {
        return name().replace('_', ' ').toLowerCase();
    }
}