package games.stendhal.server.entity.status;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.ItemTestHelper;

public class SleepStatusTest {
	private static final String ZONE_NAME = "ITEMTESTZONE";	

	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();
	}	
	/**
	 * Tests the sleeping bag movement freeze. 
	 */
	@Test
	public void testSleepMovementFreeze() {
		final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");
		assertNotNull(sleepingBag);
		final Player player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(player);
		final StendhalRPZone zone = new StendhalRPZone(ZONE_NAME);
		SingletonRepository.getRPWorld().addRPZone(zone);
		zone.add(player);

		zone.add(sleepingBag);
		sleepingBag.setPosition(1, 0);
        int oldX = player.getX();
        int oldY = player.getY();
		assertTrue(sleepingBag.onUsed(player));
		player.applyMovement();
		assertTrue("The player should not be able to move", (oldX == player.getX() && oldY == player.getY()));
	}
}
