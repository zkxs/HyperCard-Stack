package common.gameelements;

import java.util.HashMap;

import common.Vector;

/**
 * Describes a location at which there are views
 */
public class Location
{	
	private Vector position;
	private HashMap<String, View> views;
	
	/**
	 * Standard constructor
	 * @param position the position of this Location (will be deep copied)
	 */
	public Location(Vector position)
	{
		position = new Vector(3);
		setPosition(position);
	}

	/**
	 * Create a new Location at <0, 0, 0>
	 */
	public Location()
	{
		this(0, 0, 0);
	}
	
	/**
	 * Create a new vector at the given location
	 * @param positionComponents a length 3 array of position components (will be shallow copied)
	 */
	public Location(double... positionComponents)
	{
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
}
