package com.jcou.accountingdilemma.io.input.singlecolumn;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jcou.accountingdilemma.accounting.BankTransfer;
import com.jcou.accountingdilemma.accounting.DuePayment;
import com.jcou.accountingdilemma.io.input.InputFileData;
import com.jcou.accountingdilemma.io.input.InputFileParser;

import org.apache.log4j.Logger;

/**
 * Default implementation of the InputFileParser
 * 
 * Expected:
 * -The input is a text file containing one number per line
 * -The first number represents the 'Bank Transfer' 
 * -The remaining numbers represent the 'Due Payment's
 * -Each number has exactly 2 decimals
 */
public class SingleColumnParser extends InputFileParser {
    private static final Logger logger = Logger.getLogger(SingleColumnParser.class);

    /**
     * Finds and parses the input file to return InputFileData
     * @param filePath input file path
     * @return data extracted from the input file as an InputFileData
     */
    @Override
    public InputFileData parse(String filePath) {
        logger.debug("Parsing started: " + filePath);
        validateFilePath(filePath);

        List<BigDecimal> amountsList = extractAmounts(filePath);
        
        InputFileData inputFileData = this.generateInputFileData(amountsList);

        logger.debug("Parsing ended, InputFileData returned: " + inputFileData.toString());
        return inputFileData;
    }

    /**
     * Extracts the amounts from the input file (if there are no errors)
     * @param filePath input file path
     * @return a list of amounts
     */
    private List<BigDecimal> extractAmounts(String filePath) {
        List<BigDecimal> amountsList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            amountsList = stream
                .map(line -> new BigDecimal(line))
                .collect(Collectors.toList());

		} catch (InvalidPathException e) {
            throw new InvalidPathException(filePath, "Invalid file path");
        } catch (IOException e) {
            throw new RuntimeException("Input file cannot be opened: " + filePath);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input file content: " + filePath);
        }

        logger.debug("Number of amounts found: " + amountsList.size());
        return amountsList;
    }

    /**
     * Generates an InputFileData from a list of amounts
     * @param amountsList list of amounts found in the input file
     * @return InputFileData representing the input file
     */
    private InputFileData generateInputFileData(List<BigDecimal> amountsList) {
        this.validateAmountsList(amountsList);

        BankTransfer bankTransfer = extractBankTransfer(amountsList);

        List<DuePayment> duePaymentsList = extractDuePayments(amountsList);

        InputFileData inputFileData = new InputFileData(bankTransfer, duePaymentsList);

        return inputFileData;
    }

    /**
     * Validates the list of amounts
     * @param amountsList list of amounts
     * @throws IllegalArgumentException if the list is null or empty
     */
    private void validateAmountsList(List<BigDecimal> amountsList) {
        if (amountsList == null || amountsList.isEmpty()) {
            throw new IllegalArgumentException("Input file is empty");
        }
    }

    /**
     * Generates 'Due Payment's from a partial list of amounts (the first is not used)
     * @param amountsList the list of amounts
     * @return a list of 'Due Payment's which represents data from the input file
     */
    private List<DuePayment> extractDuePayments(List<BigDecimal> amountsList) {
        List<DuePayment> duePaymentsList = new ArrayList<>();
        for (int i = 1; i < amountsList.size(); i++) {
            BigDecimal duePaymentAmount = amountsList.get(i);
            duePaymentsList.add(new DuePayment(duePaymentAmount));
        }
        logger.debug("Number of Due Payments found: " + duePaymentsList.size());
        return duePaymentsList;
    }

    /**
     * Generates a 'Bank Transfer' using only the first element from the list of amounts
     * @param amountsList the list of amounts
     * @return a 'Bank Transfer' which represents a datum from the input file
     */
    private BankTransfer extractBankTransfer(List<BigDecimal> amountsList) {
        BigDecimal bankTransferAmount = amountsList.get(0);
        BankTransfer bankTransfer = new BankTransfer(bankTransferAmount);
        logger.debug("Bank Transfer amount found: " + bankTransfer.getAmount());
        return bankTransfer;
    }

    /**
     * Checking exactly input file path is not null
     * @param args arguments used to start the application
     * @throws IllegalArgumentException if input file path is null
     */
    private void validateFilePath(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("Input file path provided is null");
        }
    }
}