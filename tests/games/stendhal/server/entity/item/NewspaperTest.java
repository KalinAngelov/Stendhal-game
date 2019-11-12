package games.stendhal.server.entity.item;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import utilities.PlayerTestHelper;
import utilities.RPClass.ItemTestHelper;

public class NewspaperTest 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		Log4J.init();
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();
	}
	
	/**
	 * Tests the newspaper item creation. 
	 */
	@Test
	public void testNewspaperCreation()
	{
		final Item newspaper = SingletonRepository.getEntityManager().getItem("newspaper");
		assertNotNull("Generated item is null", newspaper);
		assertTrue("The newspaper is not a NewspaperItem", newspaper instanceof Newspaper);
	}
	
	/**
	 * Tests the reading option of the newspaper when it is on ground.
	 */
	@Test
	public void testNewspaperOnGround() {
		final Item newspaper = SingletonRepository.getEntityManager().getItem("newspaper");
		assertNotNull(newspaper);
		final Player player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(player);
		final StendhalRPZone zone = new StendhalRPZone("ITEMTESTZONE");
		SingletonRepository.getRPWorld().addRPZone(zone);
		zone.add(player);

		zone.add(newspaper);
		newspaper.setPosition(1, 0);

		assertTrue(newspaper.onUsed(player));

	}
	
	/**
	 * Tests the reading option of the newspaper when it is in the bag.
	 */
	@Test
	public void testNewspaperInTheBag() {
		final Item newspaper = SingletonRepository.getEntityManager().getItem("newspaper");
		assertNotNull(newspaper);
		final Player player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(player);
		final StendhalRPZone zone = new StendhalRPZone("ITEMTESTZONE");
		SingletonRepository.getRPWorld().addRPZone(zone);
		zone.add(player);

		player.equip("bag", newspaper);

		assertTrue(newspaper.onUsed(player));
	}


}
