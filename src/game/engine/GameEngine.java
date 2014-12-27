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
		game = new Game();
        game.init();
        
		renderer = new OpenGLRenderer();
		renderer.init(game);
	}
	
	public void run()
	{
		renderer.updateLoop();
		renderer.destroy();
	}
}