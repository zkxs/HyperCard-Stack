package engine;

import static org.lwjgl.opengl.GL11.*;
import engine.renderer.*;


public class Game {
	
	Renderer renderer;
	
	public Game() {
		
	}
	
	public void init(){
		renderer = new OpenGLRenderer();
	}
	
	public void update(){
		
	}
	
	public void draw(){
		renderer.setColor(.5f, .5f, .5f);
		renderer.fillRect(10f, 10f, 100f, 100f);
	}
}