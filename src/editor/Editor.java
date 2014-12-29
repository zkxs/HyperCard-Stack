package editor;

import javax.media.opengl.GL2;
import java.util.Hashtable;
import java.util.Iterator;

import common.gameelements.Location;

import common.gameelements.GameMap;

public class Editor {
	private ToolsPalette toolsPalette;
	private GameMap map;
	private enum MapSelection {
		NONE,
		LOCATION,
		VIEW
	}
	
	private enum EditMode {
		EDIT_MAP, //editing locations and views
		EDIT_VIEW //editing navAreas and manipulators
	}
	
	private MapSelection selection;
	private EditMode editMode;
	
	public Editor(){
	}
	
	public void init(ToolsPalette tools){
		map = new GameMap();
		toolsPalette = tools;
		selection = MapSelection.NONE;
		editMode = EditMode.EDIT_MAP;
	}
	
	public void update(){
		if(editMode == EditMode.EDIT_MAP){
			switch(selection){
			case NONE:
				toolsPalette.setNothingSelected();
				break;
			case LOCATION:
				toolsPalette.setLocationSelected();
				break;
			case VIEW:
				toolsPalette.setViewSelected();
				break;
			}
		}
		else if(editMode == EditMode.EDIT_VIEW){
			
		}
	}
	
	public void draw(EditorEngine e, GL2 gl){
//		e.setColor(1f, 1f, 1f);
//		e.drawLocation(200, 200, 0);
//		e.drawView(200, 200, 0, 40, 0);
		Iterator<Location> locIt = map.getLocationIterator();
		while(locIt.hasNext()){
			e.drawLocation(locIt.next());
		}
		
	}
	
	public void destroy(){
		
	}
	
	public void createLocation(){
		Location location = new Location();
		location.setPosition(0, 0, 0);
		map.addLocation(location);
	}
	
	public void createView(){
		
	}
	
	public void deleteView(){
		
	}
	
	public void editView(){
		
	}
}
