package main.java.gameObjects;

import java.util.LinkedList;

//test

public class ThingList extends LinkedList< Thing > {
	
//	public String type;
	
//	private LinkedList< Thing > list;;
	
	public ThingList() {
//		this.list = new LinkedList< Thing >();
//		this.type = "ThingList";
	}
	
	public String describeThings() {
		String s = "";
		
		if( this.size() == 0 ) {
			s = "nothing." + "\n";
		} else {
			for( Thing t: this ) {
				s = s + t.getName() + ": " + t.getDescription() + "\n";
			}
		}
		return s;
	}
	
	public Thing findThisObject( String aName ) {
		
		Thing aThing = null;
		String thingName = "";
		String[] thingAltNames;
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( Thing t: this ) {
			
			thingName = t.getName().trim().toLowerCase();
			if( thingName.contentEquals( aNameLowerCase ) ) {
				aThing = t;
			} else {
			
				thingAltNames = t.getAltNames();
				for( int i = 0; i < thingAltNames.length; i++ ) {
					
					if( thingAltNames[i].contentEquals( aNameLowerCase ) ) {
						aThing = t;
					}
				}
			}
		}
		return aThing;
	}
	
	public boolean thingExists( String aName ) {
		
		String thingName = "";
		String[] thingAltNames;
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( Thing t: this ) {
			
			thingName = t.getName().trim().toLowerCase();
			if( thingName.contentEquals( aNameLowerCase ) ) {
				System.out.println( "this is primary name: " + thingName );
				return true;
			}
			
			thingAltNames = t.getAltNames();
			for( int i = 0; i < thingAltNames.length; i++ ) {
				
				if( thingAltNames[i].contentEquals( aNameLowerCase ) ) {
					return true;
				}
			}	
		}
		return false;
	}
	
	public int findIndexOf( String aName ) {
		
		int aThingIndex = -1;
		String thingName = "";
		String[] altThingNames;
		String aNameLowerCase = aName.trim().toLowerCase();
		
		for( int i = 0; i < this.size(); i++  ) {
			
			thingName = this.get( i ).getName().trim().toLowerCase();
			altThingNames = this.get( i ).altNames;
			
			if( thingName.contentEquals( aNameLowerCase ) ) {
				
				aThingIndex = i;
				break;
			}
			
			for( int j = 0; j < altThingNames.length; j++ ) {
				
				if( altThingNames[j].contentEquals( aNameLowerCase ) ) {
					
					aThingIndex = i;
					break;
				}
			}
		}
		
		return aThingIndex;
	}
}
