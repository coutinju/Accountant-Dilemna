package com.jcou.accountingdilemma.accounting;

import java.math.BigDecimal;

/**
 * Object representing a 'Due Payment': The 'Due Payment' value represents the
 * amount the company should receive as payment for a given product which is
 * recently sold
 * 
 * Implementing Cloneable to instantiate the immutable class InputFileData
 */
public class DuePayment extends Amount implements Cloneable {
    /**
     * @param amount Amount of a Due Payment
     */
    public DuePayment(BigDecimal amount) {
        super(amount);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public DuePayment clone() {
        return new DuePayment(this.amount);
    }
}