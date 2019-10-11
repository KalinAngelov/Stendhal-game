/* $Id$
 /***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.item.scroll;

import java.util.Map;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.creature.Pet;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
//import games.stendhal.server.entity.player.PetOwner;;


/**
 * Represents an empty/blank pet scroll.
 */
public class BlankPetScroll extends Scroll {
	
	// Create a hashMap

	// private static final Logger logger = Logger.getLogger(EmptyScroll.class);

	/**
	 * Creates a new blank pet scroll.
	 *
	 * @param name
	 * @param clazz
	 * @param subclass
	 * @param attributes
	 */
	public BlankPetScroll(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}

	/**
	 * Copy constructor.
	 *
	 * @param item
	 *            item to copy
	 */
	public BlankPetScroll(final BlankPetScroll item) {
		super(item);
	}
    
	// use string and int to store the pet's information
	private static String storedPetName;
	private static int storedPetBaseHp;
	private static int storedPetHp;
	private static int storedPetXp;
	private static int storedPetWeight;
	private static int storedPetAtk;
	private static int storedPetDef;
	private static int storedPetLevel;
	
	// return pet's information
	public static String getPetName()
	{
		return storedPetName;
	}
	public static int getPetBaseHp()
	{
		return storedPetBaseHp;
	}
	public static int getPetHp()
	{
		return storedPetHp;
	}
	public static int getPetXp()
	{
		return storedPetXp;
	}
	public static int getPetWeight()
	{
		return storedPetWeight;
	}
	public static int getPetAtk()
	{
		return storedPetAtk;
	}
	public static int getPetDef()
	{
		return storedPetDef;
	}
	public static int getPetLevel()
	{
		return storedPetLevel;
	}
	/**
	 * Use a blank pet scroll.
	 *
	 * @param player
	 * @return always true
	 */
	@Override
	protected boolean useScroll(final Player player) {
		final StendhalRPZone zone = player.getZone();
		final String petName = player.getPet().getName();

		if (zone.isTeleportInAllowed(player.getX(), player.getY())) {
			final Item summonPetScroll = SingletonRepository.getEntityManager().getItem(
					"summon pet scroll");
			summonPetScroll.setInfoString(petName);
			player.equipOrPutOnGround(summonPetScroll);
			final Pet pet = player.getPet();
			// get pet's information before removing the pet
            storedPetName = pet.getTitle();
            storedPetBaseHp = pet.getBaseHP();
            storedPetHp = pet.getHP();
            storedPetXp = pet.getXP();
            storedPetWeight = pet.getWeight();
            storedPetAtk = pet.getAtk();
            storedPetDef = pet.getDef();
            storedPetLevel = pet.getLevel();
			
			if (pet != null) {
				//petOwner.storePet(pet);
				player.removePet(pet);
				pet.getZone().remove(pet);
				player.sendPrivateText("Amazingly your pet melds with the scroll. It just walked right into the page!");
			}

			return true;
		} else {
			player.sendPrivateText("The strong anti magic aura in this area prevents the scroll from working!");
			return false;
		}
	}

}
