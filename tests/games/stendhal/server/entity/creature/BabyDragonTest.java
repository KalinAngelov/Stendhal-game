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
package games.stendhal.server.entity.creature;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPObject;
import utilities.PlayerTestHelper;
import utilities.RPClass.BabyDragonTestHelper;

public class BabyDragonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BabyDragonTestHelper.generateRPClasses();
		MockStendlRPWorld.get();
	}

	List<String> foods = Arrays.asList("ham", "pizza", "meat");

	/**
	 * Tests for babyDragon.
	 */
	@Test
	public void testBabyDragon() {
		final BabyDragon drako = new BabyDragon();
		assertThat(drako.getFoodNames(), is(foods));
	}

	/**
	 * Tests for babyDragonPlayer.
	 */
	@Test
	public void testBabyDragonPlayer() {

		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);
		final BabyDragon drako = new BabyDragon(bob);

		assertThat(drako.getFoodNames(), is(foods));
	}

	/**
	 * Tests for babyDragonRPObjectPlayer.
	 */
	@Test
	public void testBabyDragonRPObjectPlayer() {
		RPObject template = new RPObject();
		template.put("hp", 30);
		final BabyDragon drako = new BabyDragon(template, PlayerTestHelper.createPlayer("bob"));
		assertThat(drako.getFoodNames(), is(foods));
	}
	
	@Test
	public final void testBabyDragonGetWeightMinimal() {
		Player bob =  PlayerTestHelper.createPlayer("bob"); 
		final StendhalRPZone zone = new StendhalRPZone("zone");
		zone.add(bob);
		
		RPObject template = new RPObject();
		template.put("hp", 30);
		final BabyDragon drako1 = new BabyDragon(template, bob);
		assertEquals(1, drako1.getWeight());
		
		final BabyDragon drako2 = new BabyDragon();
		assertEquals(1, drako2.getWeight());
	}
	
	@Test
	public final void testBabyDragonStarvationAtMinimalWeight() {
		Player bob =  PlayerTestHelper.createPlayer("bob"); 
		final StendhalRPZone zone = new StendhalRPZone("zone");
		zone.add(bob);
		
		RPObject template = new RPObject();
		template.put("hp", 30);
		final BabyDragon drako = new BabyDragon(template, bob);
		zone.add(drako);
		drako.hunger = 1000;
		drako.logic();
		assertEquals(1, drako.getWeight());
	}
	
	@Test
	public final void testBabyDragonStarvation() {
		Player bob =  PlayerTestHelper.createPlayer("bob"); 
		final StendhalRPZone zone = new StendhalRPZone("zone");
		zone.add(bob);
		
		RPObject template = new RPObject();
		template.put("hp", 30);
		final BabyDragon drako = new BabyDragon(template, bob);
		zone.add(drako);
		drako.weight = 2;
		drako.hunger = 1000;
		drako.logic();
		assertEquals(1, drako.getWeight());
	}

}
