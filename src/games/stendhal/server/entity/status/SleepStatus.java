package games.stendhal.server.entity.status;
/** 
 * Stub for SleepStatus.
 */
public class SleepStatus extends Status {
	
	public SleepStatus() {
		super("sleep");
	}
	
	@Override
	public StatusType getStatusType() {
		return StatusType.SLEEPING;
	}


}
