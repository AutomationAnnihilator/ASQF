package reporter.jiraXray.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreateTest {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String value) {
        this.query = value;
    }


    public void getTestQuery(String summary) {
        setQuery("mutation {\n" +
                "    createTest(\n" +
                "        testType: { name: \"Manual\" },\n" +
                "        jira: {\n" +
                "            fields: { summary:\""+summary+"\", project: {key: \""+ "ES"+"\"} }\n" +
                "        }\n" +
                "    ) {\n" +
                "        test {\n" +
                "            issueId\n" +
                "            testType {\n" +
                "                name\n" +
                "            }\n" +
                "            jira(fields: [\"key\"])\n" +
                "        }\n" +
                "        warnings\n" +
                "    }\n" +
                "}");
    }

    public String getTestId(String body) {
        String testId="";
        body="["+body+"]";
        JSONArray array = new JSONArray(body);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i); // json
            JSONObject data = object.getJSONObject("data"); // get data object
            JSONObject execution = data.getJSONObject("createTest");
            JSONObject testExecution = execution.getJSONObject("test");
            testId= testExecution.getString("issueId");
        }
        return testId;
    }

}
