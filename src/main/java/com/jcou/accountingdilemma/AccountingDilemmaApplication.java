package com.jcou.accountingdilemma;

import java.util.logging.Logger;

import com.jcou.accountingdilemma.resolver.ApplicationArgumentResolver;

/**
 * Entry point of the program Only one argument is expected as argument: 
 * the input file path.
 */
public class AccountingDilemmaApplication {
    private static Logger logger = 
            Logger.getLogger(AccountingDilemmaApplication.class.getName());

    public static void main(String[] args) {
        logger.info("===Application started===");
        String filePath = ApplicationArgumentResolver.retrieveInputFilePath(args);
        
    }
}