package games.stendhal.server.maps.kirdneh.city;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class FishmongerNPCTest extends ZonePlayerAndNPCTestImpl {
	
	private static final String ZONE_NAME = "int_ados_felinas_house";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}

	public FishmongerNPCTest() {
		setNpcNames("Fishmonger");
		setZoneForPlayer(ZONE_NAME);
		addZoneConfigurator(new KirdnehFishyMarketNPC(), ZONE_NAME);
	}
	
	@Test
	public void testSellFish() {
		final SpeakerNPC npc = getNPC("Fishmonger");
		final Engine en = npc.getEngine();
		
		assertTrue(en.step(player, "hi"));
		assertEquals("Ahoy, me hearty! Back from yer swashbucklin, ah see.", getReply(npc));
		
		assertTrue(en.step(player, "job"));
		assertEquals("By the Powers! I be buyin. You be sellin?", getReply(npc));
		
		PlayerTestHelper.equipWithItem(player, "red lionfish");
		assertTrue(en.step(player, "sell red lionfish"));
		assertEquals("A red lionfish is worth 120. Do you want to sell it?", getReply(npc));
		
		assertTrue(en.step(player, "yes"));
		
		assertFalse(player.isEquipped("red lionfish"));
		assertEquals("Thanks! Here is your money.", getReply(npc));
		
	}
	
	

}
