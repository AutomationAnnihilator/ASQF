package reporter.jiraXray.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ReportingMetaData {
    String issueId() default "";;
    String testCaseId() default "";
}