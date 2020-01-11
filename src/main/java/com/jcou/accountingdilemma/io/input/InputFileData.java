package com.jcou.accountingdilemma.io.input;

import java.util.List;
import java.util.stream.Collectors;

import com.jcou.accountingdilemma.accounting.BankTransfer;
import com.jcou.accountingdilemma.accounting.DuePayment;

/**
 * Immutable class which represents data parsed from the input file
 */
public class InputFileData {
    private final BankTransfer bankTransfer;

    private final List<DuePayment> duePaymentsList;

    public InputFileData(BankTransfer bankTransfer, List<DuePayment> duePaymentsList) {
        this.bankTransfer = bankTransfer.clone();
        this.duePaymentsList = duePaymentsList.stream()
            .map(DuePayment::clone)
            .collect(Collectors.toList());
    }

    public BankTransfer getBankTransfer() {
        return this.bankTransfer.clone();
    }

    public List<DuePayment> getDuePaymentsList() {
        return this.duePaymentsList.stream().map(DuePayment::clone)
            .collect(Collectors.toList());
    }

    /**
     * Function used to validate an InputFileData when it should be valid
     * Something unexpected happened and needs to be fixed if the error is thrown
     * @throws IllegalArgumentException if the Bank Transfer is not valid 
     *                                  or if the list of Due Payments is not valid
     */
    public void validate() {
        BankTransfer bankTransfer = this.getBankTransfer();
        if (bankTransfer == null) {
            throw new IllegalArgumentException("Bank transfer must not be null");
        } else {
            bankTransfer.validate();
        }
        List<DuePayment> duePaymentsList = this.getDuePaymentsList();
        if (duePaymentsList == null) {
            throw new IllegalArgumentException("Due Payments List must not be null");
        } else {
            duePaymentsList.stream().forEach(DuePayment::validate);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(!(obj instanceof InputFileData)) {
            return false;
        }

        InputFileData other = (InputFileData) obj;

        return  (this.bankTransfer == null ? 
                    other.bankTransfer == null : 
                    this.bankTransfer.equals(other.bankTransfer))
                && (this.duePaymentsList == null ?
                    other.duePaymentsList == null :
                    this.duePaymentsList.equals(other.duePaymentsList));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (bankTransfer == null ? 0 : bankTransfer.hashCode());
        result = 31 * result + (duePaymentsList == null ? 0 : duePaymentsList.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Bank Transfer amount: " + this.bankTransfer.getAmount() 
            + "; Number of DuePayments: " + duePaymentsList.size();
    }
}