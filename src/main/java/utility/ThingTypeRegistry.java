package main.java.utility;

import java.util.HashMap;
import java.util.Map;

import main.java.actor.Actor;
import main.java.actor.NonPlayerActor;
import main.java.gameObjects.Bottle;
import main.java.gameObjects.Chest;
import main.java.gameObjects.Container;
import main.java.gameObjects.Furniture;
import main.java.gameObjects.Key;
import main.java.gameObjects.Lock;
import main.java.gameObjects.Table;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.ThingList;
import main.java.gameObjects.Treasure;
import main.java.gameObjects.Water;
import main.java.weapons.Weapon;
import main.java.wearableObjects.Ring;
import main.java.wearableObjects.WearableThing;
import main.java.world.GameMap;
import main.java.world.GameRoom;
import main.java.world.GameTile;
import main.java.world.GameWorld;

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
