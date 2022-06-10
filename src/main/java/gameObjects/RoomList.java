package main.java.gameObjects;

import java.util.LinkedList;

import main.java.world.GameMap;
import main.java.world.GameRoom;

public class RoomList extends LinkedList< GameRoom > {
	
	public RoomList() {
	}
	
	public String describeRooms() {
		String s = "";
		
		if( this.size() == 0 ) {
			s = "nothing." + "\n";
		} else {
			for( GameRoom r: this ) {
				s = s + r.getName() + ": " + r.getDescription() + "\n";
			}
		}
		return s;
	}
	
	public GameRoom thisRoom( String aName ) {
		
		GameRoom aRoom = null;
		String roomName = "";
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( GameRoom r: this ) {
			
			roomName = r.getName().trim().toLowerCase();
			if( roomName.contentEquals( aNameLowerCase ) ) {
				aRoom = r;
			}
		}
		return aRoom;
	}
	
	public int findIndexOf( String aName ) {
		

		int aRoomIndex = -1;
		String roomName = "";
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( int i = 0; i < this.size(); i++  ) {
			
			roomName = this.get( i ).getName().trim().toLowerCase();
			if( roomName.contentEquals( aNameLowerCase ) ) {
				
				aRoomIndex = i;
				break;
			}
		}
		
		return aRoomIndex;
	}
	
	
}