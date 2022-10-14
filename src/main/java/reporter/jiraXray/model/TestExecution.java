package reporter.jiraXray.model;

import org.json.JSONArray;
import org.json.JSONObject;
import reporter.jiraXray.config.XrayConfigHolder;

public class TestExecution {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String value) {
        this.query = value;
    }


    public void getSrt(String issueId, String summary) {
        setQuery("mutation {\n" +
                "    createTestExecution(\n" +
                "        testIssueIds: [\"" + issueId + "\"]\n" +
                "        jira: {\n" +
                "            fields: { summary: \"" + summary + " \", \n" +
                "            project: {key: \"" + XrayConfigHolder.getInstance().projectKey() + "\"} }\n" +
                "        }\n" +
                "    ) {\n" +
                "        testExecution {\n" +
                "            issueId\n" +
                "            jira(fields: [\"key\"])\n" +
                "        }\n" +
                "        warnings\n" +
                "        createdTestEnvironments\n" +
                "    }\n" +
                "}");
    }

    public String getExecutionId(String body) {
         String executionId="";
         body="["+body+"]";
        JSONArray array = new JSONArray(body);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i); // json
            JSONObject data = object.getJSONObject("data"); // get data object
            JSONObject execution = data.getJSONObject("createTestExecution");
            JSONObject testExecution = execution.getJSONObject("testExecution");
            executionId= testExecution.getString("issueId");
        }
        return executionId;
    }

}
