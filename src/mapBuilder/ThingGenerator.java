package mapBuilder;

import java.util.HashMap;
import java.util.Map;

import gameObjects.Actor;
import gameObjects.Animal;
import gameObjects.Apple;
import gameObjects.Bottle;
import gameObjects.Cat;
import gameObjects.Chest;
import gameObjects.Container;
import gameObjects.Furniture;
import gameObjects.Key;
import gameObjects.Lock;
import gameObjects.NonPlayerActor;
import gameObjects.Table;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import gameObjects.Treasure;
import gameObjects.Water;
import gameObjects.Weapon;
import weapons.Gun;
import weapons.Revolver;
import wearableObjects.Ring;
import wearableObjects.WearableThing;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;


public class ThingGenerator {
	
	private Map< String, Thing > things = new HashMap<>();
	
	public ThingGenerator() {
		things.put( "Actor", new Actor() );
		things.put( "Apple", new Apple() );
		things.put( "Bottle", new Bottle() );
		things.put( "Cat", new Cat() );
		things.put( "Chest", new Chest() );
		things.put( "Closet", new Container() );
		things.put( "Container", new Container() );
		things.put( "Furniture", new Furniture() );
		things.put( "Key", new Key() );
		things.put( "Lock", new Lock() );
		things.put( "Map", new GameMap() );
		things.put( "Revolver", new Revolver() );
		things.put( "Ring", new Ring() );
		things.put( "Room", new GameRoom() );
		things.put( "Table", new Table() );
		things.put( "Ring", new Ring() );
		things.put( "ThingHolder", new ThingHolder() );
		things.put( "Tile", new GameTile() );
		things.put( "Treasure", new Treasure() );
		things.put( "Water", new Water() );
		things.put( "WearableThing", new WearableThing() );
		things.put( "World", new GameWorld() );
	}

	public Thing getThing( String type ) {
		try {
			return things.get( type );
		} catch( IllegalArgumentException e ) {
			return null;
		} 
	}
	
	public boolean check( String s ) {
		return things.containsKey( s );
	}
}
