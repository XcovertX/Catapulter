package main.java.mapBuilder;

import java.util.HashMap;
import java.util.Map;

import main.java.gameObjects.Actor;
import main.java.gameObjects.Animal;
import main.java.gameObjects.Apple;
import main.java.gameObjects.Bottle;
import main.java.gameObjects.Cat;
import main.java.gameObjects.Chest;
import main.java.gameObjects.Container;
import main.java.gameObjects.Furniture;
import main.java.gameObjects.Key;
import main.java.gameObjects.Lock;
import main.java.gameObjects.NonPlayerActor;
import main.java.gameObjects.Table;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.ThingList;
import main.java.gameObjects.Treasure;
import main.java.gameObjects.Water;
import main.java.weapons.Gun;
import main.java.weapons.Revolver;
import main.java.weapons.Weapon;
import main.java.wearableObjects.Ring;
import main.java.wearableObjects.WearableThing;
import main.java.world.GameMap;
import main.java.world.GameRoom;
import main.java.world.GameTile;
import main.java.world.GameWorld;


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
