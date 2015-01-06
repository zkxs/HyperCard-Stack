package common.gameelements;

import java.util.Hashtable;
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
	private Hashtable<String, View> views; //TODO: add functionality
	
	/**
	 * Standard constructor
	 * @param identifier A unique identifier for this location
	 * @param position the position of this Location (will be deep copied)
	 */
	public Location(String identifier, Vector position)
	{
		this.identifier = identifier;
		position = new Vector();
		setPosition(position);
	}

	/**
	 * Create a new Location at <0, 0, 0>
	 * @param identifier A unique identifier for this location
	 */
	public Location(String identifier)
	{
		this(identifier, 0, 0, 0);
	}
	
	/**
	 * Default constructor. This will create a view with default values
	 * that should be changed later by the level editor
	 */
	public Location()
	{
		this("New Location " + locationCounter);
		locationCounter++;
	}
	
	/**
	 * Create a new vector at <x, y, z>
	 * @param identifier A unique identifier for this location
	 * @param x The x component
	 * @param y The y component
	 * @param z The z component
	 */
	public Location(String identifier, float x, float y, float z)
	{
		this.identifier = identifier;
		position = new Vector();
		setPosition(x, y, z);
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
		View toReturn = views.get(id);
		if (toReturn == null)
			throw new ViewNotFoundException(id);
		else
			return toReturn;
	}
	
	/**
	 * Add a new View to this Location
	 * @param view The view to add
	 * @throws DuplicateViewException if a view with the same unique ID already exists
	 */
	public void addView(View view)
	{
		boolean present = views.contains(view.getIdentifier());
		if (present)
		{
			// then a view with this ID already exists!
			throw new DuplicateViewException(view.getIdentifier());
		}
		else
		{
			// we must add the new view
			views.put(view.getIdentifier(), view); // should always return null
		}
	}
	
	/**
	 * Remove a view from this map. This may cause map errors if links to this view
	 * exist elsewhere.
	 * @param id The unique ID of the view to remove
	 * @throws ViewNotFoundException if the view id was not valid
	 */
	public void removeView(String id)
	{
		View removed = views.remove(id);
		if (removed == null)
		{
			// nothing was removed!
			throw new ViewNotFoundException(id);
		}
	}
	
	/**
	 * Get an iterator over all views in this Location. The iterator supports element
	 * removal via the .remove() method. The .add() method is NOT supported. If Location.views
	 * is modified during iteration, the results of the iteration are undefined.
	 * @return an Iterator over all Views in the Location.
	 */
	public Iterator<View> getViewIterator()
	{
		return views.values().iterator();
	}
}
