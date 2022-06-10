package main.java.mapBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import main.java.gameObjects.Thing;
import main.java.utility.ThingTypeRegistry;

public class MapSerializer implements JsonSerializer< Thing > {
	
	private String thingTypeElementName;
	private Gson gson;
	private Map< String, Class< ? extends Thing > > thingTypeRegistry; // map here refers to hash.

	
	public MapSerializer() {

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

	@Override
	public JsonElement serialize(Thing thing, Type typeOfThing, JsonSerializationContext context) {
		System.out.println("type: " + typeOfThing);
		JsonObject jObject = new JsonObject();
		String objectType = thing.type;
		jObject.add( "type", new JsonPrimitive( objectType ) );
		
		return jObject;
	}

}
