package com.jcou.accountingdilemma.io.input;

/**
 * Interface to parse input files
 */
public abstract class InputFileParser {
    /**
     * Parse the file with the path given as parameter
     * @param filePath file path to parse
     */
    public abstract InputFileData parse(String filePath);
}