package common.gameelements;

import java.util.HashMap;

import common.Vector;

public class Location
{	
	private Vector position;
	private HashMap<String, View> views;
	
	/**
	 * Standard constructor
	 * @param position the position of this Location
	 */
	public Location(Vector position)
	{
		position.checkDimensions(3);
		this.position = position;
	}

	public Location()
	{
		this(0, 0, 0);
	}
	
	public Location(double... positionComponents)
	{
		this(new Vector(positionComponents));
	}
	
}
