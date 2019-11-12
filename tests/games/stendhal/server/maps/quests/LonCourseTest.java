/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.StackableItem;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.deniran.institute_of_technology.LonNPC;
import games.stendhal.server.maps.semos.guardhouse.RetiredAdventurerNPC;
import marauroa.common.Log4J;
import marauroa.common.game.RPObject.ID;
import utilities.PlayerTestHelper;

public class LonCourseTest {

	private SpeakerNPC lon;
	private LonCourse lc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();

		MockStendhalRPRuleProcessor.get();

		MockStendlRPWorld.reset();
		MockStendlRPWorld.get();
	}

	@Before
	public void setup() {
		PlayerTestHelper.removeAllPlayers();
		StendhalRPZone zone = new StendhalRPZone("admin_test");
		new LonNPC().configureZone(zone, null);
		lon = SingletonRepository.getNPCList().get("Lon Jatham");
		System.out.println(lon);
		
		lc = new LonCourse();
		
		lc.addToWorld();
	}

	@Test
	public void quest() {

		final Player player = PlayerTestHelper.createPlayer("player");


		final Engine en = lon.getEngine();
		en.step(player, "hi");
		// we assume the player has already completed the meet hayunn quest
		// so that we know which of the greetings he will use
		// player.setQuest("lon_course", "done");
		assertTrue(!player.isQuestCompleted("lon_course"));
		assertTrue(lon.isTalking());
		System.out.println(en.getCurrentState());

		en.step(player, "Hi Lon, can I get an extension?");
		assertEquals(
				"Hello, I am Lon!",
				getReply(lon));
		assertTrue(!player.hasQuest("lon_course"));

		en.step(player, "recruit");
		System.out.println(en.getCurrentState());
		
		assertEquals(
				"Recruit 1 student for my course!",
				getReply(lon));
		assertTrue(player.hasQuest("lon_course"));
	}

}
