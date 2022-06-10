package main.java.gameObjects;

import java.util.LinkedList;

import main.java.world.GameMap;
import main.java.world.GameRoom;
import main.java.world.GameTile;

public class TileList extends LinkedList< GameTile > {
	
	public TileList() {
	}
	
	public String describeRooms() {
		String s = "";
		
		if( this.size() == 0 ) {
			s = "nothing." + "\n";
		} else {
			for( GameTile t: this ) {
				s = s + t.getName() + ": " + t.getDescription() + "\n";
			}
		}
		return s;
	}
	
	public GameTile thisTile( String aName ) {
		
		GameTile aTile = null;
		String tileName = "";
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( GameTile t: this ) {
			
			tileName = t.getName().trim().toLowerCase();
			if( tileName.contentEquals( aNameLowerCase ) ) {
				aTile = t;
			}
		}
		return aTile;
	}
	
	public int findIndexOf( String aName ) {
		

		int aTileIndex = -1;
		String tileName = "";
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( int i = 0; i < this.size(); i++  ) {
			
			tileName = this.get( i ).getName().trim().toLowerCase();
			if( tileName.contentEquals( aNameLowerCase ) ) {
				
				aTileIndex = i;
				break;
			}
		}
		
		return aTileIndex;
	}
	
	
}