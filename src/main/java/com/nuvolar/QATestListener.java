package com.nuvolar;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;


public class QATestListener extends TestListenerAdapter implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(QATestListener.class);

    public static final String TEST_MESSAGE = "Test '";
    public static final String TEST_USER_ID = "testUserId";

    @Override
    public void onTestStart(ITestResult tr) {
        logger.info("Test Started: {}", tr.getName());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.info(TEST_MESSAGE + "{}' PASSED", tr.getName());
        logger.info("Class Name: {}", tr.getTestClass().getName());
        logger.info("Priority of this method is {}", tr.getMethod().getPriority());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger.info(TEST_MESSAGE + "{}' FAILED", tr.getName());
        logger.info("Priority of this method is {}", tr.getMethod().getPriority());
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.info(TEST_MESSAGE + "{}' SKIPPED", tr.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestNGMethod[] methods = context.getAllTestMethods();
        for (ITestNGMethod method : methods) {
            StringBuilder errors = new StringBuilder();
            String message = "";
            int nrFailures;
            int nrPassed;
            ITestResult trPrevious = null;

            IResultMap rMapFailed = context.getFailedTests();
            IResultMap rMapPassed = context.getPassedTests();

            nrFailures = rMapFailed.getResults(method).size();
            nrPassed = context.getPassedTests().getResults(method).size();
            message += "Number of failures: " + nrFailures + " of total executions " + (nrFailures + nrPassed) + " for method " + method.getMethodName() + "";

            logger.info(message);
            errors.append(message);

            String className = "";
            for (ITestResult tr : rMapFailed.getResults(method)) {
                if (trPrevious != null && trPrevious.compareTo(tr) == 0) {
                    break; // we won't insert repeated test results
                }
                errors.append(tr.getTestContext().getAttribute(TEST_USER_ID) != null ? tr.getTestContext().getAttribute(TEST_USER_ID) : "")
                        .append(" " + tr.getName() + " ")
                        .append(tr.getThrowable().getMessage() + "\n");

                errors = getStringBuilder(errors, tr);
                trPrevious = tr;
                className = tr.getTestClass().getName();
            }
            className = getClassName(method, nrFailures, rMapPassed, className);
        }
    }

    private StringBuilder getStringBuilder(StringBuilder errors, ITestResult tr) {
        StackTraceElement[] stack = tr.getThrowable().getStackTrace();
        for (StackTraceElement stackTraceElement : stack) {
            String stackElement = stackTraceElement.toString();
            if (stackElement.contains("com.nuvolar")) {
                errors = new StringBuilder(errors.toString().concat(stackElement + "\n"));
            }
        }
        return errors;
    }

    private String getClassName(ITestNGMethod method, int nrFailures, IResultMap rMapPassed, String className) {
        if (nrFailures == 0) {
            for (ITestResult tr : rMapPassed.getResults(method)) {
                className = tr.getTestClass().getName();
            }
        }
        return className;
    }
}