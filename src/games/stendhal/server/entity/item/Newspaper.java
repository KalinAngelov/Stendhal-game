package games.stendhal.server.entity.item;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import games.stendhal.server.core.engine.db.AchievementDAO;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import marauroa.server.game.db.DAORegister;

/**
 * A new Newspaper item that is a subclass of Item
 *
 * @author Kalin Angelov
 *
 */


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
		// Inherit from the superclass
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
		// Inherit from the superclass
		super(item);
	} // Newspaper
	
	/**
	 * When read is pressed 
	 *
	 * @param user
	 *            The user who used the item.
	 */
	@Override
	public boolean onUsed(RPEntity user)
	{
		// Checking if the parameter is an instance of a Player
		if (user instanceof Player) 
		{
		  // Casting the parameter to be consider as a Player
          player = (Player) user;    
		  if (isContained())
		  {
			// Print the message in a new window in the game
		    player.sendPrivateText(this.describeContentOfNewspaper());
		    return true;
		  } // if
		  else 
		    if (!player.nextTo(this))
			{
		      // Print the message in a new window in the game
			  player.sendPrivateText("The item is too far away to be used.");
			  return false;
			} // if
			else
			{
			  // Print the message in a new window in the game
		      player.sendPrivateText(this.describeContentOfNewspaper());
			  return true;
			}//else
		  
		} // if
		
		return false;
	} // onUsed
	
	/**
	 * When read is pressed, describe call the method to get the achievements' list 
	 *
	 *            
	 * @throws SQLException 
	 */
	public String describeContentOfNewspaper() 
	{
	  String startOfInfo = "This is the most interesting newspaper!\n" 
	                                + "These are your achiviements:\n";
	  String info = null;
	  // Handling the sql exceptions
	  try
	  {
		// A new hash set to contain the achievements 
		Set<String> hash_Set = new HashSet<String>();  
		hash_Set = DAORegister.get().get
		  (AchievementDAO.class).loadAllReachedAchievementsOfPlayer(player.getName());
	    info = String.join(" ,", hash_Set);
	    // Checking if there are any achievements
	    if (info.length() == 0) info = "No achievments yet";
	  } // try
      catch(SQLException e)
	  {
    	// An error message   
		System.out.println("An error has occured");		
	  }
	  //returning the string 
	  return (startOfInfo + info);
	}
	
} // class Newspaper
