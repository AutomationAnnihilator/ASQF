package reporter.slack.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import reporter.TestStatus.TestStatus;
import reporter.slack.config.SlackConfigHolder;

import static reporter.TestStatus.TestStatus.*;


public abstract class SlackListener {

    private static final String SLACK_URL_UPLOAD = "https://slack.com/api/files.upload";
    private static final String SLACK_URL_MESSAGE = "https://slack.com/api/chat.postMessage";
    private static final String SLACK_TOKEN = SlackConfigHolder.getInstance().slackToken();
    private static final String SLACK_CHANNEL_ID = SlackConfigHolder.getInstance().slackChannelId();
    private static final int MAX_TC_COUNT = SlackConfigHolder.getInstance().detailTestCaseCount();
    private static final boolean ENABLE_SLACK_REPORTING = SlackConfigHolder.getInstance().enableSlackReporting();
    private static final Logger LOG = LoggerFactory.getLogger(SlackListener.class);
    protected static int pass = 0;
    protected static int fail = 0;
    protected static int skip = 0;
    protected static final StringBuilder slackMessage = new StringBuilder();

    protected SlackListener() {
        // nothing to do here
    }

    protected synchronized void reportTest(
            TestStatus testStatus, String className, String methodName) {
        if (ENABLE_SLACK_REPORTING) {
            int tcCount = pass + fail + skip;
            boolean enableDetailReporting = tcCount <= MAX_TC_COUNT;
            switch (testStatus) {
                case PASS:
                    if (enableDetailReporting) {
                        slackMessage.append(":heavy_check_mark: PASSED   :  ");
                    }
                    pass++;
                    break;
                case FAIL:
                    if (enableDetailReporting) {
                        slackMessage.append(":x: FAILED   :   ");
                    }
                    fail++;
                    break;
                case SKIP:
                    if (enableDetailReporting) {
                        slackMessage.append(":fast_forward: SKIPPED  :  ");
                    }
                    skip++;
                    break;
                default:
                    break;
            }
            if (enableDetailReporting) {
                slackMessage.append(className).append(".").append(methodName).append("\r\n");
            } else {
                slackMessage.append("................ \r\n ................ \r\n");
            }
        }
    }

    protected void reportTest(ITestResult testResult) {
        if (ENABLE_SLACK_REPORTING) {
            reportTest(
                    parseTestNgStatus(testResult),
                    testResult.getTestClass().getName(),
                    testResult.getMethod().getMethodName());
        }
    }

    protected void updateSlackMessage(ZonedDateTime startTime, ZonedDateTime endTime) {
        if (ENABLE_SLACK_REPORTING) {
            long timeDifference = Duration.between(endTime, startTime).toMillis();
            var executionTime =
                    String.format(
                            "%02dhrs:%02dmins:%02dsecs:%02dms",
                            TimeUnit.MILLISECONDS.toHours(timeDifference),
                            TimeUnit.MILLISECONDS.toMinutes(timeDifference) % TimeUnit.HOURS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(timeDifference) % TimeUnit.MINUTES.toSeconds(1),
                            timeDifference % TimeUnit.SECONDS.toMillis(1));
            //toString().replace()
            slackMessage
                    .append("\r\nExecution Results \r\nTotal Test Cases Executed  :      *`")
                    .append((pass + fail + skip))
                    .append("`*\r\n:x: Test Cases Failed          :      *`")
                    .append(fail)
                    .append("`*\r\n:fast_forward: Test Cases Skipped      :      *`")
                    .append(skip)
                    .append("`*\r\n:white_check_mark: Test Cases Passed        :      *`")
                    .append(pass)
                    .append("`*\r\n:triangular_flag_on_post: Test Cases started        :      *`")
                    .append(startTime)
                    .append("`*\r\n:checkered_flag: Test Cases finished       :      *`")
                    .append(endTime)
                    .append("`*\r\n:stopwatch: Total Time Taken           :      *`")
                    .append(executionTime)
                    .append("`*");
            attachReportToMessage(slackMessage.toString());
        }
    }


    private void attachReportToMessage(String messageBody) {
        try {
            var reportZip =
                    Paths.get(System.getProperty("user.dir"))
                            .resolve("ZippedReport")
                            .resolve("report.zip")
                            .toFile();
            if (reportZip.isFile() && reportZip.canRead()) {
                sendSlackMessageWithReport(messageBody, reportZip);
            } else {
                sendSlackMessageWithoutReport(messageBody.replace("\r\n","\n"));
            }
        } catch (Exception e) {
            LOG.error(String.format("Unable to send message to slack due to error : %s", e));
        }
    }

    private void sendSlackMessageWithReport(String messageBody, File reportZip) throws IOException {
        var client = new OkHttpClient();
        var urlBuilder = Objects.requireNonNull(HttpUrl.parse(SLACK_URL_UPLOAD)).newBuilder();
        var mediaType = MediaType.parse("application/zip");
        RequestBody requestBody =
                new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", reportZip.getName(), RequestBody.create(reportZip,mediaType))
                        .addFormDataPart("initial_comment", messageBody)
                        .addFormDataPart("channels", SLACK_CHANNEL_ID)
                        .build();
        var request =
                new Request.Builder().url(urlBuilder.build().toString())
                        .addHeader("Content-Type", "multipart/form-data")
                        .addHeader("Authorization", SLACK_TOKEN)
                        .post(requestBody).build();
        var response = client.newCall(request).execute();
        LOG.info("Execution details sent to slack successfully. {}", response.code());
    }

    private void sendSlackMessageWithoutReport(String messageBody) {
        if (ENABLE_SLACK_REPORTING) {
            try {
                String body = "{\n" +
                        "    \"channel\": \""+SLACK_CHANNEL_ID+"\",\n" +
                        "    \"text\": \""+messageBody+"\"\n" +
                        "}";

                OkHttpClient client = new OkHttpClient();
                Request request =
                        new Request.Builder()
                                .url(SLACK_URL_MESSAGE).addHeader("Authorization",SLACK_TOKEN)
                                .post(RequestBody.create(body, MediaType.parse("application/json")))
                                .build();
                Response response = client.newCall(request).execute();
                LOG.info("Execution details sent to teams successfully. {}", response.code());
                System.out.printf("Slack message sent!!!");
            } catch (IOException ex) {
                LOG.info("Execution details not sent to teams successfully due to following error.", ex);
            }
        }
    }
}

