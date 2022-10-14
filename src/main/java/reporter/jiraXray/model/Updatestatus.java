package reporter.jiraXray.model;

public class Updatestatus {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String value) {
        this.query = value;
    }


    public void updateTest(String testRunId,String testStatus) {

        setQuery("mutation {\n" +
                "    updateTestRunStatus( id: \""+testRunId+"\", status: \""+testStatus+"\")\n" +
                "}");
    }
}
