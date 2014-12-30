package common.gameelements;

import java.util.Hashtable;

import common.Vector;
import common.VectorNew;

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
	private VectorNew position;
	private Hashtable<String, View> views; //TODO: add functionality
	
	/**
	 * Standard constructor
	 * @param identifier A unique identifier for this location
	 * @param position the position of this Location (will be deep copied)
	 */
	public Location(String identifier, VectorNew position)
	{
		this.identifier = identifier;
		position = new VectorNew();
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
		position = new VectorNew();
		setPosition(x, y, z);
	}
	
	/**
	 * Set the position of this vector to that of the given vector
	 * @param position the vector to extract the position from
	 */
	public void setPosition(VectorNew position)
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
	public VectorNew getPosition()
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
