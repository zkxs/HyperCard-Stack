package editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ToolsPalette extends JPanel implements ActionListener{
	private JButton createView, deleteView, editView;
	private JButton createLocation, deleteLocation;
	private JButton setX, setY, setZ;
	private JTextField textX, textY, textZ;
	private ArrayList<JButton> buttons;
	public static final int PALETTE_WIDTH = EditorEngine.WINDOW_WIDTH - EditorEngine.GL_WIDTH;
	private Editor editor;
	
	public ToolsPalette(Editor ed){
		editor = ed;
		buttons = new ArrayList<JButton>();
		
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		
		setLayout(boxLayout);
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		createView = new JButton("Create View");
		buttons.add(createView);
		deleteView = new JButton("Delete View");
		buttons.add(deleteView);
		editView = new JButton("Edit View");
		buttons.add(editView);
		
		createLocation = new JButton("Create Location");
		buttons.add(createLocation);
		deleteLocation = new JButton("Delete Location");
		buttons.add(deleteLocation);
		
		setX = new JButton("Set X");
		buttons.add(setX);
		setY = new JButton("Set Y");
		buttons.add(setY);
		setZ = new JButton("Set Z");
		buttons.add(setZ);
		
		for(JButton b : buttons){
			b.addActionListener(this);
		}
		
		textX = new JTextField();
		textY = new JTextField();
		textZ = new JTextField();
	}
	
	/**
	 * nothing selected, show buttons:
	 * 	-create location
	 */
	public void setNothingSelected(){
		removeAll();
		add(createLocation);
		
		revalidate();
		repaint();
	}
	
	
	/**
	 * location selected, show buttons:
	 * 	-create view
	 * 	-delete location
	 * 	-set x
	 * 	-set y
	 * 	-set z
	 */
	public void setLocationSelected(){
		removeAll();
		add(createView);
		add(deleteLocation);
		add(setX);
		add(setY);
		add(setZ);
		textX.setText("X: " + editor.getX());
		textX.setMaximumSize(new Dimension(PALETTE_WIDTH - 20, 20));
		add(textX);
		
		textY.setText("Y: " + editor.getY());
		textY.setMaximumSize(new Dimension(PALETTE_WIDTH - 20, 20));
		add(textY);
		
		textZ.setText("Z: " + editor.getZ());
		textZ.setMaximumSize(new Dimension(PALETTE_WIDTH - 20, 20));
		add(textZ);
		
		
		revalidate();
		repaint();
	}
	
	/**
	 * view selected, show buttons:
	 * 	-delete view
	 * 	-edit view
	 */
	public void setViewSelected(){
		removeAll();
		add(deleteView);
		add(editView);
		
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == createLocation){
			editor.createLocation();
		}
		else if(source == deleteLocation){
			editor.deleteLocation();
		}
		else if(source == createView){
			editor.createView();
		}
		else if(source == deleteView){
			editor.deleteView();
		}
		else if(source == editView){
			editor.editView();
		}
		else if(source == setX){
			editor.setX(Integer.parseInt(JOptionPane.showInputDialog("Enter X Coordinate")));
		}
		else if(source == setY){
			editor.setY(Integer.parseInt(JOptionPane.showInputDialog("Enter Y Coordinate")));
		}
		else if(source == setZ){
			editor.setZ(Integer.parseInt(JOptionPane.showInputDialog("Enter Z Coordinate")));
		}
	}
}
