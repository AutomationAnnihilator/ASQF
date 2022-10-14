package reporter.slack.config;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"classpath:src/main/resources/SlackConfig.properties"})
public interface SlackConfig extends Config {

    @Key("slack.token")
    @DefaultValue("Bearer xoxb-3535958005522-3561947992358-f3U7yCstzIZvgsSyX58d2X8Q")
    String slackToken();

    @Key("slack.channel.id")
    @DefaultValue("C03GLQ7FDJL")
    String slackChannelId();
    
    @Key("slack.enable")
    @DefaultValue("true")
    boolean enableSlackReporting();

    @Key("slack.detail.tc.count")
    @DefaultValue("8")
    int detailTestCaseCount();
}