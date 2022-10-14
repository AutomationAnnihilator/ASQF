package reporter.slack.config;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"classpath:src/main/resources/SlackConfig.properties"})
public interface SlackConfig extends Config {

    @Key("slack.token")
    @DefaultValue("Bearer xoxb-4216721181842-4229674120881-TH0arlAuTwYgf8CpqlghUr6V")
    String slackToken();

    @Key("slack.channel.id")
    @DefaultValue("C046D1NA2AE")
    String slackChannelId();
    
    @Key("slack.enable")
    @DefaultValue("true")
    boolean enableSlackReporting();

    @Key("slack.detail.tc.count")
    @DefaultValue("8")
    int detailTestCaseCount();
}