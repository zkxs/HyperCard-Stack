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
	
	public Hashtable<String, Integer> viewIntIds;
	public Hashtable<Integer, String> viewStringIds;
	private int viewCounter = 0;
	
	public enum MapSelection {
		NONE,
		LOCATION,
		VIEW
	}
	
	public enum EditMode {
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
		e.setColor(1, 0, 0);
		e.drawAxes();
		e.setColor(1, 1, 1);
		Iterator<Location> locIt = map.getLocationIterator();
		
		while(locIt.hasNext()){
			Location nextLocation = locIt.next();
			if(selectedLocation != null && nextLocation.getIdentifier() == selectedLocation.getIdentifier())
				e.setColor(0, 1, 0);
			else
				e.setColor(1, 1, 1);
			e.drawLocation(nextLocation);
		}
	}
	
	public void destroy(){
		
	}
	
	public void selectLocation(int id){
		if(id >= 0){
			String sid = locationStringIds.get(id);
			selectedLocation = map.getLocation(sid);
			selection = MapSelection.LOCATION;
			toolsPalette.setLocationSelected();
		}
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
		selectedView = new View();
		viewIntIds.put(selectedView.getIdentifier(), viewCounter + locationCounter);
		viewStringIds.put(locationCounter + viewCounter, selectedView.getIdentifier());
		viewCounter++;
		//TODO selectedLocation.addView(selectedView);
		selection = MapSelection.VIEW;
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
	
	public float getX(){
		return selectedLocation.getPosition().getX();
	}
	
	public float getY(){
		return selectedLocation.getPosition().getY();
	}
	
	public float getZ(){
		return selectedLocation.getPosition().getZ();
	}
	
	public void setX(float x){
		selectedLocation.getPosition().setX(x);
	}
	
	public void setY(float y){
		selectedLocation.getPosition().setY(y);
	}
	
	public void setZ(float z){
		selectedLocation.getPosition().setZ(z);
	}
	
	public MapSelection getMapSelection(){
		return selection;
	}
	
	public EditMode getEditMode(){
		return editMode;
	}
}
