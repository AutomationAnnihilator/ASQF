package reporter.jiraXray.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"classpath:csrc/main/resources/XrayConfig.properties"})
public interface XrayConfig extends Config {

  @Key("clientID")
  @DefaultValue("7BB3A70527D14AFEA47C3FBA8A6F9A5C")
  String clientId();

  @Key("password")
  @DefaultValue("c74410f25949360fa0f1b0730f7da8e22664f683078dd29e85a1174a89e5c009")
  String clientSecret();

  @Key("cloudAddress")
  @DefaultValue("xray.cloud.getxray.app")
  String  cloudAddress();

  @Key("projectKey")
  @DefaultValue("ES")
  String projectKey();

  @Key("bearerKey")
  @DefaultValue("Bearer ")
  String bearerKey();

  @Key("graphql")
  @DefaultValue("graphql")
  String graphql();

  @Key("authentication")
  @DefaultValue("authenticate")
  String authenticate();

  @Key("scheme")
  @DefaultValue("HTTPS")
  String scheme();

  @Key("apis")
  @DefaultValue("api")
  String apis();

  @Key("port")
  @DefaultValue("443")
  int port();

  @Key("apisVersion")
  @DefaultValue("v2")
  String apisVersion();

  @Key("core.xray.enable")
  @DefaultValue("true")
  boolean isXrayEnabled();

  @Key("url")
  @DefaultValue("HTTPS://xray.cloud.getxray.app/api/v2/graphql")
  String url();

  @Key("token")
  @DefaultValue("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiI4MTk5YmM2Ni1kMjI1LTMzNWEtOWY4YS03YzNiMjM0MTIwZjUiLCJhY2NvdW50SWQiOiI2MWYyZGRmMGUyMThmMDAwNmEwMWNmY2YiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTY2NTY4MDA3NCwiZXhwIjoxNjY1NzY2NDc0LCJhdWQiOiI3QkIzQTcwNTI3RDE0QUZFQTQ3QzNGQkE4QTZGOUE1QyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IjdCQjNBNzA1MjdEMTRBRkVBNDdDM0ZCQThBNkY5QTVDIn0.SJf9_kwkO6eVqNyQd7xdWwzpPkuIzWBZoSViG2n2XJQ")
  String token();
}
