package games.stendhal.server.entity.status;

import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.TurnNotifier;
import games.stendhal.server.entity.RPEntity;
/**
 * Class for handling the sleep status each turn
 *
 * @author Nikolay Avramov
 */
public class SleepStatusTurnListener implements TurnListener {
	private StatusList statusList;
	
	/**
	 * SleepStatusTurnListener
	 *
	 * @param statusList StatusList
	 */
	public SleepStatusTurnListener(StatusList statusList) {
		this.statusList = statusList;
	}
	
	@Override
	public void onTurnReached(int currentTurn) {
		RPEntity entity = statusList.getEntity();
		
		SleepStatus status = statusList.getFirstStatusByClass(SleepStatus.class);

		// check that the entity exists and has this status
		if ((entity == null) || (status == null)) {
			return;
		}
		// heal the entity every 10 turns
		if (currentTurn % 10 == 0) {
			entity.heal((entity.getBaseHP() / 1000), true);
		}
		
		/*// Stop the entity's movement
		((Player) entity).forceStop();	
		entity.clearPath();*/
		
		entity.notifyWorldAboutChanges();
		TurnNotifier.get().notifyInTurns(0, this);
	}
}
