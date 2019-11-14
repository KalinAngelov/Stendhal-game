package games.stendhal.server.entity.status;
/**
 * A status effect that causes the entity to stop in place 
 * and recover until awoken
 *
 * @author Nikolay Avramov
 *
 */
public class SleepStatus extends Status {
	/**
	 * Create the status
	 */
	public SleepStatus() {
		super("sleep");
	}
	
	/**
	 * returns the status type
	 *
	 * @return StatusType
	 */
	@Override
	public StatusType getStatusType() {
		return StatusType.SLEEPING;
	}


}
