package editor;

import javax.media.opengl.GL2;
import javax.swing.JOptionPane;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Iterator;

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
	private int locationCounter = 0; // do not decrement, used to create unique ids
	
	public Hashtable<String, Integer> viewIntIds;
	public Hashtable<Integer, String> viewStringIds;
	private int viewCounter = 0; // do not decrement, used to create unique ids
	
	private MapSelection selection;
	private EditMode editMode;
	private boolean aimingView;
	private boolean aimingViewFrom;
	
	public enum MapSelection {
		NONE,
		LOCATION,
		VIEW
	}
	
	public enum EditMode {
		EDIT_MAP, //editing locations and views
		EDIT_VIEW //editing navAreas and manipulators
	}
	
	
	public Editor(){
	}
	
	public void init(ToolsPalette tools){
		map = new GameMap();
		locationIntIds = new Hashtable<String, Integer>();
		locationStringIds = new Hashtable<Integer, String>();
		viewIntIds = new Hashtable<String, Integer>();
		viewStringIds = new Hashtable<Integer, String>();
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
		
		Iterator<View> viewIt = map.getViewIterator();
		
		while(viewIt.hasNext()){
			View nextView = viewIt.next();
			if(selectedView != null && nextView.getIdentifier() == selectedView.getIdentifier())
				e.setColor(0, 1, 0);
			else
				e.setColor(1, 1, 1);
			e.drawView(nextView.getLocation(), nextView);
		}
		
	}
	
	public void destroy(){
		
	}
	
	public void pickResult(int id){
		
		if(aimingView || aimingViewFrom){
			
			if(locationStringIds.get(id) != null){ // id does belong to a location
				String sid = locationStringIds.get(id);
				Location destination = map.getLocation(sid);
				
				double x = selectedLocation.getPosition().getX();
				double y = selectedLocation.getPosition().getY();
				
				double x2 = destination.getPosition().getX();
				double y2 = destination.getPosition().getY();
				
				double dx = x2 - x;
				double dy = y2 - y;
				
				//calculate the angle between the points in xy radians
				//and convert xy coords to compass coords, and radians to degrees
				double angle = -Math.atan2(dy, dx) * 180 / Math.PI + 90;
				
				if(aimingViewFrom)
					angle += 180;
				
				selectedView.getOrientation().setTheta((float)angle);
			}
			
			aimingView = false;
			aimingViewFrom = false;
		}
		else{
			selectLocationOrView(id);
		}
	}
	
	public void selectLocationOrView(int id){
		if(id >= 0){
			String sid;
			if(locationStringIds.get(id) != null){ // id does belong to a location
				sid = locationStringIds.get(id);
				selectedLocation = map.getLocation(sid);
				selectedView = null;
				selection = MapSelection.LOCATION;
				toolsPalette.setLocationSelected();				
			}
			else if(viewStringIds.get(id) != null){ // id does belong to a view
				sid = viewStringIds.get(id);
				selectedView = map.getView(sid);
				selectedLocation = selectedView.getLocation();
				selection = MapSelection.VIEW;
				toolsPalette.setViewSelected();
			}
		}
	}
	
	public void selectNothing(){
		selection = MapSelection.NONE;
		selectedLocation = null;
		selectedView = null;
	}
	
	public void createLocation(){
		selectedLocation = new Location(map);
		locationIntIds.put(selectedLocation.getIdentifier(), locationCounter + viewCounter);
		locationStringIds.put(locationCounter + viewCounter, selectedLocation.getIdentifier());
		locationCounter++;
		selectedLocation.setPosition(0, 0, 0);
		map.addLocation(selectedLocation);
		selection = MapSelection.LOCATION;
	}
	
	public void createView(){
		selectedView = new View(selectedLocation);
		viewIntIds.put(selectedView.getIdentifier(), locationCounter + viewCounter);
		viewStringIds.put(locationCounter + viewCounter, selectedView.getIdentifier());
		viewCounter++;
		selectedLocation.addView(selectedView);
		selection = MapSelection.VIEW;
	}
	
	public void deleteLocation(){
		String id = selectedLocation.getIdentifier();
		map.removeLocation(id);
		locationStringIds.remove(locationIntIds.get(id));
		locationIntIds.remove(id);
		selectedLocation = null;
		selection = MapSelection.NONE;
	}
	
	public void deleteView(){
		String id = selectedView.getIdentifier();
		selectedLocation.removeView(id);
		selectedView = null;
		selection = MapSelection.LOCATION;
	}
	
	public void editView(){
		
	}
	
	public void aimViewAtLocation(){
		aimingView = true;
	}
	
	public void aimViewFromLocation(){
		aimingViewFrom = true;
	}
	
	public void setViewImage(BufferedImage image){
		
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
