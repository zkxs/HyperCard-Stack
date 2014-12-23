package game.engine.renderer;

import game.engine.Game;

/**
 * Defines methods used for rendering
 */
public interface Renderer
{	
	/**
	 * Initialize the renderer.
	 */
	public void init(Game game);
	
	/**
	 * Calls game.draw() in a loop
	 * @param game
	 */
	public void updateLoop();
	
	/**
	 * Shuts down the renderer and closes the window
	 */
	public void destroy();
	
	/**
	 * Set the drawing color
	 * @param red red component
	 * @param green green component
	 * @param blue blue component
	 */
	public void setColor(float red, float green, float blue);
	
	/**
	 * 
	 * @param x x coordinate of upper left corner
	 * @param y y coordinate of upper left corner
	 * @param width width of rectangle
	 * @param height height of rectangle
	 */
	public void fillRect(float x, float y, float width, float height);
}
