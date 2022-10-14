package reporter.jiraXray.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporter.TestStatus.TestStatus;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestNgListener extends XrayListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
    }
    @Override
    public void onTestStart(ITestResult testResult) {
        createTestExecution(testResult.getMethod().getConstructorOrMethod().getMethod());
    }

    @Override
    public void onTestSuccess(ITestResult testResult){
        updateTestWithStatus(TestStatus.parseTestNgStatus(testResult));
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        updateTestWithStatus(
                TestStatus.parseTestNgStatus(testResult));
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        updateTestWithStatus(
                TestStatus.parseTestNgStatus(testResult));
    }
    @Override
    public void onFinish(ITestContext context) {
        try {
            finishExecution(
                    ZonedDateTime.ofInstant(context.getEndDate().toInstant(), ZoneId.systemDefault()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
