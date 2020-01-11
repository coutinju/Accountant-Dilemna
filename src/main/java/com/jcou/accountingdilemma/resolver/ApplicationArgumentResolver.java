package com.jcou.accountingdilemma.resolver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationArgumentResolver {
    private static Logger logger = 
            Logger.getLogger(ApplicationArgumentResolver.class.getName());
    
    private static final String ERROR_MESSAGE =
        "Exactly one argument is expected. Please provide the file to parse.";

    /**
     * Checking the arguments are valid
     * Parsing the arguments to return the input file path
     * @param args arguments used to launch the application
     * @return input file path
     */
    public static String retrieveInputFilePath(String[] args) {
        logger.info("===Retrieving input file path===");
        validateArguments(args);
        String filePath = args[0];

        logger.log(Level.FINEST, 
            "Input file path retrieved from arguments: {0}",
            new Object[]{filePath});

        return filePath;
    }

    /**
     * Checking exactly one argument is present
     * @param args arguments used to start the application
     * @throws IllegalArgumentException if there is not exactly one argument
     */
    private static void validateArguments(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }
}