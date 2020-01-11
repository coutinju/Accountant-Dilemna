package com.jcou.accountingdilemma.io.output.inline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.jcou.accountingdilemma.accounting.DuePayment;
import com.jcou.accountingdilemma.accounting.Solution;
import com.jcou.accountingdilemma.io.output.inline.InlineGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Make sure tests are run one after the other
// Otherwise multiple tests try to create/delete the same file
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class InlineGeneratorTest {
    protected static final String OUPUT_FILE_NAME = "output.txt";

    protected static final String NO_SOLUTION = "No Solution";

    private InlineGenerator inlineGenerator;

    @BeforeEach
    public void beforeEach() {
        this.inlineGenerator = new InlineGenerator();
    }

    @Test
    public void testGenerateFile_givenNullThenGenerateNoSolution() throws IOException {
        this.inlineGenerator.generateFile(null);
        Path outputPath = Paths.get(OUPUT_FILE_NAME);
        assertTrue(Files.exists(outputPath));
        Files.delete(outputPath);
    }

    @Test
    public void testGenerateFile_givenValidSolutionThenGenerateInlineOutput() throws IOException {
        List<DuePayment> duePaymentsList = new LinkedList<>();
        duePaymentsList.add(new DuePayment(new BigDecimal("192.43")));
        duePaymentsList.add(new DuePayment(new BigDecimal("83.10")));
        Solution solution = new Solution(duePaymentsList);

        this.inlineGenerator.generateFile(solution);
        Path outputPath = Paths.get(OUPUT_FILE_NAME);
        assertTrue(Files.exists(outputPath));
        assertEquals(Files.lines(outputPath).collect(Collectors.joining()), "192.43 83.10");
        Files.delete(outputPath);
    }
}