package games.stendhal.server.entity.item;

import java.util.Map;

import org.apache.log4j.Logger;

import games.stendhal.server.entity.status.Status;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.status.SleepStatus;
import games.stendhal.server.entity.status.StatusType;
import marauroa.common.game.RPObject;

/** 
 * Class for items which may be used to sleep.
 * 
 * @author Kamil Synak
 */
public class SleepItem extends Item {
	
	private final static Logger logger = Logger.getLogger(ConsumableItem.class);
	
	/**
	 *
	 * Creates a new SleepItem.
	 *
	 * @param name
	 *            name of SleepItem.
	 * @param clazz
	 *            class (or type) of item.
	 * @param subclass
	 *            subclass of this item.
	 * @param attributes
	 *            attributes (like attack). may be empty or <code>null</code>.
	 */
	public SleepItem(final String name, final String clazz, final String subclass,
				     final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
		
		setPersistent(true);
	}
	
	/**
	 * Copy constructor.
	 *
	 * @param SleepItem
	 *            SleepItem to copy.
	 */
	public SleepItem(final SleepItem item) {
		super(item);
		
		setPersistent(true);
	}
	
	/**
	 * Inflicts or removes the SLEEPING status on player who uses the item.
	 *
	 * @param user
	 *            The user who used the item.
	 */
	@Override
	public boolean onUsed(final RPEntity user) {
		if (user instanceof Player) {
			final Player player = (Player) user;

			if (isContained()) {
				// We modify the base container if the object change.
				RPObject base = getContainer();

				while (base.isContained()) {
					base = base.getContainer();
				}

				if (!user.nextTo((Entity) base)) {
					user.sendPrivateText("The sleeping item is too far away");
					return false;
				}
			} else {
				if (!nextTo(user)) {
					user.sendPrivateText("The sleeping item is too far away");
					return false;
				} 
			}
			Status status;
			if(!player.hasStatus(StatusType.SLEEPING))
			{
				status = new SleepStatus();
				player.getStatusList().inflictStatus(status, this);
				
				//Does not seem to work consistently.
		        //put("menu", "Wake up|Use");
			}
			else
			{
				player.getStatusList().removeAll(StatusType.SLEEPING);
				//Does not seem to work consistently.
				//put("menu", "Sleep|Use");
			}
			player.notifyWorldAboutChanges();
			return true;
		} else {
			logger.error("user is no instance of Player but: " + user, new Throwable());
			return false;
		}
	}
}
