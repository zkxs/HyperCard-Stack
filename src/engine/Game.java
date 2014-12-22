package engine;

import static org.lwjgl.opengl.GL11.*;


public class Game {
	public Game(){
		
	}
	
	public void init(){
		
	}
	
	public void update(){
		
	}
	
	public void draw(){
		glColor3f(.5f, .5f, .5f);
		glBegin(GL_QUADS);
		{
			glVertex2f(0f, 0f);
			glVertex2f(0f, 100f);
			glVertex2f(100f, 100f);
			glVertex2f(100f, 0f);
			
		}
		glEnd();
	}
}