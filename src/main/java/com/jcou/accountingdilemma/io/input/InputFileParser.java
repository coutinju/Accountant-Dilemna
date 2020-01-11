package com.jcou.accountingdilemma.io.input;

/**
 * Interface to parse input files
 */
public interface InputFileParser {
    /**
     * Parse the file with the path given as parameter
     * @param filePath file path to parse
     */
    public InputFileData parse(String filePath);
}