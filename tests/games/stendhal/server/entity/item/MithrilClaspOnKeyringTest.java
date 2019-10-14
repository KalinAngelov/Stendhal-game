package games.stendhal.server.entity.item;

import static org.junit.Assert.*;
import org.junit.Test;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.item.Item;

/*
  Testing if the item "mithril clasp" can be equipped on the keyring slot
  -Creating an item "mithril clasp"
  -Using a method from Item(server item) that checks if an item can be equipped in a slot
*/

public class MithrilClaspOnKeyringTest 
{
	
	@Test
	public void ShouldCheckIfMithrilClaspCanBeEquippedOnKeyring() 
	{
		// Creating a mithril clasp 
		final Item item = SingletonRepository.getEntityManager().getItem("mithril clasp");
		
		// Returns true if the mithril clasp can be equipped on the keyring
	    assertTrue(item.canBeEquippedIn("keyring"));
	    
	} // ShouldCheckIfMithrilClaspCanBeEquippedOnKeyring
	
} // class EquipMithrilClaspOnKeyringTest
