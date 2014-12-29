package common.gameelements;

import java.util.HashMap;

import common.gameelements.exceptions.*;

/**
 * Contains all object relevant to the games map. This includes
 * all locations and puzzlesControllers.
 */
public class Map
{
	private HashMap<String, Location> locations;
	private HashMap<String, PuzzleController> puzzleControllers;
	
	/**
	 * Look up a Location from its unique ID
	 * @param id The unique location ID to look up
	 * @return The found Location object
	 * @throws LocationNotFoundException if the location id was not valid
	 */
	public Location getLocation(String id)
	{
		Location toReturn = locations.get(id);
		if (toReturn == null)
			throw new LocationNotFoundException(id);
		else
			return toReturn;
	}
	
	/**
	 * Look up a PuzzleController from its unique ID
	 * @param id The unique puzzle controller ID to look up
	 * @return The found PuzzleController object
	 * @throws PuzzleControllerNotFoundException if the puzzle controller id was not valid
	 */
	public PuzzleController getPuzzleController(String id)
	{
		PuzzleController toReturn = puzzleControllers.get(id);
		if (toReturn == null)
			throw new PuzzleControllerNotFoundException(id);
		else
			return toReturn;
	}
}
