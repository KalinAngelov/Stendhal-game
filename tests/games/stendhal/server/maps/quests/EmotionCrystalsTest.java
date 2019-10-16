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


import static org.junit.Assert.assertEquals;

import java.util.List;

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
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.ados.snake_pit.PurpleCrystalNPC;
import games.stendhal.server.maps.ados.wall.GreeterSoldierNPC;
import games.stendhal.server.maps.fado.hut.BlueCrystalNPC;
import games.stendhal.server.maps.nalwor.forest.RedCrystalNPC;
import games.stendhal.server.maps.nalwor.river.PinkCrystalNPC;
import games.stendhal.server.maps.semos.mountain.YellowCrystalNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.RPClass.ItemTestHelper;


public class EmotionCrystalsTest {

	private Player player;
	private SpeakerNPC npc;
	private Engine en;

	private String questSlot;
	private EmotionCrystals quest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		MockStendlRPWorld.get();
	}

	@Before
	public void setUp() {
		//set up the world for testing
		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new GreeterSoldierNPC().configureZone(zone, null);
		new RedCrystalNPC().configureZone(zone, null);
		new PurpleCrystalNPC().configureZone(zone, null);
		new YellowCrystalNPC().configureZone(zone, null);
		new BlueCrystalNPC().configureZone(zone, null);
		new PinkCrystalNPC().configureZone(zone, null);
		
		npc = SingletonRepository.getNPCList().get("Julius");
		en = npc.getEngine();
		

		quest = new EmotionCrystals();
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
		
		PlayerTestHelper.registerPlayer(player);
        // give the player the required quest
		player.setQuest("emotion_crystals", 0, "start");
		Item red_emotion_crystal = ItemTestHelper.createItem("red emotion crystal");
		Item blue_emotion_crystal = ItemTestHelper.createItem("blue emotion crystal");
		Item yellow_emotion_crystal = ItemTestHelper.createItem("yellow emotion crystal");
		//give the player two crystals
		player.getSlot("bag").add(red_emotion_crystal);
		player.getSlot("bag").add(blue_emotion_crystal);
        //check the travel log says which crystals the player has collected
        List<String> playerHistory = quest.getHistory(player);
        int size = playerHistory.size();
        String crystalsSentence = playerHistory.get(size-1);
        assertEquals("I have found the following crystals: red emotion crystal and blue emotion crystal", crystalsSentence);
        //give a third crystal
        player.getSlot("bag").add(yellow_emotion_crystal);   
        //check the travel log says which crystals the player has collected
        List<String> playerHistory2 = quest.getHistory(player);
        int size2 = playerHistory2.size();
        String crystalsSentence2 = playerHistory2.get(size2-1);
        assertEquals("I have found the following crystals: red emotion crystal, blue emotion crystal, and yellow emotion crystal", crystalsSentence2);
        //drop one of the crystals
        player.drop("red emotion crystal", 1);
        List<String> playerHistory3 = quest.getHistory(player);
        int size3 = playerHistory3.size();
        String crystalsSentence3 = playerHistory3.get(size3-1);
        System.out.println(crystalsSentence3);
        //check the travel log for all 3 crystals
        assertEquals("I have found the following crystals: red emotion crystal, blue emotion crystal, and yellow emotion crystal", crystalsSentence3);

		
	}

}
