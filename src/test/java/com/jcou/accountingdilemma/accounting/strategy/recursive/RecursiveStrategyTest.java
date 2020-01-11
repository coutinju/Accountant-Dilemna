package com.jcou.accountingdilemma.accounting.strategy.recursive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;

import com.jcou.accountingdilemma.accounting.BankTransfer;
import com.jcou.accountingdilemma.accounting.DuePayment;
import com.jcou.accountingdilemma.accounting.Solution;
import com.jcou.accountingdilemma.io.input.InputFileData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecursiveStrategyTest {
    private RecursiveStrategy recursiveStrategy;

    @BeforeEach
    public void beforeEach() {
        this.recursiveStrategy = new RecursiveStrategy();
    }

    @Test
    public void testFindSolution_givenNullThenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            this.recursiveStrategy.findSolution(null)
        );
    }

    @Test
    public void testFindSolution_givenImpossibleMatchThenReturnsNull() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("2")), 
                Arrays.asList(
                    new DuePayment(new BigDecimal("1"))
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        assertNull(solution);
    }

    @Test
    public void testFindSolution_givenEmptyDuePaymentsThenReturnsReturnsNull() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("2")), 
                Arrays.asList(
                    // Missing DuePayments
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        assertNull(solution);
    }

    @Test
    public void testFindSolution_givenOneBankTransferOneDuePaymentThenReturnsTheFirstDuePayment() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("1")), 
                Arrays.asList(
                    new DuePayment(new BigDecimal("1"))
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        this.validateSolution(solution, inputFileData);
    }

    @Test
    public void testFindSolution_givenOneBankTransferTwoDuePaymentsThenReturnsTheSecondDuePayment() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("1")), 
                Arrays.asList(
                    new DuePayment(new BigDecimal("2")),
                    new DuePayment(new BigDecimal("1"))
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        this.validateSolution(solution, inputFileData);
    }

    @Test
    public void testFindSolution_givenOneBankTransferTwoDuePaymentsThenReturnsBothDuePayments() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("2")), 
                Arrays.asList(
                    new DuePayment(new BigDecimal("1")),
                    new DuePayment(new BigDecimal("1"))
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        this.validateSolution(solution, inputFileData);
    }

    @Test
    public void testFindSolution_givenOneBankTransferThreeDuePaymentsThenReturnsTwoLastDuePayments() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("3")), 
                Arrays.asList(
                    new DuePayment(new BigDecimal("4")),
                    new DuePayment(new BigDecimal("2")),
                    new DuePayment(new BigDecimal("1"))
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        this.validateSolution(solution, inputFileData);
    }

    @Test
    public void testFindSolution_givenOneBankTransferFourDuePaymentsThenReturnsFirstAndLastDuePayments() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("6")), 
                Arrays.asList(
                    new DuePayment(new BigDecimal("5")),
                    new DuePayment(new BigDecimal("2")),
                    new DuePayment(new BigDecimal("2")),
                    new DuePayment(new BigDecimal("1"))
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        this.validateSolution(solution, inputFileData);
    }

    @Test
    public void testFindSolution_givenDefaultTestFileThenReturnSolution() {
        InputFileData inputFileData = 
            new InputFileData(
                new BankTransfer(new BigDecimal("74.06")), 
                Arrays.asList(
                    new DuePayment(new BigDecimal("22.75")),
                    new DuePayment(new BigDecimal("59.33")),
                    new DuePayment(new BigDecimal("34.22")),
                    new DuePayment(new BigDecimal("27.21")),
                    new DuePayment(new BigDecimal("17.09")),
                    new DuePayment(new BigDecimal("100.99"))
                )
            );

        Solution solution = this.recursiveStrategy.findSolution(inputFileData);

        this.validateSolution(solution, inputFileData);
    }

    /**
     * Asserts the solution is correct
     * @param solution the solution found
     * @param inputFileData the input file data
     */
    private void validateSolution(Solution solution, InputFileData inputFileData) {
        assertNotNull(solution);

        assertEquals(
            inputFileData.getBankTransfer().getAmount(), 
            solution.getDuePaymentsToSumList().stream()
                .map(DuePayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}