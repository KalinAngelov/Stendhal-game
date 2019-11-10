package games.stendhal.server.entity.status;

import games.stendhal.common.NotificationType;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
/** 
 * Class for handling the sleep status.
 * 
 * @author Nikolay Avramov
 */
public class SleepStatusHandler implements StatusHandler<SleepStatus> {
	
	/**
	 *
	 * Inflicts the sleep status to a player causing them to freeze in place
	 *
	 * @param status
	 *            status to be inflicted
	 * @param statusList
	 *            statusList of the entity to whom the status would be inflicted
	 * @param attacker
	 *            the entity that inflicts the status
	 */
	@Override
	public void inflict(SleepStatus status, StatusList statusList, Entity attacker) {
		// if the entity is not already sleeping
		if (!statusList.hasStatus(status.getStatusType())) {
			RPEntity entity = statusList.getEntity();
			// if the given status list has an existing entity
			// notify it that it is asleep
			if (entity != null) {
			    entity.sendPrivateText(NotificationType.SCENE_SETTING, "You are asleep.");
			    // stop the entity's movement (not working as intended)
				if (entity instanceof Player) {
					((Player) entity).forceStop();
				} else {
					entity.stop();
				}
				entity.clearPath();
			    // set the status
				statusList.addInternal(status);
				
				entity.notifyWorldAboutChanges();
			}
		}	
	}
	
	/**
	 *
	 * remove the sleep status from a player causing them to wake up
	 *
	 * @param status
	 *            status to be removed
	 * @param statusList
	 *            statusList of the entity to whom the status would be removed
	 */
	@Override
	public void remove(SleepStatus status, StatusList statusList) {
		RPEntity entity = statusList.getEntity();
		if (entity != null) {
		    entity.sendPrivateText(NotificationType.SCENE_SETTING, "You are awake.");
		}
		statusList.removeInternal(status);
	}
}
