package reporter.slack.listeners;

import org.jetbrains.annotations.NotNull;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TestNgListener extends SlackListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult testResult) {
        reportTest(testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        reportTest(testResult);
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        reportTest(testResult);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        updateSlackMessage(
                getAsZonedDateTime(testContext.getStartDate()),
                getAsZonedDateTime(testContext.getEndDate()));
    }

    @NotNull
    private ZonedDateTime getAsZonedDateTime(Date date) {
        return ZonedDateTime.of(
                LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), ZoneId.systemDefault());
    }
}
