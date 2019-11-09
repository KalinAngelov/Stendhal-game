package games.stendhal.server.entity.item;

import java.util.Map;

/** 
 * Stub for SleepItem.
 */
public class SleepItem extends Item {
	public SleepItem(final String name, final String clazz, final String subclass,
				     final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
		
		setPersistent(true);
	}
	public SleepItem(final SleepItem item) {
		super(item);
		
		setPersistent(true);
	}
}
