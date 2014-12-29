package common.gameelements;

import java.util.Hashtable;

import common.Vector;

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
		position = new Vector(3);
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
	 * Create a new vector at the given location
	 * @param identifier A unique identifier for this location
	 * @param positionComponents a length 3 array of position components (will be shallow copied)
	 */
	public Location(String identifier, double... positionComponents)
	{
		this.identifier = identifier;
		position = new Vector(3);
		setPosition(positionComponents);
	}
	
	/**
	 * Set the position of this vector to that of the given vector (deep copy)
	 * @param position the vector to extract the position from
	 */
	public void setPosition(Vector position)
	{
		position.setComponents(position);
	}
	
	/**
	 * Set this location's components to the given components (shallow copy)
	 * @param positionComponents the components to set the position to
	 */
	public void setPosition(double... positionComponents)
	{
		position.setComponents(positionComponents);
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
}
