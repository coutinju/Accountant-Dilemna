package com.jcou.accountingdilemma.io.input.singlecolumn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

public class SingleColumnArgumentResolverTest {
    private SingleColumnArgumentsResolver argsResolver;

    @BeforeEach
    public void beforEach() {
        this.argsResolver = new SingleColumnArgumentsResolver();
    }

    @Test
    public void testRetrieveInputFilePath_givenNullThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.argsResolver.retrieveInputFilePath(null);
        });
    }

    @Test
    public void testRetrieveInputFilePath_givenEmptyArgsThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.argsResolver.retrieveInputFilePath(new String[]{});
        });
    }

    @Test
    public void testRetrieveInputFilePath_givenTooMuchArgsThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.argsResolver.retrieveInputFilePath(new String[]{"too","much"});
        });
    }

    @Test
    public void testRetrieveInputFilePath_givenValidArgsThenThrowIllegalArgumentException() {
        String arg = "valid/path";
        String filePath = this.argsResolver.retrieveInputFilePath(new String[]{arg});
        assertEquals(arg,filePath);
    }
}