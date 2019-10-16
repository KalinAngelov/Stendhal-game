/***************************************************************************
 *                   (C) Copyright 2003-2011 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.quests;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.ados.wall.GreeterSoldierNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.RPClass.ItemTestHelper;


public class EmotionCrystalsTest {

	/*private Player player;
	private SpeakerNPC npc;
	private Engine en;

	private String questSlot;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
	}

	@Before
	public void setUp() {
		//set up the world for testing
		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new GreeterSoldierNPC().configureZone(zone, null);
		

		AbstractQuest quest = new EmotionCrystals();
		quest.addToWorld();
		questSlot = quest.getSlotName();

		player = PlayerTestHelper.createPlayer("bob");
		zone.add(player);
	}

	@After
	public void tearDown() {
		PlayerTestHelper.removeNPC("Julius");
	}

	@Test
	public void testQuest() {
		//set up npc in the world
		npc = SingletonRepository.getNPCList().get("Julius");
		en = npc.getEngine();
		
		PlayerTestHelper.registerPlayer(player);
        // give the player the required quest
		player.setQuest("emotion_crystals", null);

		//give the player two crystals
        PlayerTestHelper.equipWithItem(player , "red emotion crystal");
        PlayerTestHelper.equipWithItem(player , "blue emotion crystal");
        //check the travel log says which crystals the player has collected
        assertEquals("I have found the following crystals: red emotion crystal, and blue emotion crystal", GET TRAVEL LOG ENTRY);
        //give a third crystal
        PlayerTestHelper.equipWithItem(player , "yellow emotion crystal");
        //check the travel log says which crystals the player has collected
        assertEquals("I have found the following crystals: red emotion crystal, blue emotion crystal, and yellow emotion crystal", GET TRAVEL LOG ENTRY);
        //drop one of the crystals
        player.dropItem("blue emotion crystal", 1);
        //check the travel log for all 3 crystals
        assertEquals("I have found the following crystals: red emotion crystal, blue emotion crystal, and yellow emotion crystal", GET TRAVEL LOG ENTRY);

		
	}*/

}
