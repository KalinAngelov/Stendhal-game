package games.stendhal.server.entity.status;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.creature.Creature;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.CreatureTestHelper;
import utilities.RPClass.ItemTestHelper;

public class SleepStatusTest {
	private static final String ZONE_NAME = "ITEMTESTZONE";	

	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();
		CreatureTestHelper.generateRPClasses();
	}	
	/**
	 * Tests waking up from damage. 
	 */
	@Test
	public void testWakeUpFromDamage() {
		final Player player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(player);
		Creature atkCreature = new Creature();
		final StendhalRPZone zone = new StendhalRPZone(ZONE_NAME);
		SingletonRepository.getRPWorld().addRPZone(zone);
		
		zone.add(player);
		zone.add(atkCreature);
		
		StatusList playerStatus = player.getStatusList();
        playerStatus.inflictStatus(new SleepStatus(), player);
        
        atkCreature.setAtkXP(100);
		atkCreature.setTarget(player);
		atkCreature.attack();
		
		assertTrue("The player should wake up when damaged", !player.hasStatus(StatusType.SLEEPING));
	}
}
