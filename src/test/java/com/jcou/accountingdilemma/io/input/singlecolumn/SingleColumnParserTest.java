package com.jcou.accountingdilemma.io.input.singlecolumn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.jcou.accountingdilemma.accounting.BankTransfer;
import com.jcou.accountingdilemma.accounting.DuePayment;
import com.jcou.accountingdilemma.io.input.InputFileData;
import com.jcou.accountingdilemma.util.FileUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingleColumnParserTest {
    private SingleColumnParser singleColumnParser;

    @BeforeEach
    public void beforeEach() {
        this.singleColumnParser = new SingleColumnParser();
    }

    @Test
    public void testParse_givenNullThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> this.singleColumnParser.parse(null));
    }

    @Test
    public void testParse_givenInvalidPathThenThrowInvalidPathException() {
        assertThrows(RuntimeException.class, () -> this.singleColumnParser.parse("INVALID:\\"));
    }

    @Test
    public void testParse_givenInvalidContentFileThenThrowNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> this.singleColumnParser
                .parse(FileUtil.generateTestFilePath("/invalid-number-format-exception.txt")));
    }

    @Test
    public void testParse_givenValidFileThenReturnCorrectInputFileData() {
        InputFileData inputFileData = this.singleColumnParser.parse(
            FileUtil.generateTestFilePath("/default-test-file.txt")
        );
        assertNotNull(inputFileData);

        BankTransfer bankTransfer = inputFileData.getBankTransfer();
        assertNotNull(bankTransfer);
        assertEquals(new BigDecimal("74.06"), bankTransfer.getAmount());

        List<DuePayment> duePaymentsAmountsList = Arrays.asList(
            new DuePayment(new BigDecimal("22.75")),
            new DuePayment(new BigDecimal("59.33")),
            new DuePayment(new BigDecimal("34.22")),
            new DuePayment(new BigDecimal("27.21")),
            new DuePayment(new BigDecimal("17.09")),
            new DuePayment(new BigDecimal("100.99"))
        );

        List<DuePayment> duePaymentsList = inputFileData.getDuePaymentsList();
        assertNotNull(duePaymentsList);
        assertEquals(duePaymentsAmountsList.size(), duePaymentsList.size());
        assertTrue(inputFileData.getDuePaymentsList().containsAll(duePaymentsAmountsList));
    }
}