package com.jcou.accountingdilemma;

import com.jcou.accountingdilemma.io.input.ArgumentsResolver;
import com.jcou.accountingdilemma.io.input.InputFileData;
import com.jcou.accountingdilemma.io.input.InputFileParser;
import com.jcou.accountingdilemma.io.input.singlecolumn.SingleColumnArgumentsResolver;
import com.jcou.accountingdilemma.io.input.singlecolumn.SingleColumnParser;

import org.apache.log4j.Logger;

/**
 * Entry point of the program only one argument is expected: 
 * the input file path.
 */
public class AccountingDilemmaApplication {
    private static final Logger logger = 
        Logger.getLogger(AccountingDilemmaApplication.class);

    /**
     * Method called when the application is run
     * Expect as unique argument: file input path
     * Exit with error status code 1 if any exception is raised
     * @param args arguments used to start the application
     */
    public static void main(String[] args) {
        try {
            logger.info("Accounting Dilemma Application started");
            findSolution(args);
            logger.info("Accounting Dilemma Application ended normally");
        } catch(Exception e) {
            logger.error(e.getMessage());
            logger.info("Accounting Dilemma Application ended unexpectedly");
            System.exit(1);
        }
    }

    /**
     * Fetch the input file
     * Parses the data inside the file to generate an InputFileData
     * Find a solution
     * Generate an output file containing the solution
     */
    private static void findSolution(String[] args) {
        ArgumentsResolver argumentResolver = new SingleColumnArgumentsResolver();
        String filePath = argumentResolver.retrieveInputFilePath(args);
        InputFileParser inputFileParser = new SingleColumnParser();
        InputFileData inputFileData = inputFileParser.parse(filePath);
    }
}