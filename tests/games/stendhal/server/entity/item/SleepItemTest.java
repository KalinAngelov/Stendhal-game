package games.stendhal.server.entity.item;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
//import games.stendhal.server.entity.status.StatusType;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import utilities.PlayerTestHelper;
import utilities.RPClass.ItemTestHelper;

public class SleepItemTest {
	private static final String ZONE_NAME = "ITEMTESTZONE";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();
		
	}
	
	@Test
	public void testSleepingBagCreation() {
		final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");
		assertNotNull("Generated item is not null", sleepingBag);
		assertTrue("The sleeping bag is not a SleepItem", sleepingBag instanceof SleepItem);
		assertTrue("The sleeping bag is persistent", !sleepingBag.isPersistent());
	}
	
	@Test
	public void testSleepingBagSleepOptionOnGround() {
		final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");
		assertNotNull(sleepingBag);
		final Player player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(player);
		final StendhalRPZone zone = new StendhalRPZone(ZONE_NAME);
		SingletonRepository.getRPWorld().addRPZone(zone);
		zone.add(player);

		zone.add(sleepingBag);
		sleepingBag.setPosition(1, 0);

		assertTrue(sleepingBag.onUsed(player));
		//assertTrue(player.hasStatus(StatusType.SLEEPING));
	}
	
	@Test
	public void testSleepingBagSleepOptionInBag() {
		final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");
		assertNotNull(sleepingBag);
		final Player player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(player);
		final StendhalRPZone zone = new StendhalRPZone(ZONE_NAME);
		SingletonRepository.getRPWorld().addRPZone(zone);
		zone.add(player);

		player.equip("bag", sleepingBag);

		assertTrue(sleepingBag.onUsed(player));
		//assertTrue(player.hasStatus(StatusType.SLEEPING));
	}

}
