package game.engine.renderer;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLRenderer implements Renderer
{	
	public void setColor(float red, float green, float blue)
	{
		glColor3f(red, green, blue);
	}
	
	public void fillRect(float x, float y, float width, float height)
	{
		glBegin(GL_QUADS);
		{
			glVertex2f(x, y);
			glVertex2f(x, y + height);
			glVertex2f(x + width, y + height);
			glVertex2f(x + width, y);
			
		}
		glEnd();
	}
}
