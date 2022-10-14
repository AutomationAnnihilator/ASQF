package reporter.slack.config;


import org.aeonbits.owner.ConfigFactory;

/**
 * This class is used to create the instance of the SlackConfig.
 * 
 * @author nagarro
 */
public final class SlackConfigHolder {
	
    /* The instance of SlackConfig */
	private static final SlackConfig INSTANCE = ConfigFactory.create(SlackConfig.class);

	/**
	 * Gets the instance of SlackConfig.
	 * 
	 * @return the instance of SlackConfig.
	 */
	public static SlackConfig getInstance() {
		return INSTANCE;
	}
	
	private SlackConfigHolder() {
		//not called
	}
}
