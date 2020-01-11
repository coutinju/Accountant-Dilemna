package com.jcou.accountingdilemma;

import com.jcou.accountingdilemma.accounting.Solution;
import com.jcou.accountingdilemma.accounting.strategy.recursive.RecursiveStrategy;
import com.jcou.accountingdilemma.io.input.ArgumentsResolver;
import com.jcou.accountingdilemma.io.input.InputFileData;
import com.jcou.accountingdilemma.io.input.singlecolumn.SingleColumnArgumentsResolver;
import com.jcou.accountingdilemma.io.input.singlecolumn.SingleColumnParser;
import com.jcou.accountingdilemma.io.output.inline.InlineGenerator;

import org.apache.log4j.Level;
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
            solveAccountingDilemma(args);
            logger.info("Accounting Dilemma Application ended normally");
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (Logger.getRootLogger().getLevel().isGreaterOrEqual(Level.ERROR)) {
                e.printStackTrace();
            }
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
    private static void solveAccountingDilemma(String[] args) {
        ArgumentsResolver argumentResolver = new SingleColumnArgumentsResolver();
        String filePath = argumentResolver.retrieveInputFilePath(args);
        InputFileData inputFileData = new SingleColumnParser().parse(filePath);
        Solution solution = new RecursiveStrategy().findSolution(inputFileData);
        new InlineGenerator().generateFile(solution);
    }
}