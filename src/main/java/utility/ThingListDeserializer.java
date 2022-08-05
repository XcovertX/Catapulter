package utility;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import gameObjects.Thing;
import gameObjects.ThingList;
import utility.ThingTypeRegistry;

public class ThingListDeserializer implements JsonDeserializer< ThingList > {
	
	private String listTypeElementName;
	private Gson gson;
	private Map< String, Class< ? extends ThingList > > listTypeRegistry; // map here refers to hash.

	public ThingListDeserializer( String aListTypeElementName ) {
		
		this.listTypeElementName = aListTypeElementName;
		this.gson = new Gson();
		this.setTypeRegistry( new ListTypeRegistry().getRegistry() );
		
	}
	
	public void setTypeRegistry( Map< String, Class< ? extends ThingList > > aTypeRegistry ) {
		this.listTypeRegistry = aTypeRegistry;
	}
	
	public ThingList deserialize(JsonElement json, Type typeOfT , JsonDeserializationContext context) throws JsonParseException {
		

		JsonObject listObject = json.getAsJsonObject();
		JsonElement listTypeElement = listObject.get( listTypeElementName );
		Class< ? extends ThingList > listType = listTypeRegistry.get( listTypeElement.getAsString() );
	

		return gson.fromJson( listObject, listType );
	}

}
