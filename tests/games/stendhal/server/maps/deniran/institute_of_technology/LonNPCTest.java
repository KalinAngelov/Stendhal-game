package games.stendhal.server.maps.deniran.institute_of_technology;
import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;
import org.junit.BeforeClass;
import org.junit.Test;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.maps.deniran.institute_of_technology.LonNPC;
import games.stendhal.server.entity.npc.SpeakerNPC;


public class LonNPCTest extends ZonePlayerAndNPCTestImpl{
	// set the zone name which is inside the  deniran castle
	private static final String ZONE_NAME = "int_deniran_castle";

	// initialize the zone
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	} // setUpBeforeClass

	// set the LonNPC, player and add tehm to the zone
	public LonNPCTest() {
		setNpcNames("Lon Jatham");
		setZoneForPlayer(ZONE_NAME);
		addZoneConfigurator(new LonNPC(), ZONE_NAME);
	} // LonNPCTest

	// test hi and bye behaviour of LonNPC
	@Test
	public void testHiAndBye() {
		// get the NPC
		final SpeakerNPC LonNPC = getNPC("Lon Jatham");
		// test if the NPC is set properly
		assertNotNull(LonNPC);
		final Engine en = LonNPC.getEngine();
        // say hi to Lon and test for reply
		assertTrue(en.step(player, "hi"));
		assertEquals("Hello, I am Lon!", getReply(LonNPC));
        // say bye to Lon and test for reply
		assertTrue(en.step(player, "bye"));
		assertEquals("See you!", getReply(LonNPC));
	} // testHiandBye
} // LonNPCTest