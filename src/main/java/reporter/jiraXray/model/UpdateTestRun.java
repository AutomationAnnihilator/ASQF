package reporter.jiraXray.model;

public class UpdateTestRun {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String value) {
        this.query = value;
    }

    public void getTestQuery(String testId,String startTime, String endTime){
        setQuery("mutation {\n" +
                "    updateTestRun( id: \""+testId+"\", comment: \"Everything is OK.\", startedOn: \""+startTime+"Z"+"\", finishedOn: \""+endTime+"Z"+"\") {\n" +
                "        warnings\n" +
                "    }\n" +
                "}");
    }
}
