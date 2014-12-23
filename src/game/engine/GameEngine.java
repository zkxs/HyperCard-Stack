package game.engine;

import game.engine.renderer.*;

public class GameEngine {
	
	private Game game;
	private Renderer renderer;
	
	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		engine.run();
	}
	
	public GameEngine(){
		renderer = new OpenGLRenderer();
		renderer.init();
		 
		game = new Game();
        game.init();
	}
	
	public void run()
	{
		renderer.updateLoop(game);
		renderer.destroy();
	}
}