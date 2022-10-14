package reporter.jiraXray.listeners;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reporter.TestStatus.TestStatus;
import reporter.jiraXray.config.XrayConfigHolder;
import reporter.jiraXray.model.GetTest;
import reporter.jiraXray.model.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class XrayListener {
    protected static final boolean IS_XRAY_ENABLED = XrayConfigHolder.getInstance().isXrayEnabled();
    protected static final Logger LOG = LoggerFactory.getLogger(XrayListener.class);
    private String executionId;
    private ZonedDateTime startDate;
    private static String testRunId;
    private String testId;
    private static final String OPTIONAL = "Optional";

    public static String getTestRunId() {
        return testRunId;
    }

    public static void setTestRunId(String testRunId) {
        XrayListener.testRunId = testRunId;
    }
    protected void createTestExecution(Method testName) {
        if (IS_XRAY_ENABLED) {
            try {
                createExecution(testName);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }

    private void createExecution(Method testName) throws IOException {
        TestExecution query = new TestExecution();
        testId = getJiraTestCaseName(testName);
        query.getSrt(testId, testName.getName());
        startDate = ZonedDateTime.now();
        createTestExecutionWithTestCase(query);
        getTestRun();
    }

    protected void getTestRun() {
        GetTestRun queryUrl = new GetTestRun();
        queryUrl.getTestRun(testId, executionId);
        RestAssured.baseURI = XrayConfigHolder.getInstance().url();
        Response response= given().log().all().
                contentType("application/json").
                header("Authorization", XrayConfigHolder.getInstance().token())
                .body(queryUrl).
                when().log().all().
                get().then().log().all().assertThat().statusCode(200).extract().response();

        String body = String.valueOf(response.getBody());
        body = body.replaceFirst(OPTIONAL, "");
        GetTestRun run = new GetTestRun();
        run.getTestId(body);
    }

    protected boolean getTestQuery(String testcaseId, String summary) throws IOException {
        GetTest query = new GetTest();
        query.getTest(testcaseId);


        OkHttpClient client = new OkHttpClient();
        Request request =
                new Request.Builder()
                        .url(XrayConfigHolder.getInstance().url()+"?query="+query.getQuery()).addHeader("Authorization",XrayConfigHolder.getInstance().token())
                        .addHeader("Content-Type","application/json")
                        .addHeader("Accept", "*/*")
                        .get()
                        .build();
        okhttp3.Response response = client.newCall(request).execute();

        String responseBody = response.body().toString();
        responseBody = responseBody.replaceFirst(OPTIONAL, "");
        GetTest test = new GetTest();
        return test.getTestIdAndSummary(responseBody, summary);
    }

    protected static String createTest(Method method) {
        CreateTest test = new CreateTest();
        test.getTestQuery(method.getName());
        try {
            return createTestCase(test);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected static String createTestCase(CreateTest executionBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request =
                new Request.Builder()
                        .url(XrayConfigHolder.getInstance().url()+"?query="+executionBody.getQuery()).addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiI4MTk5YmM2Ni1kMjI1LTMzNWEtOWY4YS03YzNiMjM0MTIwZjUiLCJhY2NvdW50SWQiOiI2MWYyZGRmMGUyMThmMDAwNmEwMWNmY2YiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTY2NTY4MDA3NCwiZXhwIjoxNjY1NzY2NDc0LCJhdWQiOiI3QkIzQTcwNTI3RDE0QUZFQTQ3QzNGQkE4QTZGOUE1QyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IjdCQjNBNzA1MjdEMTRBRkVBNDdDM0ZCQThBNkY5QTVDIn0.SJf9_kwkO6eVqNyQd7xdWwzpPkuIzWBZoSViG2n2XJQ")
                        .addHeader("Content-Type","application/json")
                        .addHeader("Accept", "*/*").get()
                        .post(RequestBody.create(executionBody.getQuery(),MediaType.parse("text/plain")))
                        .build();
        okhttp3.Response response = client.newCall(request).execute();

        try {
            String body = response.body().string();
            body = body.replaceFirst(OPTIONAL, "");
            CreateTest query = new CreateTest();
            return query.getTestId(body);

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    protected void updateTestWithStatus(TestStatus testStatus) {
        if (IS_XRAY_ENABLED) {
            Updatestatus status = new Updatestatus();
            String getStatus = String.valueOf(XrayTestStatus.translateInternalStatusToXray(testStatus));
            status.updateTest(getTestRunId(), getStatus);
            try {
                updateTestCase(status);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void finishExecution(ZonedDateTime endDate) throws IOException {
        if (IS_XRAY_ENABLED) {
            UpdateTestRun updateTest = new UpdateTestRun();
            String startTime = String.valueOf(startDate);
            int s = startTime.lastIndexOf('.');
            String sTime = startTime.substring(0, s);
            String endTime = String.valueOf(endDate);
            String eTime = endTime.substring(0, s);
            updateTest.getTestQuery(getTestRunId(), sTime, eTime);
            finishTestCase(updateTest);

        }
    }

    protected void updateTestCase(Updatestatus xrayStatus) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request =
                new Request.Builder()
                        .url(XrayConfigHolder.getInstance().url()+"?query="+xrayStatus.getQuery()).addHeader("Authorization",XrayConfigHolder.getInstance().token())
                        .addHeader("Content-Type","application/json")
                        .addHeader("Accept", "*/*").get()
                        .post(RequestBody.create(xrayStatus.getQuery(),MediaType.parse("text/plain")))
                        .build();
        okhttp3.Response response = client.newCall(request).execute();
    }

    protected void createTestExecutionWithTestCase(TestExecution executionBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request =
                new Request.Builder()
                        .url(XrayConfigHolder.getInstance().url()+"?query="+executionBody.getQuery()).addHeader("Authorization",XrayConfigHolder.getInstance().token())
                        .addHeader("Content-Type","application/json")
                        .addHeader("Accept", "*/*").get()
                        .post(RequestBody.create(executionBody.getQuery(),MediaType.parse("text/plain")))
                        .build();
        okhttp3.Response response = client.newCall(request).execute();


        try {
            String body = response.body().toString();
            body = body.replaceFirst(OPTIONAL, "");
            TestExecution query = new TestExecution();
            executionId = query.getExecutionId(body);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void finishTestCase(UpdateTestRun executionBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request =
                new Request.Builder()
                        .url(XrayConfigHolder.getInstance().url()+"?query="+executionBody.getQuery()).addHeader("Authorization",XrayConfigHolder.getInstance().token())
                        .addHeader("Content-Type","application/json")
                        .addHeader("Accept", "*/*").get()
                        .post(RequestBody.create(executionBody.getQuery(),MediaType.parse("text/plain")))
                        .build();
        okhttp3.Response response = client.newCall(request).execute();
    }

    private String getJiraTestCaseName(Method method) {
        ReportingMetaData xrayAnnotation = method.getAnnotation(ReportingMetaData.class);

        if (xrayAnnotation != null) {
            boolean testCaseIsPresent = false;
            try {
                testCaseIsPresent = getTestQuery(xrayAnnotation.issueId(), method.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (testCaseIsPresent) {
                testId = xrayAnnotation.issueId();
            } else {
                testId = createTest(method);
            }

        } else {
            testId = createTest(method);
        }
        return testId;
    }

}



