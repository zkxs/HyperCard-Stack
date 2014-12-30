package editor;

import javax.media.opengl.GL2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import common.gameelements.Location;
import common.gameelements.View;

import common.gameelements.GameMap;

public class Editor {
	private ToolsPalette toolsPalette;
	private GameMap map;
	private Location selectedLocation;
	private View selectedView;
	
	// map location string ids to ints because opengl picking needs an int id
	public Hashtable<String, Integer> locationIntIds;
	public Hashtable<Integer, String> locationStringIds;
	private int locationCounter = 0;
	
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
		locationIntIds = new Hashtable<String, Integer>();
		locationStringIds = new Hashtable<Integer, String>();
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
	
	public void selectLocation(int id){
		String sid = locationStringIds.get(id);
		selectedLocation = map.getLocation(sid);
		toolsPalette.setLocationSelected();
	}
	
	public void selectNothing(){
		selection = MapSelection.NONE;
		selectedLocation = null;
		selectedView = null;
	}
	
	public void createLocation(){
		selectedLocation = new Location();
		locationIntIds.put(selectedLocation.getIdentifier(), locationCounter);
		locationStringIds.put(locationCounter, selectedLocation.getIdentifier());
		locationCounter++;
		selectedLocation.setPosition(0, 0, 0);
		map.addLocation(selectedLocation);
		selection = MapSelection.LOCATION;
	}
	
	public void createView(){
		
	}
	
	public void deleteLocation(){
		String id = selectedLocation.getIdentifier();
		map.removeLocation(id);
		locationStringIds.remove(locationIntIds.get(id));
		locationIntIds.remove(id);
		locationCounter--;
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
