package main.java.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import main.java.gameObjects.Actor;
import main.java.gameObjects.Apple;
import main.java.gameObjects.Bone;
import main.java.gameObjects.Bottle;
import main.java.gameObjects.Cat;
import main.java.gameObjects.Chest;
import main.java.gameObjects.Container;
import main.java.gameObjects.Food;
import main.java.gameObjects.Fruit;
import main.java.gameObjects.Furniture;
import main.java.gameObjects.HomogeneousContentContainer;
import main.java.gameObjects.Key;
import main.java.gameObjects.Lock;
import main.java.gameObjects.Meat;
import main.java.gameObjects.NonPlayerActor;
import main.java.gameObjects.Skull;
import main.java.gameObjects.Table;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.Treasure;
import main.java.gameObjects.Vegetable;
import main.java.gameObjects.Water;
import main.java.gameObjects.Weapon;
import main.java.weapons.Gun;
import main.java.wearableObjects.Ring;
import main.java.wearableObjects.WearableThing;
import main.java.world.GameMap;
import main.java.world.GameRoom;
import main.java.world.GameTile;
import main.java.world.GameWorld;
import main.java.resources.res.ResourceLoader;

public class WorldReader {
	
	GameWorld world;
	
	public WorldReader() {
	}
	
	public GameWorld getWorld( String folderPath, String mapName ) {
		
		File fileObj = ResourceLoader.getFilesFolder( folderPath, mapName );
		
		RuntimeTypeAdapterFactory< Thing > thingAdapterFactory = RuntimeTypeAdapterFactory.of( Thing.class, "type" );
		thingAdapterFactory.registerSubtype( Actor.class, "Actor" );
		thingAdapterFactory.registerSubtype( Apple.class, "Apple" );
		thingAdapterFactory.registerSubtype( Bottle.class, "Bottle" );
		thingAdapterFactory.registerSubtype( Bone.class, "Bone" );
		thingAdapterFactory.registerSubtype( Cat.class, "Cat" );
		thingAdapterFactory.registerSubtype( Container.class, "Container" );
		thingAdapterFactory.registerSubtype( Chest.class, "Chest" );
		thingAdapterFactory.registerSubtype( Food.class, "Food" );
		thingAdapterFactory.registerSubtype( Fruit.class, "Fruit" );
		thingAdapterFactory.registerSubtype( Furniture.class, "Furniture" );
		thingAdapterFactory.registerSubtype( HomogeneousContentContainer.class, "HomogeneousContentContainer" );
		thingAdapterFactory.registerSubtype( Gun.class, "Gun" );
		thingAdapterFactory.registerSubtype( Key.class, "Key" );
		thingAdapterFactory.registerSubtype( Lock.class, "Lock" );
		thingAdapterFactory.registerSubtype( Meat.class, "Meat" );
		thingAdapterFactory.registerSubtype( GameMap.class, "Map" );
		thingAdapterFactory.registerSubtype( NonPlayerActor.class, "NonPlayerActor" );
		thingAdapterFactory.registerSubtype( Ring.class, "Ring" );
		thingAdapterFactory.registerSubtype( GameRoom.class, "Room" );
		thingAdapterFactory.registerSubtype( Skull.class, "Skull" );
		thingAdapterFactory.registerSubtype( Table.class, "Table" );
		thingAdapterFactory.registerSubtype( ThingHolder.class, "ThingHolder" );
		thingAdapterFactory.registerSubtype( GameTile.class, "Tile" );
		thingAdapterFactory.registerSubtype( Treasure.class, "Treasure" );
		thingAdapterFactory.registerSubtype( Vegetable.class, "Vegetable" );
		thingAdapterFactory.registerSubtype( Water.class, "Water" );
		thingAdapterFactory.registerSubtype( Weapon.class, "Weapon" );
		thingAdapterFactory.registerSubtype( WearableThing.class, "WearableThing" );
		thingAdapterFactory.registerSubtype( GameWorld.class, "World" );
		
		BufferedReader br = null;
		
		Gson gson = new GsonBuilder()
		.registerTypeAdapterFactory( thingAdapterFactory )
		.enableComplexMapKeySerialization()
		.create();
		
		try {
			
			br = new BufferedReader( new FileReader( fileObj ) );
			world = gson.fromJson( br, GameWorld.class );
//			br.close();
			
		} catch( FileNotFoundException e ){
			
			System.out.println( "File not found" );
		}
		
		return world;
	}
}
