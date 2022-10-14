package reporter.slack.config;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"classpath:src/main/resources/SlackConfig.properties"})
public interface SlackConfig extends Config {

    @Key("slack.token")
    @DefaultValue("Bearer xoxb-4207247658215-4215198371750-oTpJzfk9kP7ooggaq9Ecgct2")
    String slackToken();

    @Key("slack.channel.id")
    @DefaultValue("C046B5M8CT0")
    String slackChannelId();
    
    @Key("slack.enable")
    @DefaultValue("true")
    boolean enableSlackReporting();

    @Key("slack.detail.tc.count")
    @DefaultValue("8")
    int detailTestCaseCount();
}