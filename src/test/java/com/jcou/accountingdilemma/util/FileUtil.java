package com.jcou.accountingdilemma.util;

import java.io.File;
import java.net.URL;

/**
 * Util which contains utility method common to all tests
 */
public class FileUtil {
    /**
     * Generate an absolute path to a resource from its relative path (resources)
     * @param resourcePath path to the resource relative to resources directory
     * @return an absolute path to the resource
     */
    public static String generateTestFilePath(String resourcePath) {
        URL testFileUrl = FileUtil.class.getResource(resourcePath);
        File testFile = new File(testFileUrl.getPath());
        String testFileAbsolutePath = testFile.getAbsolutePath();
        return testFileAbsolutePath;
    }
}