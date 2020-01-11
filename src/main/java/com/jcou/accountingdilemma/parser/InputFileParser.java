package com.jcou.accountingdilemma.parser;

/**
 * Interface to parse input files
 */
public interface InputFileParser {
    /**
     * Parse the file with the path given as parameter
     * @param filePath file path to parse
     */
    public void parse(String filePath);
}