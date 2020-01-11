package com.jcou.accountingdilemma.io.output;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.jcou.accountingdilemma.accounting.Solution;

public abstract class OutputFileGenerator {
    protected static final Path OUPUT_FILE_PATH = Paths.get("output.txt");

    public abstract void generateFile(Solution solution);
}