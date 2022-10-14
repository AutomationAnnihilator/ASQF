package reporter.jiraXray.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetTest {
    private String query;

    public String getQuery() { return query; }
    public void setQuery(String value) { this.query = value; }

    public void getTest(String testid){
        setQuery("{ getTest(issueId: \""+testid+"\") { issueId testType { name kind } jira(fields: [\"key\",\"summary\"]) }}");
    }

    public boolean getTestIdAndSummary(String body,String summary) {
        String testId="";
        body="["+body+"]";
        JSONArray array = new JSONArray(body);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i); // json
            JSONObject data = object.getJSONObject("data"); // get data object
            JSONObject execution = data.getJSONObject("getTest");
            JSONObject testExecution = execution.getJSONObject("jira");
            testId = testExecution.getString("summary");
        }
            return summary.equals(testId);

    }
}
