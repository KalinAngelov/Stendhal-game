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
package games.stendhal.server.maps.deniran;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.parser.Expression;
import games.stendhal.common.parser.ExpressionType;
import games.stendhal.common.parser.Sentence;
import games.stendhal.common.parser.SentenceImplementation;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.NPC;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.deniran.NewspaperSellerNPC;
import marauroa.common.Log4J;
import utilities.PlayerTestHelper;


public class NewspaperSellerNPCTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		MockStendlRPWorld.get();
	}

	/**
	 * Tests for configureZone.
	 */
	@Test
	public void testConfigureZone() {

		SingletonRepository.getRPWorld();
		final NewspaperSellerNPC newspaperSellerConfigurator = new NewspaperSellerNPC();

		final StendhalRPZone zone = new StendhalRPZone("testzone");
		newspaperSellerConfigurator.configureZone(zone, null);
		assertFalse(zone.getNPCList().isEmpty());
		final NPC newspaperSeller = zone.getNPCList().get(0);
		assertThat(newspaperSeller.getName(), is("Tom"));
		assertThat(newspaperSeller.getDescription(), is("You see a newspaper seller, he looks excited about something."));
	}
	
	/**
	 * Tests for hiandBye.
	 */
	@Test
	public void testHiandBye() {
		SingletonRepository.getRPWorld();
		final NewspaperSellerNPC newspaperSellerConfigurator = new NewspaperSellerNPC();
		final StendhalRPZone zone = new StendhalRPZone("testzone");
		newspaperSellerConfigurator.configureZone(zone, null);
		final SpeakerNPC newspaperSeller = (SpeakerNPC) zone.getNPCList().get(0);
		assertThat(newspaperSeller.getName(), is("Tom"));
		final Engine engine = newspaperSeller.getEngine();
		engine.setCurrentState(ConversationStates.IDLE);

		Sentence sentence = new SentenceImplementation(new Expression("hi", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat(getReply(newspaperSeller), is("Hello my friend!"));
		
		sentence = new SentenceImplementation(new Expression("job", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat("job text", getReply(newspaperSeller),
				is("I'm a newspaper salesman, but unfortunately our newspaper has not been released so I am here to promote."));
		
		sentence = new SentenceImplementation(new Expression("help", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat("help text", getReply(newspaperSeller),
				is("Ask me about #newspaper and I will tell you about the new newspapers."));
		
		sentence = new SentenceImplementation(new Expression("newspaper", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat("help text", getReply(newspaperSeller),
				is("The newspaper coming  will tell stories of the biggest accomplishments across the Stendhal world!"));

		sentence = new SentenceImplementation(new Expression("bye", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.IDLE));
		assertThat(getReply(newspaperSeller), is("Hope to see you again soon!"));
	}

}
