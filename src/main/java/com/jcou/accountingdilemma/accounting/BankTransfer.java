package com.jcou.accountingdilemma.accounting;

import java.math.BigDecimal;

/**
 * Object representing a 'Bank Transfer'’':
 * The 'Bank Transfer' value represents the amount of a bank transfer 
 * which sums up payments of sold items in a specific period of time 
 * (as they appear in the company’s account statement).
 * 
 * Implementing Cloneable to instantiate the immutable class InputFileData
 */
public class BankTransfer extends Amount implements Cloneable {
    /**
     * @param amount Amount of a Bank Transfer 
     */
    public BankTransfer(BigDecimal amount) {
        super(amount);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public BankTransfer clone() {
        return new BankTransfer(this.amount);
    }
}