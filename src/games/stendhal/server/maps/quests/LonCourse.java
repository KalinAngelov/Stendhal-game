/* $Id$ */
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


import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.SetQuestAction;
import games.stendhal.server.entity.npc.condition.*;
import games.stendhal.server.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * QUEST: Lon Course
 *
 * PARTICIPANTS:
 * <ul>
 * <li>Lon Jatham (Lon)</li>
 * </ul>
 *
 * STEPS:
 * <ul>
 * <li>Lon asks players to get another NPC to sign to his class</li>
 * </ul>
 *
 * REPETITIONS:
 * <ul>
 * <li>None</li>
 * </ul>
 */

public class LonCourse extends AbstractQuest {
	public static final String QUEST_SLOT = "lon_course";

	// getHistory stub
	public List<String> getHistory(final Player player) {
		final List<String> res = new ArrayList<String>();
		return res;
	}

//	new AndCondition(new QuestNotCompletedCondition(QUEST_SLOT),
//			new AdminCondition()),
	
	// assertThat(player.getAdminLevel(), is(adminlevel));
	private void prepareRequestingStep() {
		final SpeakerNPC npc = npcs.get("Lon Jatham");

		npc.add(ConversationStates.ATTENDING,
			"recruit",
			null,
			ConversationStates.ATTENDING,
			// replace this message with something that makes sense
			"Recruit 1 student for my course!",
			new SetQuestAction(QUEST_SLOT, "start"));

		npc.add(ConversationStates.ATTENDING,
			"recruit",
			new QuestCompletedCondition(QUEST_SLOT),
			ConversationStates.ATTENDING,
			"Thanks, the course is full now!",
			null);

		// Add QUEST which
	}

	@Override
	public void addToWorld() {
		fillQuestInfo(
				"Lon Jatham course",
				"Jon Latham wants you to sign people onto his course.",
				false);
		prepareRequestingStep();
	}

	@Override
	public String getSlotName() {
		return QUEST_SLOT;
	}

	@Override
	public String getName() {
		return "lon_course";
	}

	public String getTitle() {
		return "Lon Jatham course";
	}

	@Override
	public int getMinLevel() {
		return 0;
	}

	@Override
	public String getNPCName() {
		return "Lon";
	}
}
