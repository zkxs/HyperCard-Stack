package common.gameelements;

import java.util.Hashtable;
import java.util.Iterator;

import common.gameelements.exceptions.*;

/**
 * Contains all object relevant to the games map. This includes
 * all locations and puzzlesControllers.
 */
public class GameMap
{
	private Hashtable<String, Location> locations;
	private Hashtable<String, PuzzleController> puzzleControllers;
	
	public GameMap()
	{
		locations = new Hashtable<String, Location>();
		puzzleControllers = new Hashtable<String, PuzzleController>();
	}
	
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
	 * Add a new Location to this GameMap
	 * @param location The location to add
	 * @throws DuplicateLocationException if a location with the same unique ID already exists
	 */
	public void addLocation(Location location)
	{
		boolean present = locations.contains(location.getIdentifier());
		if (present)
		{
			// then a location with this ID already exists!
			throw new DuplicateLocationException(location.getIdentifier());
		}
		else
		{
			// we must add the new location
			locations.put(location.getIdentifier(), location); // should always return null
		}
		
		// end method
		
		/* The following code works in Java 8+ and uses only one hashtable lookup:
		Location currentValue = locations.putIfAbsent(location.getIdentifier(), location);
		if (currentValue != null)
		{
			// then a location with this ID allready exists!
			throw new DuplicateLocationException(location.getIdentifier());
		}
		// otherwise it worked and all is good
		*/
	}
	
	/**
	 * Remove a location from this map. This may cause map errors if links to this location
	 * exist elsewhere.
	 * @param id The unique ID of the location to remove
	 * @throws LocationNotFoundException if the location id was not valid
	 */
	public void removeLocation(String id)
	{
		Location removed = locations.remove(id);
		if (removed == null)
		{
			// nothing was removed!
			throw new LocationNotFoundException(id);
		}
	}
	
	/**
	 * Get an iterator over all locations in this GameMap. The iterator supports element
	 * removal via the .remove() method. The .add() method is NOT supported. If GameMap.locations
	 * is modified during iteration, the results of the iteration are undefined.
	 * @return an Iterator over all Locations in the GameMap.
	 */
	public Iterator<Location> getLocationIterator()
	{
		return locations.values().iterator();
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