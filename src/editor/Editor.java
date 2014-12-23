package editor;

import javax.media.opengl.GL2;

import common.gameelements.Map;

public class Editor {
	private Map map;
	
	public Editor(){
	}
	
	public void init(){
		
	}
	
	public void update(){
		
	}
	
	public void draw(EditorEngine e, GL2 gl){
		int x = 0;
		int y = 0;
		int width = 100;
		int height = 100;
		gl.glColor3f(1.0f, 0, 0f);
		gl.glBegin(GL2.GL_QUADS);
		{
			gl.glVertex2f(x, y);
			gl.glVertex2f(x, y + height);
			gl.glVertex2f(x + width, y + height);
			gl.glVertex2f(x + width, y);
			
		}
		gl.glEnd();
	}
	
	public void destroy(){
		
	}
}
