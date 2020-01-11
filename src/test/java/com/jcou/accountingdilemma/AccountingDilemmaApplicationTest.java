package com.jcou.accountingdilemma;

import java.security.Permission;

import com.jcou.accountingdilemma.util.FileUtil;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountingDilemmaApplicationTest {
     private static SecurityManager orignalSecurityManager;

     @BeforeAll
     public static void setUp() {
          orignalSecurityManager = System.getSecurityManager();
          System.setSecurityManager(new NoExitSecurityManager());
     }

     @AfterAll
     public static void tearDown() {
          System.setSecurityManager(orignalSecurityManager);
     }

     @Test
     public void testMain_givenValidFilePathThenFinishWithoutError() {
          String defaultTestFileAbsolutePath = 
               FileUtil.generateTestFilePath("/default-test-file.txt");
          AccountingDilemmaApplication.main(new String[]{defaultTestFileAbsolutePath});
          // No exception should be raised
     }

     @Test
     public void testMain_givenTooMuchArgumentsThenThrowIllegalArgumentException() {
          try {
               AccountingDilemmaApplication.main(new String[]{"Too","Much","Arguments"});
          } catch (ExitException e) {
               assertEquals(1, e.status);
          }
     }

     @Test
     public void testMain_givenNoArgumentsThenThrowIllegalArgumentException() {
          try {
               AccountingDilemmaApplication.main(new String[]{});
          } catch (ExitException e) {
               assertEquals(1, e.status);
          }
     }

     /**
      * Custom exception thrown in custom ExitManager to test exit status
      */
     protected static class ExitException extends SecurityException {
          private static final long serialVersionUID = -530544431493849539L;
          public final int status;
          public ExitException(int status) {
             this.status = status;
         }
     }
 
     /**
      * Custom Security Manager to throw a custom exception in case of System.exit
      */
     private static class NoExitSecurityManager extends SecurityManager {
          @Override
          public void checkPermission(Permission perm) {
               // allow anything otherwise AccessControlException is thrown
          }
          @Override
          public void checkPermission(Permission perm, Object context) {
               // allow anything otherwise AccessControlException is thrown
          }
          @Override
          public void checkExit(int status) {
               super.checkExit(status);
               throw new ExitException(status);
          }
     }
 
}