package game.engine;

import game.engine.renderer.*;


public class Game {
	
	Renderer renderer;
	
	public Game(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public void init() {
		
	}
	
	public void update() {
		
	}
	
	public void draw() {
		renderer.setColor(.5f, .5f, .5f);
		renderer.fillRect(10f, 10f, 100f, 100f);
	}
}