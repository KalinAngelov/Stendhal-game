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
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.deniran.institute_of_technology.LonNPC;
import marauroa.common.Log4J;
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
		StendhalRPZone zone = new StendhalRPZone("player_test");
		new LonNPC().configureZone(zone, null);

		lon = SingletonRepository.getNPCList().get("Lon Jatham");

		lc = new LonCourse();
		lc.addToWorld();

	}

	@Test
	public void quest() {

		final Player player = PlayerTestHelper.createPlayer("player");

		final Engine en = lon.getEngine();

		// Admin being tested
		en.step(player, "hi");

		// player.setQuest("lon_course", "done");
		assertTrue(!player.isQuestCompleted("lon_course"));
		assertTrue(lon.isTalking());

		en.step(player, "Hi Lon, can I get an extension?");
		assertEquals(
				"Hello, I am Lon!",
				getReply(lon));
		assertTrue(!player.hasQuest("lon_course"));

		en.step(player, "Hi Lon, can I get an extension?");

		en.step(player, "recruit");

		assertEquals(
				null,
				getReply(lon));
		assertTrue(!player.hasQuest("lon_course"));

		player.setAdminLevel(100);

		en.step(player, "recruit");
		assertEquals(
				"Recruit 1 student for my course!",
				getReply(lon));

		assertTrue(player.hasQuest("lon_course"));

//		player.setQuest("lon_course", "done");
//		en.step(player, "recruit");
//
//		assertEquals(
//				"Thanks, the course is full now!",
//				getReply(lon));
	}

}
