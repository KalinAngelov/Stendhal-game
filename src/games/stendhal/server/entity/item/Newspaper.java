package games.stendhal.server.entity.item;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import games.stendhal.server.core.engine.db.AchievementDAO;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import marauroa.server.game.db.DAORegister;



public class Newspaper extends Item 
{
	private Player player;
    
	/**
	 *
	 * Creates a new Newspaper item.
	 *
	 * @param name
	 *            name of Newspaper.
	 * @param clazz
	 *            class (or type) of item.
	 * @param subclass
	 *            subclass of this item.
	 * @param attributes
	 *            attributes (like attack). may be empty or <code>null</code>.
	 */

	public Newspaper(final String name, final String clazz, final String subclass, final Map<String,String> attributes) 
	{
		super(name, clazz, subclass, attributes);
	} // Newspaper
	
	
	/**
	 * copy constructor.
	 *
	 * @param item
	 *            item to copy
	 */
	public Newspaper(final Newspaper item) 
	{
		super(item);
	} // Newspaper
	
	/**
	 * When read is pressed 
	 *
	 * @param user
	 *            The user who used the item.
	 * @throws SQLException 
	 */
	@Override
	public boolean onUsed(RPEntity user)
	{
		if (user instanceof Player) 
		{
          player = (Player) user;    
		  if (isContained())
		  {
		    player.sendPrivateText(this.describeContentOfNewspaper());
		    return true;
		  } // if
		  else 
		    if (!player.nextTo(this))
			{
			  player.sendPrivateText("The item is too far away to be used.");
			  return false;
			} // if
			else
			{
		      player.sendPrivateText(this.describeContentOfNewspaper());
			  return true;
			}//else
		  
		} // if
		
		return false;
	} // onUsed
	
	
	public String describeContentOfNewspaper() 
	{
	  String startOfInfo = "This is the most interesting newspaper!\nThese are your achiviements:\n";
	  String info = null;
	  try
	  {
		Set<String> hash_Set = new HashSet<String>();  
		hash_Set = DAORegister.get().get(AchievementDAO.class).loadAllReachedAchievementsOfPlayer(player.getName());
	    info = String.join(" ,", hash_Set);
	  } // try
      catch(SQLException e)
	  {
		System.out.println("An error has occured");		
	  }
	  return (startOfInfo + info);
	}
	
} // class Newspaper
