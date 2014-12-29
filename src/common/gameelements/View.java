package common.gameelements;

import game.engine.renderer.Renderer;

/**
 * A single view. A view is what is displayed on the screen when the user is standing
 * at a Location looking in a specific direction
 */
public class View
{	
	/**
	 * Used to name new default view instances.
	 * This is not a thread-safe solution.
	 */
	private static int viewCounter = 1;
	
	/** The orientation of this view */
	private Orientation orientation;
	
	/** This view's unique identifier */
	private String identifier;
	
	/**
	 * Constructor that specifies all fields
	 * @param orientation The orientation of this view
	 * @param identifier This view's unique identifier
	 */
	public View(
			Orientation orientation,
			String identifier
		) { // I listed the args like this as I envision more args being added later
		this.orientation = orientation;
		this.identifier = identifier;
		viewCounter++;
	}
	
	/**
	 * Default constructor. This will create a view with default values
	 * that should be changed later by the level editor
	 */
	public View()
	{
		this(new Orientation(), "New View " + viewCounter);
	}
	
	/**
	 * Get this view's unique identifier
	 * @return this view's unique identifier
	 */
	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * Set this view's unique identifier
	 * @param identifier this view's new unique identifier
	 */
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	/**
	 * Draw this view
	 * @param r The renderer to draw with
	 */
	public void draw(Renderer r)
	{
		// how to do this from a script?
		// should background images be a default thing?
	}
	
	public static class Orientation
	{
		private float theta;
		private float phi;
		
		/**
		 * Get the angle from North of this Orientation
		 * @return the angle from North of this Orientation
		 */
		public float getTheta() { return theta; }
		
		/**
		 * Get the angle from horizontal of this Orientation
		 * @return the angle from horizontal of this Orientation
		 */
		public float getPhi() { return phi; }
		
		/**
		 * Set the angle from North of this Orientation
		 * @param theta the angle from North of this Orientation
		 */
		public void setTheta(float theta) { this.theta = theta; }
		
		/**
		 * Set the angle from horizontal of this Orientation
		 * @param phi the angle from horizontal of this Orientation
		 */
		public void setPhi(float phi) { this.phi = phi; }
	}
	
	/**
	 * Get the orientation of this View
	 * @return the orientation object which can be used to get and set theta and phi
	 */
	public Orientation getOrientation(){ return orientation; }
}