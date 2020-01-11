package com.jcou.accountingdilemma.io.output.inline;

import java.io.IOException;
import java.nio.file.Files;

import com.jcou.accountingdilemma.accounting.Solution;
import com.jcou.accountingdilemma.io.output.OutputFileGenerator;

import org.apache.log4j.Logger;

/**
 * Generates an output file with a name depending on OUPUT_FILE_PATH
 * The content depends on the solution provided:
 * If the solution is null => NO_SOLUTION variable
 * Otherwise => a single line which each DuePayment in the solution
 *              separated by a space
 */
public class InlineGenerator extends OutputFileGenerator {
    private static final Logger logger = 
        Logger.getLogger(InlineGenerator.class);

    @Override
    public void generateFile(Solution solution) {
        String outputContent = outputContent(solution);
        try {
            logger.debug("Output file destination: " + OUPUT_FILE_PATH.toString());
            Files.write(OUPUT_FILE_PATH, outputContent.getBytes());
            logger.debug("Output file created");
        } catch (IOException e) {
            throw new RuntimeException("Cannot create the output file: " + 
                OUPUT_FILE_PATH.getFileName(), e);
        }
    }

    /**
     * Computes the content to print in the output file
     * @param solution the solution to the acocounting dilemma
     * @return the content to print in the output file
     */
    private String outputContent(Solution solution) {
        if (solution == null) {
            return NO_SOLUTION;
        } else {
            return solution.toString();
        }
    }
}