package games.stendhal.server.entity.item;

import java.util.Map;


import games.stendhal.server.entity.item.Item;

/** 
 * Stub for SleepItem.
 */


public class Newspaper extends Item 
{
	//private NewspaperGUIPanel guiPanel;

	public Newspaper(final String name, final String clazz, final String subclass, final Map<String,String> attributes) 
	{
		super(name, clazz, subclass, attributes);
	}
	

	public Newspaper(final Newspaper item) 
	{
		super(item);
	}
	
	
}
