package com.jcou.accountingdilemma.io.output.inline;

import java.io.IOException;
import java.nio.file.Files;

import com.jcou.accountingdilemma.accounting.Solution;
import com.jcou.accountingdilemma.io.output.OutputFileGenerator;

public class InlineGenerator extends OutputFileGenerator {
    @Override
    public void generateFile(Solution solution) {
        try {
            Files.write(OUPUT_FILE_PATH, solution.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cannot create the output file: " + 
                OUPUT_FILE_PATH.getFileName());
        }
    }
}