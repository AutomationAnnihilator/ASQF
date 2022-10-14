package reporter.jiraXray.config;

import org.aeonbits.owner.ConfigFactory;

public class XrayConfigHolder {

  private XrayConfigHolder(){}

  private static final XrayConfig INSTANCE = ConfigFactory.create(XrayConfig.class);

  public static XrayConfig getInstance() {
    return INSTANCE;
  }
}
