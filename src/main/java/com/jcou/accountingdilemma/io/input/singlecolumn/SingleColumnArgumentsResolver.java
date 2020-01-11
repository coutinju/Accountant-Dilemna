package com.jcou.accountingdilemma.io.input.singlecolumn;

import com.jcou.accountingdilemma.io.input.ArgumentsResolver;

import org.apache.log4j.Logger;

public class SingleColumnArgumentsResolver implements ArgumentsResolver {
    private static final Logger logger = 
        Logger.getLogger(SingleColumnArgumentsResolver.class);

    /**
     * Checking the arguments are valid
     * Parsing the arguments to return the input file path
     * @param args arguments used to launch the application
     * @return input file path
     */
    @Override
    public String retrieveInputFilePath(String[] args) {
        logger.debug("Retrieving input file path");
        validateArguments(args);
        String filePath = args[0];

        logger.debug( "Input file path retrieved from arguments: " + filePath);

        return filePath;
    }

    /**
     * Checking exactly one argument is present
     * @param args arguments used to start the application
     * @throws IllegalArgumentException if there is not exactly one argument
     */
    private static void validateArguments(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException(
                "Exactly one argument is expected. Please provide the file to parse.");
        }
    }
}