package com.jcou.accountingdilemma.accounting;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents the abstract concept of 'Amount' to centralize the scaling
 */
public abstract class Amount {
private static final int HALF_UP_SCALING = 2;

    protected BigDecimal amount;

    public Amount(BigDecimal amount) {
        this.amount = amount.setScale(HALF_UP_SCALING, RoundingMode.HALF_UP);
    }

    /**
     * Function used to validate an Amount when it should be valid
     * Something unexpected happened and needs to be fixed if the error is thrown
     * @throws IllegalArgumentException if the amount is null
     */
    public void validate() {
        if (this.amount == null) {
            throw new IllegalArgumentException("Amount must not be null");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(!(obj instanceof Amount)) {
            return false;
        }

        Amount other = (Amount) obj;

        if (this.amount !=null) {
            return this.amount.equals(other.amount);
        } else {
            return this.amount == other.amount;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (amount == null ? 0 : amount.hashCode());
        return result;
    }
}