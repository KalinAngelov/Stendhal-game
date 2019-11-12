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
	
	@Test
	public void testNewspaperCreation()
	{
		final Item newspaper = SingletonRepository.getEntityManager().getItem("newspaper");
		assertNotNull("Generated item is null", newspaper);
		assertTrue("The newspaper is not a NewspaperItem", newspaper instanceof Newspaper);
	}
	
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
