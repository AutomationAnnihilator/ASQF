package reporter.jiraXray.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetTestRun {
    private String query;

    public String getQuery() { return query; }
    public void setQuery(String value) { this.query = value; }

   public void getTestRun(String testId, String testExecutionId){
        setQuery("{ getTestRun( testIssueId: \""+testId+"\", testExecIssueId: \""+testExecutionId+"\") { id status { name color description } gherkin examples { id status { name color description } } }}");
   }

  public String getTestId(String responseBody){
        String testId="";
      JSONArray array = new JSONArray(responseBody);
      for (int i = 0; i < array.length(); i++) {
          JSONObject object = array.getJSONObject(i); // json
          JSONObject data = object.getJSONObject("data"); // get data object
          JSONObject execution = data.getJSONObject("getTestRun");
          testId= execution.getString("id");
      }
      return testId;
  }
}
