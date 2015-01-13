package editor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
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
import com.jogamp.opengl.util.GLBuffers;
import common.gameelements.Location;
import common.gameelements.View;


@SuppressWarnings("serial")
public class EditorEngine extends JFrame implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener{
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
	private JSplitPane splitPane;
	private boolean picking;
	
	//used for panning the camera
	int mouseX, mouseY, oldMouseX, oldMouseY;
    int oldXOffset, oldYOffset;
    int xOffset, yOffset;
    
    int zoom = 1;

	public static void main(String[] args) {
		new EditorEngine();
	}

	public EditorEngine(){
		super(WINDOW_TITLE);
		init();
	}
    
    public void init()
    {
    	splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
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
    	
    	gljpanel.addMouseListener(this);
    	gljpanel.addMouseMotionListener(this);
    	gljpanel.addMouseWheelListener(this);
    	
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
			displayScene(gl);
		}
		else{
			int buffersize = 256;
			int[] viewPort = new int[4];
			IntBuffer selectBuffer = GLBuffers.newDirectIntBuffer(buffersize);
			int hits = 0;
			gl.glGetIntegerv(GL.GL_VIEWPORT, viewPort, 0);
			gl.glSelectBuffer(buffersize, selectBuffer);
			gl.glRenderMode(GL2.GL_SELECT);
			gl.glInitNames();
			gl.glPushName(-1);
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glPushMatrix();
			gl.glLoadIdentity();
			glu.gluPickMatrix(mouseX,  mouseY, 10.0d, 10.0d, viewPort, 0);
			displayScene(gl);
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glPopMatrix();
			gl.glFlush();
			hits = gl.glRenderMode(GL2.GL_RENDER);
			processHits(hits, selectBuffer);
			picking = false;
		}
	}
	
	public void displayScene(GL2 gl){
		if(!picking){
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrtho(0, GL_WIDTH / zoom, 0, WINDOW_HEIGHT / zoom, 0, 1);
		}
		else
			gl.glOrtho(0, GL_WIDTH / zoom, WINDOW_HEIGHT / zoom, 0, 0, 1);

		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glTranslatef(xOffset / zoom, -yOffset / zoom, 0); //pan the camera
		editor.draw(this, gl);
	}

	@Override
	public void dispose(GLAutoDrawable glautodrawable) {
		
	}

	public void destroy(){
		
	}

	@Override
	public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
		splitPane.setDividerLocation(getWidth() - ToolsPalette.PALETTE_WIDTH);
		
		double AR = (float)GL_WIDTH / WINDOW_HEIGHT;
		if (AR*height<width) gl.glViewport(x, y, (int) (AR*height), height);
		else gl.glViewport(x, y, width, (int) (width/AR));
	}
	
	public void processHits(int hits, IntBuffer buffer){
		if(hits == 0){
			editor.selectNothing();
			return;
		}
		int offset = 0;
		int names;
		long z1, z2;
		for (int i=0;i<hits;i++)
		{
			names = buffer.get(offset); offset++;
			z1 = ((long) buffer.get(offset) & 0xffffffffl); 
			offset++;
			z2 = ((long) buffer.get(offset) & 0xffffffffl); 
			offset++;
			//z1 and z2 are the entrance and exit depth

			for (int j=0;j<names;j++){
				//buffer.get(offset) is the id of the hit object
				editor.selectLocationOrView(buffer.get(offset));
				offset++;
			}
		}
	}
	
	public void drawAxes(){
		gl.glLoadName(-1);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3f(-10, 0, 0);
		gl.glVertex3f(10, 0, 0);
		gl.glEnd();
		
		gl.glLoadName(-1);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3f(0, -10, 0);
		gl.glVertex3f(0, 10, 0);
		gl.glEnd();
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
	
	/**
	 * draw a circle representing a location at the coordinates specified
	 * @param loc the location to draw
	 */
	public void drawLocation(Location loc){
		float radius = 5f;
		float diag = 3.5f;
		double x = loc.getPosition().getX();
		double y = loc.getPosition().getY();
		double z = loc.getPosition().getZ();
		String id = loc.getIdentifier();
		gl.glLoadName(editor.locationIntIds.get(id)); // for picking
		if(picking)
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
		else
			gl.glBegin(GL2.GL_LINE_LOOP);
		{
			gl.glVertex3d(x, y + radius, z);
			gl.glVertex3d(x + diag, y + diag, z);
			gl.glVertex3d(x + radius, y, z);
			gl.glVertex3d(x + diag, y - diag, z);
			gl.glVertex3d(x, y - radius, z);
			gl.glVertex3d(x - diag, y - diag, z);
			gl.glVertex3d(x - radius, y, z);
			gl.glVertex3d(x - diag, y + diag, z);
		}
		if(!picking){
			gl.glEnd();

			gl.glBegin(GL2.GL_POINTS);
		}
		
		gl.glVertex3d(x, y, z);
		
		gl.glEnd();
	}
	
	/**
	 * draw a line representing a view at a location pointing in a direction
	 * @param loc the view's location
	 * @param vw the view to be draw
	 */
	public void drawView(Location loc, View vw){
		float radius = 10f;
		float half_width = 8f;
		double locationx = loc.getPosition().getX();
		double locationy = loc.getPosition().getY();
		double locationz = loc.getPosition().getZ();
		double theta = vw.getOrientation().getTheta();
		double phi = vw.getOrientation().getPhi();
		//proof that Will knows OpenGL
		String id = vw.getIdentifier();
		gl.glLoadName(editor.viewIntIds.get(id)); //for picking
		gl.glTranslated(locationx, locationy, locationz);
		gl.glRotated(-theta, 0, 0, 1);
		gl.glBegin(GL2.GL_LINE_STRIP);
		{
			gl.glVertex3d(-half_width, radius, locationz);
			gl.glVertex3d(0, radius, locationz);
			gl.glVertex3d(half_width, radius, locationz);
		}
		gl.glEnd();
		gl.glRotated(theta, 0, 0, 1);
		gl.glTranslated(-locationx, -locationy, -locationz);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		float dx = e.getX() - oldMouseX; // change in mouse position
		float dy = e.getY() - oldMouseY;
		if(e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK){ // panning
			xOffset = (e.getX() - mouseX) + oldXOffset;
			yOffset = (e.getY() - mouseY) + oldYOffset;
		}
		else if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK && editor.getMapSelection() == Editor.MapSelection.LOCATION){ // dragging location
			editor.setX(editor.getX() + dx / zoom);
			editor.setY(editor.getY() - dy / zoom);
		}
		oldMouseX = e.getX();
		oldMouseY = e.getY();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			picking = true;
			mouseX = e.getX();
			mouseY = e.getY();
			oldMouseX = e.getX(); //for calculating dx when dragging location
			oldMouseY = e.getY();
		}
		else if(e.getButton() == MouseEvent.BUTTON3){
			mouseX = e.getX();
			mouseY = e.getY();
		}
		else if(e.getButton() == MouseEvent.BUTTON2){ // reset pan to origin
			xOffset = 0;
			yOffset = 0;
			oldXOffset = 0;
			oldYOffset = 0;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){ // save pan when release right mouse
			oldXOffset = xOffset;
			oldYOffset = yOffset;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() < 0 && zoom < 4){
			zoom += zoom;
		}
		else if(e.getWheelRotation() > 0){
			zoom -= zoom / 2;
		}
	}
}
