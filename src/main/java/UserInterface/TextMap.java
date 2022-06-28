package main.java.UserInterface;

import main.java.game.Game;
import main.java.world.GameRoom;
import main.java.world.GameTile;

public class TextMap {

	private GameRoom gr;
	private String textMap;
	
	public TextMap() {
		
	}
	
	public void setRoom( GameRoom aRoom ) {
		
		String mapRep = "";
		int roomNumberTotal = aRoom.getRoomLength() * aRoom.getRoomWidth();
		
	    for( int i = aRoom.getRoomWidth(); i > 0; i-- ) {
	    	
	    	for( int j = aRoom.getRoomLength(); j > 0; j-- ) {
	    		
	    		int index = roomNumberTotal - j;
	    		mapRep += ( ( GameTile ) aRoom.getTiles().get( index ) ).getTileChar();
	    	}
	    	mapRep += "\r\n";
	    	roomNumberTotal = roomNumberTotal - aRoom.getRoomLength();
    	}
		
	    textMap = mapRep;
		Game.currentGame.getUI().getGuiController().setMapChars( mapRep, aRoom.getRoomWidth(), aRoom.getRoomLength() );
	}
	
	public void mapPan() {
		
		int[] mapEdges = calculateMapEdgeDistances(); // 0 = n, 1 = s, 2 = e, 3 = w
		
		if( Game.currentRoom.getRoomLength() > 10 ) {
			
			System.out.println( textMap );
		}
			
		
		if( mapEdges[ 0 ] > 10 ) {
			
		}
		
		
	}
	
	// direction calculations
    public int northEquation( GameTile gt ) {
    
    	return gt.getTileNumber() + Game.currentRoom.getRoomLength();
    }
    
    public int southEquation( GameTile gt ) {
    	
    	return gt.getTileNumber() - Game.currentRoom.getRoomLength();
    }
    
    public int eastEquation( GameTile gt ) {
    	
    	return gt.getTileNumber() + 1;
    }
    
    public int westEquation( GameTile gt ) {
    	
    	return gt.getTileNumber() - 1;
    }
	
    // map edge calculator
    
    public int[] calculateMapEdgeDistances() {
    	
    	GameTile startTile = Game.currentTile;
    	
    	int n = 0;
    	int s = 0;
    	int e = 0;
    	int w = 0;
    	
    	int[] wallDistances = { n, s, e, w }; 			// 0 = n, 1 = s, 2 = e, 3 = w
    	
    	for( int i = 0; i < Game.currentRoom.getRoomWidth(); i++ ) {
    		
    		if( northWall( startTile ) ) {
    			
    			n = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( northEquation( startTile ) );
    		}
    	}
    	
    	for( int i = 0; i < Game.currentRoom.getRoomWidth(); i++ ) {
    		
    		if( southWall( startTile ) ) {
    			
    			s = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( southEquation( startTile ) );
    		}
    	}
    	
    	for( int i = 0; i < Game.currentRoom.getRoomLength(); i++ ) {
    		
    		if( eastWall( startTile ) ) {
    			
    			e = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( eastEquation( startTile ) );
    		}
    		
    	}
    	
    	for( int i = 0; i < Game.currentRoom.getRoomLength(); i++ ) {
    		
    		if( westWall( startTile ) ) {
    			
    			w = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( westEquation( startTile ) );
    		}
    	}
    	
    	return wallDistances;
    }
    
	// map wall detection
    public boolean northWall( GameTile gt ) {
    	
    	if( northEquation( gt ) > Game.currentRoom.getRoomSize() - 1 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean southWall( GameTile gt ) {
    	
    	if( southEquation( gt ) < 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean eastWall( GameTile gt ) {

    	if ( ( eastEquation( gt ) ) % ( Game.currentRoom.getRoomLength() )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean westWall( GameTile gt ) {

    	if ( ( gt.getTileNumber() ) % ( Game.currentRoom.getRoomLength() )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }

}
