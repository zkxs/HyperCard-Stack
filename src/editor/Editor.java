package editor;

import javax.media.opengl.GL2;
import java.util.Iterator;

import common.gameelements.Location;
import common.gameelements.View;

import common.gameelements.GameMap;

public class Editor {
	private ToolsPalette toolsPalette;
	private GameMap map;
	private Location selectedLocation;
	private View selectedView;
	
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
		Iterator<Location> locIt = map.getLocationIterator();
		while(locIt.hasNext()){
			e.drawLocation(locIt.next());
		}
	}
	
	public void destroy(){
		
	}
	
	public void createLocation(){
		selectedLocation = new Location();
		selectedLocation.setPosition(0, 0, 0);
		map.addLocation(selectedLocation);
		selection = MapSelection.LOCATION;
	}
	
	public void createView(){
		
	}
	
	public void deleteLocation(){
		String id = selectedLocation.getIdentifier();
		map.removeLocation(id);
		selectedLocation = null;
		selection = MapSelection.NONE;
	}
	
	public void deleteView(){
		
	}
	
	public void editView(){
		
	}
	
	public void setX(int x){
		selectedLocation.getPosition().setX(x);
	}
	
	public void setY(int y){
		selectedLocation.getPosition().setY(y);
	}
	
	public void setZ(int z){
		selectedLocation.getPosition().setZ(z);
	}
}
