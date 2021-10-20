package gameObjects;

import java.util.LinkedList;

import world.GameMap;
import world.GameWorld;

public class MapList extends LinkedList< GameMap > {
	
	public MapList() {
	}
	
	public String describeMaps() {
		String s = "";
		
		if( this.size() == 0 ) {
			s = "nothing." + "\n";
		} else {
			for( GameMap m: this ) {
				s = s + m.getName() + ": " + m.getDescription() + "\n";
			}
		}
		return s;
	}
	
	public GameMap thisMap( String aName ) {
		
		GameMap aMap = null;
		String mapName = "";
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( GameMap m: this ) {
			
			mapName = m.getName().trim().toLowerCase();
			if( mapName.contentEquals( aNameLowerCase ) ) {
				aMap = m;
			}
		}
		return aMap;
	}
	
	public int findIndexOf( String aName ) {
		

		int aMapIndex = -1;
		String MapName = "";
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( int i = 0; i < this.size(); i++  ) {
			
			MapName = this.get( i ).getName().trim().toLowerCase();
			if( MapName.contentEquals( aNameLowerCase ) ) {
				
				aMapIndex = i;
				break;
			}
		}
		
		return aMapIndex;
	}
	
	
}