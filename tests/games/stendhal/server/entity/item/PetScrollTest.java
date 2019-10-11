package games.stendhal.server.entity.item;
import static org.junit.Assert.*;

//import java.awt.geom.Rectangle2D;

import org.junit.BeforeClass;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.creature.Pet;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.PetTestHelper;
import games.stendhal.server.entity.creature.PurpleDragon;
import org.junit.Test;



public class PetScrollTest {	
	private static Pet pet;
	private static Player playerUser;
	private static Item testBlankPetScroll;
	private static StendhalRPZone testZone;
	private static Item testSummonPetScroll;
 
    @BeforeClass
    public static void SetUpBeforeClass()throws Exception{
    	PetTestHelper.generateRPClasses();
    	// create a player
    	playerUser = PlayerTestHelper.createPlayer("test user");
    	// create a zone for test, override the isInProtectionArea method in StendhalRPZone
    	testZone = new StendhalRPZone("zone") {
    		@Override
    		public boolean isInProtectionArea(final Entity entity) {
    			return false;
    		}
    	};
    	MockStendlRPWorld.get().addRPZone(testZone);
    	// add the user to a zone
    	testZone.add(playerUser);
    	// new a pet
        pet = new PurpleDragon(playerUser);        
        testZone.add(pet);
    	pet.setLevel(100);
		pet.setTitle("happy");
		pet.setBaseHP(1000);
		pet.setHP(800);
		pet.setXP(500);
		pet.setWeight(20);
		pet.setAtk(200);
		pet.setDef(200);
		pet.setWeight(20);
    	playerUser.setPet(pet);
    	// equipped with a blank pet scroll
    	PlayerTestHelper.equipWithItem(playerUser, "blank pet scroll");
    	// get the blank pet scroll from the player
    	testBlankPetScroll = playerUser.getFirstEquipped("blank pet scroll");
    }
    
	@Test
	public void test() {
		// use the blank pet scroll
		testBlankPetScroll.onUsed(playerUser);
		// get the summon pet scroll from the player
		testSummonPetScroll = playerUser.getFirstEquipped("summon pet scroll");
		// use the summon pet scroll
		testSummonPetScroll.onUsed(playerUser);
		// compare the pet
		assertEquals(100, playerUser.getPet().getLevel());
		assertEquals("happy", playerUser.getPet().getTitle());
		assertEquals(1000, playerUser.getPet().getBaseHP());
		assertEquals(800, playerUser.getPet().getHP());
		assertEquals(500, playerUser.getPet().getXP());
		assertEquals(200, playerUser.getPet().getAtk());
		assertEquals(200, playerUser.getPet().getDef());
		assertEquals(20, playerUser.getPet().getWeight());		

	}

}
