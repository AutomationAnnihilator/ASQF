package reporter.jiraXray.model;

import reporter.TestStatus.TestStatus;

public enum XrayTestStatus {
  EXECUTING,
  PASSED,
  FAILED,
  TODO,
  ABORTED;

  public static XrayTestStatus translateInternalStatusToXray(TestStatus testStatus) {
    switch (testStatus) {
      case PASS:
        return PASSED;
      case FAIL:
        return FAILED;
      case INFO:
        return EXECUTING;
      case SKIP:
        return TODO;
      case ERROR:
      default:
        return ABORTED;
    }
  }
}
