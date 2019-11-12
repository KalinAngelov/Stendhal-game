package games.stendhal.server.entity.item;

import java.util.Map;

import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;



public class Newspaper extends Item 
{
	private NewspaperGUIPanel guiPanel;

	public Newspaper(final String name, final String clazz, final String subclass, final Map<String,String> attributes) 
	{
		super(name, clazz, subclass, attributes);
	}
	
	
	/**
	 * copy constructor.
	 *
	 * @param item
	 *            item to copy
	 */
	public Newspaper(final Newspaper item) 
	{
		super(item);
	}
	
	@Override
	public boolean onUsed(RPEntity user) 
	{
		if (user instanceof Player) 
		{
			if (isContained())
			{
			  guiPanel = new NewspaperGUIPanel();
			  return true;
			} // if
			else 
			
				if (!user.nextTo(this))
				{
					user.sendPrivateText("The item is too far away to be used.");
					return false;
				} // if
				else
				{
					guiPanel = new NewspaperGUIPanel();
					  return true;
				}
		} // if
		
		return false;
	}
}
