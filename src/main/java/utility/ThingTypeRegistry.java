package utility;

import java.util.HashMap;
import java.util.Map;

import actor.Actor;
import actor.NonPlayerActor;
import gameObjects.Bottle;
import gameObjects.Chest;
import gameObjects.Container;
import gameObjects.Furniture;
import gameObjects.Key;
import gameObjects.Lock;
import gameObjects.Table;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import gameObjects.Treasure;
import gameObjects.Water;
import weapons.Weapon;
import wearableObjects.Ring;
import wearableObjects.WearableThing;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;

public class ThingTypeRegistry {
	
	private Map< String, Class< ? extends Thing > > thingTypeRegistry;
	
	public ThingTypeRegistry() {
		this.thingTypeRegistry = new HashMap<>();
		registerType( "Actor", Actor.class );
		registerType( "Bottle", Bottle.class );
		registerType( "Container", Container.class );
		registerType( "Chest", Chest.class );
		registerType( "Furniture", Furniture.class );
		registerType( "Key", Key.class );
		registerType( "Lock", Lock.class );
		registerType( "Map", GameMap.class );
		registerType( "NonPlayerActor", NonPlayerActor.class );
		registerType( "Ring", Ring.class );
		registerType( "Room", GameRoom.class );
		registerType( "Table", Table.class );
		registerType( "ThingHolder", ThingHolder.class );
//		registerType( "ThingList", ThingList.class );
		registerType( "Tile", GameTile.class );
		registerType( "Treasure", Treasure.class );
		registerType( "Water", Water.class );
		registerType( "Weapon", Weapon.class );
		registerType( "WearableThing", WearableThing.class );
		registerType( "World", GameWorld.class );
	}
	
	public void registerType( String thingTypeName, Class< ? extends Thing > thingType ) {
		
		thingTypeRegistry.put( thingTypeName, thingType );
	}
	
	public Map< String, Class< ? extends Thing > > getRegistry() {
		return thingTypeRegistry;
	}
	
	public Class<? extends Thing> getClass( String className ) {
		return this.thingTypeRegistry.get( className );
	}
}
