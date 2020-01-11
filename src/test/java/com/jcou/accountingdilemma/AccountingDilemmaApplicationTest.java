package com.jcou.accountingdilemma;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AccountingDilemmaApplicationTest {
     @Test
     public void testMain_givenValidFilePathShouldFinishWithoutError() {
          Path defaultTestFilePath = Paths.get("src","test","resources","default-text-file.txt");
          String defaultTestFileAbsolutePath = defaultTestFilePath.toFile().getAbsolutePath();
          
          AccountingDilemmaApplication.main(new String[]{defaultTestFileAbsolutePath});
     }

     @Test
     public void testMain_givenTooMuchArgumentsShouldThrowIllegalArgumentException() {
          assertThrows(IllegalArgumentException.class, () -> {
               AccountingDilemmaApplication.main(new String[]{"Too","Much","Arguments"});
          });
     }

     @Test
     public void testMain_givenNoArgumentsShouldThrowIllegalArgumentException() {
          assertThrows(IllegalArgumentException.class, () -> {
               AccountingDilemmaApplication.main(new String[]{});
          });
     }
}