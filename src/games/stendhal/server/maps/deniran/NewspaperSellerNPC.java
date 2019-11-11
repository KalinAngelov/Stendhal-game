package games.stendhal.server.maps.deniran;

import java.util.Map;
import java.util.LinkedList;
import java.util.List;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.SpeakerNPC;
public class NewspaperSellerNPC implements ZoneConfigurator {


	@Override
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildNPC(zone);
	}

	
	private void buildNPC(final StendhalRPZone zone) {
	    final SpeakerNPC npc = new SpeakerNPC("Tom") {
	    	
        @Override
		protected void createPath() {
            List<Node> nodes=new LinkedList<Node>();
            nodes.add(new Node(30,31));
            nodes.add(new Node(34,31));
            nodes.add(new Node(34,28));
            nodes.add(new Node(30,28));
            setPath(new FixedPath(nodes, true));
        }

        @Override
		protected void createDialog() {
            // Lets the NPC reply with "Hello my friend!" when a player greets him. But we could have set a custom greeting inside the ()
            addGreeting("Hello my friend!");
            // Lets the NPC reply when a player says "job"
            addJob("I'm a newspaper salesman, but unfortunately our newspaper has not been released so I am here to promote.");
            // Lets the NPC reply when a player asks for help
            addHelp("Ask me about #newspaper and I will tell you about the new newspapers.");
            // respond about the newspaper if asked about it
            addReply("newspaper","The newspaper coming  will tell stories of the biggest accomplishments across the Stendhal world!");
            // Let the NPC say goodbye
            addGoodbye("Hope to see you again soon!");
        }
    };

    // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    npc.setEntityClass("welcomernpc");
    // set a description for when a player does 'Look'
    npc.setDescription("You see a newspaper seller, he looks excited about something.");
    // Set the initial position to be the first node on the Path you defined above.
    npc.setPosition(30,31);
    npc.initHP(100);

    zone.add(npc);   
    }


}
