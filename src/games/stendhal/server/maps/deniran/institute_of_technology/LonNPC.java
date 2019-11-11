package games.stendhal.server.maps.deniran.institute_of_technology;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import java.util.Map;

public class LonNPC implements ZoneConfigurator{
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
	    final SpeakerNPC npc = new SpeakerNPC("Lon Jatham") {
	    	@Override
	    	protected void createPath() {
	            // NPC does not move
	            setPath(null);
	        }
        @Override
        protected void createDialog() {
            addGreeting("Hello, I am Lon!");
            addGoodbye("See you!");
        }
    };

 // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    npc.setEntityClass("beggarnpc");
    // set a description for when a player does 'Look'
    npc.setDescription("You see the popular Java lecturer, Dr Lon Jatham, he is recruiting students, maybe you can help.");
    npc.setPosition(13, 9);
    npc.initHP(100);

    zone.add(npc);
  }

}