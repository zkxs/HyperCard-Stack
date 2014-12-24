package editor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.jogamp.opengl.util.FPSAnimator;


@SuppressWarnings("serial")
public class EditorEngine extends JFrame implements GLEventListener{
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final String WINDOW_TITLE = "HyperCard Stack Editor";
	
	public static final int GL_WIDTH = 1100;

	private GLJPanel gljpanel;
	private GLProfile glprofile;
	private GLCapabilities glcapabilities;
	private GL2 gl;
	private GLU glu;
	private FPSAnimator fpsanimator;
	private Editor editor;
	private ToolsPalette toolsPalette;
	
	private boolean picking;

	public static void main(String[] args) {
		new EditorEngine();
	}

	public EditorEngine(){
		super(WINDOW_TITLE);
		init();
	}
    
    
    public void init()
    {
    	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    	getContentPane().add(splitPane);
    	editor = new Editor();
    	
    	toolsPalette = new ToolsPalette(editor);
    	editor.init(toolsPalette);
    	
    	glprofile = GLProfile.getDefault();
    	glcapabilities = new GLCapabilities(glprofile);
    	gljpanel = new GLJPanel(glcapabilities);
    	fpsanimator = new FPSAnimator(gljpanel, 60);
    	gljpanel.addGLEventListener(this);
    	
    	splitPane.setLeftComponent(gljpanel);
    	splitPane.setRightComponent(toolsPalette);
    	
    	splitPane.setDividerLocation(GL_WIDTH);
    	splitPane.setEnabled(false);
    	
    	
    	addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent windowevent ) {
				remove( gljpanel );
				dispose();
				System.exit( 0 );
			}
		});
    	
    	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    	setVisible(true);
    	gljpanel.requestFocus();
    }
	
	@Override
	public void init( GLAutoDrawable glautodrawable )
	{
		gl = glautodrawable.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0f); //set to non-transparent black
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		
	}
	
	public void updateLoop(){
		fpsanimator.start();
	}


	@Override
	public void display(GLAutoDrawable glautodrawable) {
		editor.update();
		
		if(!picking){
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrtho(0, GL_WIDTH, WINDOW_HEIGHT, 0, 0, 1);
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			gl.glMatrixMode(GL2.GL_MODELVIEW);
			gl.glLoadIdentity();
			editor.draw(this, gl);
		}
		else{
			
		}
	}

	@Override
	public void dispose(GLAutoDrawable glautodrawable) {
		
	}

	public void destroy(){
		
	}

	@Override
	public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
		
	}
	
	public void setColor(float red, float green, float blue)
	{
		gl.glColor3f(red, green, blue);
	}
	
	public void fillRect(float x, float y, float width, float height)
	{
		gl.glBegin(GL2.GL_QUADS);
		{
			gl.glVertex2f(x, y);
			gl.glVertex2f(x, y + height);
			gl.glVertex2f(x + width, y + height);
			gl.glVertex2f(x + width, y);
			
		}
		gl.glEnd();
	}
}
