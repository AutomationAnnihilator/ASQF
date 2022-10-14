package reporter.TestStatus;

import org.testng.ITestResult;

public enum TestStatus {
    PASS,
    INFO,
    SKIP,
    FAIL,
    ERROR;

    public static TestStatus parseTestNgStatus(ITestResult testResult) {
        switch (testResult.getStatus()) {
            case ITestResult.STARTED:
            case ITestResult.CREATED:
                return INFO;
            case ITestResult.SUCCESS:
                return PASS;
            case ITestResult.SKIP:
                return SKIP;
            case ITestResult.FAILURE:
            case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
            default:
                return FAIL;
        }
    }
}