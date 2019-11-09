package games.stendhal.server.entity.status;

import games.stendhal.server.entity.Entity;
/** 
 * Stub for SleepStatusHandler.
 */
public class SleepStatusHandler implements StatusHandler<SleepStatus> {
	
	@Override
	public void inflict(SleepStatus status, StatusList statusList, Entity attacker) {
		statusList.addInternal(status);
	}
	
	@Override
	public void remove(SleepStatus status, StatusList statusList) {
		return;
	}
}
