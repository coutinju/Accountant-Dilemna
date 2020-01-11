package com.jcou.accountingdilemma.accounting.strategy;

import com.jcou.accountingdilemma.accounting.Solution;
import com.jcou.accountingdilemma.io.input.InputFileData;

public interface Strategy {
    Solution findSolution(InputFileData inputFileData);
}