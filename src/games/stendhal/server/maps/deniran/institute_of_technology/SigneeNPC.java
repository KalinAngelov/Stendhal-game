package games.stendhal.server.maps.deniran.institute_of_technology;

import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.npc.ChatAction;
import games.stendhal.server.entity.npc.ConversationPhrases;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.EventRaiser;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.player.Player;

/**
 * Builds an npc to sign up to the Deniran Institute of Technology.
 *
 * @author Adrian Szvoren
 */
public class SigneeNPC implements ZoneConfigurator {

	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildNPC(zone);
	}

	private void buildNPC(final StendhalRPZone zone) {
			final SpeakerNPC npc = new SpeakerNPC("Signee") {

			@Override
			protected void createPath() {
				setPath(null);
			}

			@Override
			protected void createDialog() {
				addGreeting("Hi. I wish I could learn #Java.");
				addJob("I have no work because I am not educated.");
				addQuest("I hope that we can attend a university some day.");
				addGoodbye("Bye, enjoy your day!");

			}

			@Override
			protected void onGoodbye(RPEntity player) {
				setDirection(Direction.DOWN);
			}
		};

		npc.setEntityClass("boynpc");
		npc.setPosition(98, 119);
		npc.initHP(100);
		npc.setDescription("This boy wishes he could study some computers.");
		zone.add(npc);
	}
}
