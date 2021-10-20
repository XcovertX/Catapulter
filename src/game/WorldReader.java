package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import gameObjects.Actor;
import gameObjects.Apple;
import gameObjects.Bone;
import gameObjects.Bottle;
import gameObjects.Cat;
import gameObjects.Chest;
import gameObjects.Container;
import gameObjects.Food;
import gameObjects.Fruit;
import gameObjects.Furniture;
import gameObjects.HomogeneousContentContainer;
import gameObjects.Key;
import gameObjects.Lock;
import gameObjects.Meat;
import gameObjects.NonPlayerActor;
import gameObjects.Skull;
import gameObjects.Table;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.Treasure;
import gameObjects.Vegetable;
import gameObjects.Water;
import gameObjects.Weapon;
import res.ResourceLoader;
import weapons.Gun;
import wearableObjects.Ring;
import wearableObjects.WearableThing;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;

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
