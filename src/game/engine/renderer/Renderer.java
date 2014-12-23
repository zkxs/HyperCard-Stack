package game.engine.renderer;

import game.engine.Game;

/**
 * Defines methods used for rendering
 */
public interface Renderer
{	
	// drawImage
	// drawRect
	// etc
	
	/**
	 * Initialize the renderer.
	 */
	public void init();
	
	
	public void updateLoop(Game game);
	
	
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
