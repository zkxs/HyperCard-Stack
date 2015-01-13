package common.gameelements;

import java.util.HashSet;
import java.util.Iterator;

import common.Vector;
import common.gameelements.exceptions.*;

/**
 * Describes a location at which there are views
 */
public class Location
{
	/**
	 * Used to name new default location instances.
	 * This is not a thread-safe solution.
	 */
	private static int locationCounter = 1;
	
	/** This view's unique identifier */
	private String identifier;
	private Vector position;
	private HashSet<View> views;
	
	private GameMap map; // reference back to game map to access allViews
	
	/**
	 * Standard constructor
	 * @param identifier A unique identifier for this location
	 * @param position the position of this Location (will be deep copied)
	 * @param map a reference to the game map
	 */
	public Location(String identifier, Vector position, GameMap map)
	{
		views = new HashSet<View>();
		this.identifier = identifier;
		this.map = map;
		position = new Vector(position);
	}

	/**
	 * Create a new Location at <0, 0, 0>
	 * @param identifier A unique identifier for this location
	 * @param map a reference to the game map
	 */
	public Location(String identifier, GameMap map)
	{
		this(identifier, 0, 0, 0, map);
	}
	
	/**
	 * Default constructor. This will create a view with default values
	 * that should be changed later by the level editor
	 * @param map a reference to the game map
	 */
	public Location(GameMap map)
	{
		this("New Location " + locationCounter, map);
		locationCounter++;
	}
	
	/**
	 * Create a new vector at <x, y, z>
	 * @param identifier A unique identifier for this location
	 * @param x The x component
	 * @param y The y component
	 * @param z The z component
	 */
	public Location(String identifier, float x, float y, float z, GameMap map)
	{
		views = new HashSet<View>();
		this.identifier = identifier;
		this.map = map;
		position = new Vector(x, y, z);
	}
	
	/**
	 * Set the position of this vector to that of the given vector
	 * @param position the vector to extract the position from
	 */
	public void setPosition(Vector position)
	{
		position.set(position);
	}
	
	/**
	 * Set this location's components to the given components (shallow copy)
	 * @param positionComponents the components to set the position to
	 */
	/**
	 * Set the components of this vector to <x, y, z>
	 * @param x The new x component
	 * @param y The new y component
	 * @param z The new z component
	 */
	public void setPosition(float x, float y, float z)
	{
		position.set(x, y, z);
	}
	
	/**
	 * Get this locations's position
	 * @return this locations's position
	 */
	public Vector getPosition()
	{
		return position;
	}
	
	/**
	 * Get this location's unique identifier
	 * @return this location's unique identifier
	 */
	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * Set this location's unique identifier
	 * @param identifier this location's new unique identifier
	 */
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
	
	/**
	 * Look up a View from its unique ID
	 * @param id The unique view ID to look up
	 * @return The found View object
	 * @throws ViewNotFoundException if the view id was not valid
	 */
	public View getView(String id)
	{
		return map.getView(id);
	}
	
	/**
	 * Add a new View to this Location
	 * @param view The view to add
	 * @throws DuplicateViewException if a view with the same unique ID already exists
	 */
	public void addView(View view)
	{
		map.addView(view);
		views.add(view);
	}
	
	/**
	 * Remove a view from this location. This may cause map errors if links to this view
	 * exist elsewhere.
	 * @param id The unique ID of the view to remove
	 * @throws ViewNotFoundException if the view id was not valid
	 */
	public void removeView(String id)
	{
		View removed = map.removeView(id);
		views.remove(removed);
	}
	
	/**
	 * Remove all views from this location
	 */
	public void removeAllViews()
	{
		Iterator<View> iterator = getViewIterator();
		while (iterator.hasNext())
		{
			View view = iterator.next();
			map.removeView(view.getIdentifier());
			views.remove(view);
		}
	}
	
	/**
	 * Get an iterator over all views in this Location. The iterator supports element
	 * removal via the .remove() method. The .add() method is NOT supported. If Map.views
	 * is modified during iteration, the results of the iteration are undefined.
	 * @return an Iterator over all Views in the Location.
	 */
	public Iterator<View> getViewIterator()
	{
		return views.iterator();
	}
}
