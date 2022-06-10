package main.java.utility;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingList;


public class ListTypeRegistry {
	
	private Map< String, Class< ? extends ThingList > > listTypeRegistry;
	
	public ListTypeRegistry() {
		this.listTypeRegistry = new HashMap<>();
		registerType( "ThingList", ThingList.class );

	}
	
	public void registerType( String thingTypeName, Class< ? extends ThingList > thingType ) {
		
		listTypeRegistry.put( thingTypeName, thingType );
	}
	
	public Map< String, Class< ? extends ThingList > > getRegistry() {
		return listTypeRegistry;
	}
}
