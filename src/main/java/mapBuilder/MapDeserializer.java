package main.java.mapBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import main.java.gameObjects.Thing;
import main.java.utility.ThingTypeRegistry;

public class MapDeserializer implements JsonDeserializer< Thing > {
	
	private String thingTypeElementName;
	private Gson gson;
	private Map< String, Class< ? extends Thing > > thingTypeRegistry; // map here refers to hash.

	public MapDeserializer( String aThingTypeElementName ) {
		
		this.thingTypeElementName = aThingTypeElementName;
		this.gson = new Gson();
		this.setTypeRegistry( new ThingTypeRegistry().getRegistry() );
//		this.thingTypeRegistry = new HashMap<>();
		
	}
	
	public void registerType( String thingTypeName, Class< ? extends Thing > thingType ) {
		
		thingTypeRegistry.put( thingTypeName, thingType );
	}
	
	public void setTypeRegistry( Map< String, Class< ? extends Thing > > aTypeRegistry ) {
		this.thingTypeRegistry = aTypeRegistry;
	}
	
	public Thing deserialize(JsonElement json, Type typeOfT , JsonDeserializationContext context) throws JsonParseException {
		

		JsonObject thingObject = json.getAsJsonObject();
		JsonElement thingTypeElement = thingObject.get( thingTypeElementName );
		Class< ? extends Thing > thingType = thingTypeRegistry.get( thingTypeElement.getAsString() );
	

		return gson.fromJson( thingObject, thingType );
	}

}
