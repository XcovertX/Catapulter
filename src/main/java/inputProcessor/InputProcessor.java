package inputProcessor;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import userInterface.UserInterface;
import actor.Actor;
import game.Game;
import game.WorldReader;
import globals.Direction;
import verbs.Action;
import world.GameMap;
import world.GameRoom;
import world.GameTile;

public class InputProcessor {
	
	private WorldReader worldReader;
    private String input;
    private String output;
    private UserInterface userInterface;
    private boolean roomChange = false;
    private Actor player;  // the player - provides 'first person perspective'

	private KnownVerbs knownVerbs;
	private KnownNouns knownNouns;
	private KnownPrepositions knownPrepositions;
	private KnownArticles knownArticles;
	private KnownAdjectives knownAdjectives;
	private KnownNames knownNames;

	public InputProcessor() {
		player = Game.currentGame.getPlayer();
		userInterface = Game.currentGame.getUI();
		setKnownVerbs( new KnownVerbs() );
		setKnownNouns( new KnownNouns() );
		setKnownPrepositions( new KnownPrepositions() );
		setKnownArticles( new KnownArticles() );
		setKnownAdjectives( new KnownAdjectives() );
	}

    // move a Person to a Room
    void moveActorTo( Actor p, GameTile aGameTile ) {
    	
        p.setTile( aGameTile );

    }

    // move an Actor in direction 'dir'
    int moveTo( Actor anActor, Direction dir ) {
        // return: Constant representing the room number moved to
        // or NOEXIT
        //
        // try to move any Person (typically but not necessarily player)
        // in direction indicated by dir
        GameTile gt = anActor.getTile();
        int exit;

        switch (dir) {
            case NORTH:
                exit = gt.getN();
                break;
            case SOUTH:
                exit = gt.getS();
                break;
            case EAST:
                exit = gt.getE();
                break;
            case WEST:
                exit = gt.getW();
                break;
            default:
                exit = Direction.NOEXIT; // noexit - stay in same room
                break;
        }
        if ( exit != Direction.NOEXIT ) {
        	
    		gt.setCurrentTileChar(); //TODO Make this a variable
        	if( ( ( ( GameTile) Game.currentRoom.getTiles().get( exit ) ) ).isDoor() ) { //NOTE: you can stop using this if you want the NPA to stay in room.
        		
        		//TODO This doesn't make sense --
        		// What needs to be done: each json should be a map
        		
        		GameTile door = (GameTile) ( Game.currentRoom.getTiles().get( exit ) );
        		Game.currentTile.setCurrentTileCharToDefaultTileChar();
//        		Game.currentWorld =  worldReader.getWorld( door.getExternalMapLocation(), door.getExternalMapName() );
        		Game.currentMap  = ( GameMap )  ( Game.currentWorld.getMaps().get( Game.currentWorld.getMaps().findIndexOf( door.getExternalMapName() ) ) );
        		Game.currentRoom = ( GameRoom ) ( Game.currentMap.getRooms().get( Game.currentMap.getRooms().findIndexOf( door.getExternalRoomName() ) ) );
        		Game.currentTile = ( GameTile ) ( Game.currentRoom.getTiles().get( door.getExternalTile() ) );
        		
        		Game.currentMap.setWorld( Game.currentWorld );
        		Game.currentRoom.setMap( Game.currentMap );
        		Game.currentTile.setRoom( Game.currentRoom );
        		
        		moveActorTo( anActor, Game.currentTile );
        		
        	} else {
        		
        		moveActorTo( anActor, ( GameTile ) Game.currentRoom.getTiles().get( exit ) );
        	}
        }
        return exit;
    }

    public int movePlayerTo(Direction dir) {
        // return: Constant representing the room number moved to
        // or NOEXIT (see moveTo())
        //        
        return moveTo(player, dir);
    }

    private void goN() {
        updateOutput(movePlayerTo(Direction.NORTH));
    }

    private void goS() {
        updateOutput(movePlayerTo(Direction.SOUTH));
    }

    private void goW() {
        updateOutput(movePlayerTo(Direction.WEST));
    }

    private void goE() {
        updateOutput(movePlayerTo(Direction.EAST));
    }

    public void updateOutput( int roomNumber ) {
        // if roomNumber = NOEXIT, display a special message, otherwise
        // display text (e.g. name and description of room)    
        String s;
        String currentExits = null;

        GameTile gt = Game.currentGame.getPlayer().getTile(); // current room player is in
        
        if ( roomNumber == Direction.NOEXIT ) {
        	
            s = "No Exit!";
            currentExits = gt.getTileExits().toString();
    
        	userInterface.println( s );

        } else {
        	
    		gt.setCurrentTileChar(); // updates tile char
    		userInterface.printColor( "Time: " + Game.calendar.getTime(), Color.ORANGE );
    		userInterface.printColor( "Wind Direction: " + Game.calendar.getWeather().getCurrentWindDirection() + " ::: ", Color.ORANGE );
    		userInterface.printlnColor( "Wind Speed: " + Game.calendar.getWeather().getCurrentWindIntensity(), Color.ORANGE );
    		userInterface.print( "You are in " );
    		userInterface.printColor( gt.getName(), Color.MAGENTA );
            userInterface.println( "." );
            userInterface.println( gt.getDescription() );
            
            if( !gt.getThings().isEmpty() ) {
            	
            	for( int i = 0; i < gt.getThings().size(); i++ ) {
            		
            		s = "There is a ";
            		userInterface.printColor( s, Color.white );
            		s = gt.getThings().get( i ).toString();
            		userInterface.printColor( s, Color.yellow );
            		s = " on the " + gt.getThings().get( i ).getLocationInRoom() + ".";
            		userInterface.printlnColor( s, Color.white );
            	}
            }
            
            if( !gt.getNPCs().isEmpty() ) {
            	
            	for( int i = 0; i < gt.getNPCs().size(); i++ ) {
            		
            		Actor actor = ( Actor ) gt.getNPCs().get( i );

					//TODO: this is a weird way of doing this
            		if( actor.isAlive() ) {
	            		userInterface.printColor( actor.toString(), Color.green );
	            		userInterface.printlnColor( " is here.", Color.white );
            		} else {
            			userInterface.printColor( "The corpse of ", Color.white );
	            		userInterface.printColor( actor.toString(), Color.RED );
	            		userInterface.printlnColor( " is here.", Color.white );
            		}
            	}
            }
            
            currentExits = "The current exits are " + gt.getTileExits().toString();
            userInterface.getDisplay().setRoom( Game.currentRoom ); 
        }
        
        userInterface.printlnColor( currentExits, Color.CYAN );
    }

    //processes a stand alone verb
    public String ProcessVerb( List< String > wordlist ) {
        String verb;
        String msg = "";
        verb = wordlist.get(0);
        if( knownVerbs.check( verb ) == false ) {
        	msg = "I am unfamiliar with the term " + verb + ". What should I do?";
        } else if( knownVerbs.getAction( verb ).isDirection() == true ) {
        	knownVerbs.getAction( verb ).run( verb );
        } else {
        	knownVerbs.getAction( verb ).run();
        }
        return msg;
    }

    //processes a verb/noun combo
    public String ProcessVerbNoun( List< String > wordlist ) {
        String verb;
        String string;
        String msg = "";
        verb = wordlist.get( 0 );
        string = wordlist.get( 1 );
        
        //Checks if viable verb;
        if( knownVerbs.check( verb ) == false ) {
        	msg = "I am unfamiliar with the term " + verb + ". What should I do?";
        	return msg;
        //checks if talk verb
        } else if ( knownVerbs.getAction( verb ).requiresNoun() ) {
        	knownVerbs.getAction( verb ).run( string );
        	return msg;
        } else if( knownVerbs.getAction( verb ).canHaveNoun() ) {
        	knownVerbs.getAction( verb ).run( string );
        	return msg;
        } else {
        	knownVerbs.getAction( verb );
        	msg = "I'm not sure what you're asking me to do.";
        }
        return msg;
    }

    //processes a verb with a string of any length
    public String ProcessVerbString( List< String > wordlist ) {
        String verb;
        String noun = "";
        String msg = "";
        verb = wordlist.get( 0 );
        
        for( int i = 1; i < wordlist.size(); i++ ) {
        	if( i == wordlist.size() - 1 ) {
        		noun += wordlist.get( i );
        	} else {
        		noun += wordlist.get( i ) + " ";
        	}
        }
        
        //Checks if viable verb
        if( knownVerbs.check( verb ) == false ) {
        	msg = "I am unfamiliar with the term " + verb + ". What should I do?";
        	return msg;
        //checks if requires noun
        } else if ( knownVerbs.getAction( verb ).requiresNoun() ) {
        	knownVerbs.getAction( verb ).run( noun );
        	return msg;
        } else {
        	knownVerbs.getAction( verb );
        	msg = "I'm not sure what you're trying to do.";
        }
        return msg;
    }
    
    //processes a verb/noun combo
    public String processVerbPrepNoun( List< String > wordlist ) {
        String verb;
        String preposition;
        String noun;
        String msg = "";
        verb = wordlist.get( 0 );
        preposition = wordlist.get( 1 );
        noun = wordlist.get( 2 );
        
        
        //Checks if viable verb;
        if( knownVerbs.check( verb ) == false ) {
        	msg = "I am unfamiliar with the term " + verb + ". What should I do?";
        	return msg;
        //checks if talk verb
        } else if( knownNouns.check( noun ) == false ) {
        	msg = "I'm unfamiliar with what a " + noun + " is. Is there anything else it's called?";
        	return msg;
        } else if( knownPrepositions.check( preposition ) == false ) {
        	msg = "I am unfamiliar with how to " + verb + " '" + preposition 
        			+ "' a " + noun + ". Maybe your command needs to be modified.";
        	return msg;
        } else {
        	knownVerbs.getAction( verb ).run( noun, preposition );
        	return msg;
        } 
    }
    
    //processes a verb/noun combo
    public String ProcessVerbNounPrepNoun( List< String > wordlist ) {
        String verb;
        String preposition;
        String nounOne;
        String nounTwo;
        String msg = "";
        verb = wordlist.get( 0 );
        nounOne = wordlist.get( 1 );
        preposition = wordlist.get( 2 );
        nounTwo = wordlist.get( 3 );
        
        //Checks if viable verb;
        if( knownVerbs.check( verb ) == false ) {
        	msg = "I am unfamiliar with the term " + verb + ". What should I do?";
        	return msg;
        //checks if talk verb
        } else if( knownNouns.check( nounOne ) == false ) {
        	msg = "I'm unfamiliar with what a " + nounOne + " is. Is there anything else it's called?";
        	return msg;
        } else if( knownNouns.check( nounTwo ) == false ) {
        	msg = "I'm unfamiliar with what a " + nounTwo + " is. Is there anything else it's called?";
        	return msg;
        } else if( knownPrepositions.check( preposition ) == false ) {
        	msg = "I am unfamiliar with how to " + verb + nounOne + " '" + preposition 
        			+ "' a " + nounTwo + ". Maybe your command needs to be modified.";
        	return msg;
        } else {
        	knownVerbs.getAction( verb ).run( nounOne, preposition, nounTwo );
        	return msg;
        } 
    }  

    public String ParseCommand( List< String > wordlist ) {

        String msg;
        if ( wordlist.size() == 1 ) {
            msg = ProcessVerb( wordlist );
        } else if( wordlist.size() == 2 ) {
            msg = ProcessVerbNoun( wordlist );
        } else if( wordlist.size() > 2) {
        	msg = ProcessVerbString( wordlist );
        } else {
            msg = "I don't understand what you're trying to do";
        }
        return msg;
    }
    
    public String parseCommand( List< String > wordList ) {

        String msg;
        List< String > sentenceComponents = SentenceComponents( wordList );	
        List< List < String > > bothLists;
        Map< String, String > nounAdjectives;
        int wordCount;
        Action verb;
        
    	if( sentenceComponents.get( 0 ).equals( "verb" ) 
    			|| sentenceComponents.get( 0 ).equals( "verbNoun" ) ) {		// ensures first word is verb
    		verb = knownVerbs.getAction( wordList.get( 0 ) );				// assigns verb to variable
    		if( verb.isTalkCommand() == false ) {							// filters out talk commands
    			bothLists = removeArticles( sentenceComponents, wordList );	// removes articles from sentence
    			bothLists = joinNeighboringPrepositions( bothLists );		// joins neighboring prepositions
    			bothLists = joinNeighboringNouns( bothLists );				// joins neighboring nouns
    			nounAdjectives = joinAdjectiveNoun( bothLists );			// joins adjective with noun to better identify noun
    			wordCount = wordList.size();

    			if( wordCount == 1 ) {		
    				msg = ProcessVerb( wordList );
	    		} else if( wordCount == 2 ) {								// if two words present => process verb + noun
	        		msg = ProcessVerbNoun( wordList );						
	    		} else if( wordCount == 3 ) {								// if three words present => process verb + preposition + noun
	    			msg = processVerbPrepNoun( wordList );					
	        	} else if( wordCount == 4 ) {								// if four words present => process verb + noun + preposition + noun
	    			msg = ProcessVerbNounPrepNoun( wordList );				
	        	} else {
	        		msg = "I don't understand what you're trying to do";
	        	}
    		} else {														// if verb is talk command
    			if( sentenceComponents.size() > 1 ) {						// ensures a string follows the talk command
    				msg = ProcessVerbString( wordList );					// process talk command with string
    			} else {													// if no string follows
    				msg = ProcessVerb( wordList );							// process command with no string
    			}
    		}
        	
        } else {
            msg = "I don't understand what you're trying to do";
        }
        return msg;
    }
    
    /**
     * This method removes all of the articles from the sentence
     * @param sentenceComponents -- list of components within the sentence
     * @param wordList -- list of words in the sentence
     * @return -- returns both lists
     */
    public List< List < String > > removeArticles( List< String > sentenceComponents, List< String > wordList ) {
    	for( int i = 0; i < sentenceComponents.size(); i++ ) {
    		if( sentenceComponents.get( i ).contentEquals( "article" ) ) {
    			sentenceComponents.remove( i );
    			wordList.remove( i );
    		}
    	}
    	List< List < String > > bothLists = new ArrayList< List < String > >();
    	bothLists.add( sentenceComponents );
    	bothLists.add( wordList );
//    	System.out.println( wordList );
//    	System.out.println( sentenceComponents );
    	return bothLists;
    }
    
    /**
     * This method joins any neighboring nouns 
     * and then replaces the two prepositions with one new nouns
     */
    public List< List< String > > joinNeighboringNouns( List< List< String > > bothLists ) {
    	List< String > sentenceComponents = bothLists.get( 0 );
    	List< String > wordList = bothLists.get( 1 );
		String nounOne;																	// first noun variable
		String nounTwo;																	// second noun variable
    	for( int i = 0; i < sentenceComponents.size(); i++ ) {							// for loop to find noun in the sentenceComponents
    		if( sentenceComponents.get( i ).equals( "noun" ) ) { 						// finds noun in wordList
    			nounOne = wordList.get( i );											// assigns found noun to first variable
    			if( i + 1 <= sentenceComponents.size() - 1) { 							// checks for index out of bounds
	    			if( sentenceComponents.get( i + 1 ).equals( "noun" ) ) { 			// determines if next is also noun
	    				nounTwo = wordList.get( i + 1 );								// assigns found noun to second variable
	    				String joinedNouns = nounOne + " " + nounTwo;					// joins the two noun to one variable
	    				wordList.remove( i );											// removes noun to be replaced
	    				wordList.remove( i );											// removes noun to be replaced
	    				wordList.add( i, joinedNouns );									// adds joined noun
	    				sentenceComponents.remove( i );									// removes noun marker
	    				i--;															// correction for the for loop
	    			}
    			}
    		}
    	}
//    	System.out.println( wordList );
//    	System.out.println( sentenceComponents );
    	return bothLists;
    	
    }
    
    /**
     * This method joins any neighboring prepositions 
     * and then replaces the two prepositions with one new prepositions
     */
    public List< List< String > > joinNeighboringPrepositions( List< List< String > > bothLists ) {
    	List< String > sentenceComponents = bothLists.get( 0 );
    	List< String > wordList = bothLists.get( 1 );
		String prepositionOne;															// first preposition variable
		String prepositionTwo;															// second preposition variable
    	for( int i = 0; i < sentenceComponents.size(); i++ ) {							// for loop to find prepositions in the sentenceComponents
    		if( sentenceComponents.get( i ).equals( "preposition" ) ) { 				// finds preposition in wordList
    			prepositionOne = wordList.get( i );										// assigns found preposition to first variable
    			if( i + 1 <= sentenceComponents.size() - 1) { 							// checks for index out of bounds
	    			if( sentenceComponents.get( i + 1 ).equals( "preposition" ) ) { 	// determines if next is also preposition
	    				prepositionTwo = wordList.get( i + 1 );							// assigns found preposition to second variable
	    				String joinedPreposition = prepositionOne + prepositionTwo;		// joins the two prepositions to one variable
	    				wordList.remove( i );											// removes preposition to be replaced
	    				wordList.remove( i );					//<===== check this     // removes preposition to be replaced
	    				wordList.add( i, joinedPreposition );							// adds joined preposition
	    				sentenceComponents.remove( i );									// removes preposition marker
	    				i--;															// correction for the for loop
	    			}
    			}
    		}
    	}
//    	System.out.println( wordList );
//    	System.out.println( sentenceComponents );
    	return bothLists;
    	
    }
    
    /**
     * This method joins the adjectives to their respective nouns
     * and then removes the adjectives from the word list
     */
    public Map< String, String > joinAdjectiveNoun( List< List< String > > bothLists ){
    	Map< String, String > nounAdjectives = new HashMap< String, String >();
    	List< String > sentenceComponents = bothLists.get( 0 );
    	List< String > wordList = bothLists.get( 1 );
    	for( int i = 0; i < sentenceComponents.size(); i++ ) {
    		if( sentenceComponents.get( i ).equals( "adjective" ) ) { 						// finds adjective in wordList
    			String adjective = wordList.get( i ); 										// sets adjective to variable
    			String noun;
    			if( i + 1 <= sentenceComponents.size() - 1) { 								// checks for index out of bounds
	    			if( sentenceComponents.get( i + 1 ).equals( "noun" ) ) { 				// determines if describing noun is immediate
	    				noun = wordList.get( i + 1 ); 										// sets noun variable
	    				nounAdjectives.put( adjective, noun ); 								// places pair in map
	    			} else if( sentenceComponents.get( i + 1 ).equals( "adjective" ) ) { 	// determines if second adjective follows first
	    				if( i + 2 <= sentenceComponents.size() - 1 ) { 						// checks for index out of bounds
		    				if( sentenceComponents.get( i + 2 ).equals( "noun" ) ) { 		// determines if noun follows second adjective
		    					noun = wordList.get( i + 2 ); 								// sets noun
		    					nounAdjectives.put( adjective, noun ); 						// places pair in map
		    					adjective = wordList.get( i + 1 ); 							// sets second adjective
		    					nounAdjectives.put( adjective, noun ); 						// places pair in map
		    				}
	    				}
	    			}
    			}
    		}
    	}
    	for( int i = 0; i < sentenceComponents.size(); i++ ) {
    		if( sentenceComponents.get( i ).equals( "adjective" ) ) {
    			sentenceComponents.remove( i ); 						// removes adjective from the sentenceComponent
    			wordList.remove( i ); 									// removes this adjective from the wordList
    			i--; 													// shift count to account for deletion of adjective
    		}
    	}
//    	System.out.println( wordList );
//    	System.out.println( sentenceComponents );
//    	System.out.println( nounAdjectives );
    	return nounAdjectives;
    }
    
    public List< String > SentenceComponents( List< String > wordList ) {
    	List< String > components = new ArrayList<>();
		for( int i = 0; i < wordList.size(); i++ ) {
			if( knownVerbs.check( wordList.get( i ) ) && knownNouns.check( wordList.get( i ) ) ) {
				components.add( i , "verbNoun" );
			} else if( knownVerbs.check( wordList.get( i ) ) ) {
				components.add( i, "verb");
			} else if( knownNouns.check( wordList.get( i ) ) ) {
				components.add( i, "noun" );
			} else if( knownArticles.check( wordList.get( i ) ) ) {
				components.add( i, "article" );
			} else if( knownPrepositions.check( wordList.get( i ) ) ) {
				components.add( i, "preposition" );
			} else if( knownAdjectives.check( wordList.get( i ) ) ) {
				components.add( i, "adjective" );
			} else {
				components.add( i, "unknown" );
			}
		}
//		System.out.println( wordList ); // remove when done
//		System.out.println( components ); // remove when done
    	return components;
    }
   
    public List< String > WordList( String input ) {
    	
        String delims = " \t,.:;?!\"'";
        List< String > stringList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer( input, delims );
        String t;

        while ( tokenizer.hasMoreTokens() ) {
            t = tokenizer.nextToken();
            
            stringList.add( t );
        }
        
        return stringList;
    }
	
    public String RunCommand( String inputstr ) {
        List< String > wordList;
        String s = "ok";
        String lowString = inputstr.trim().toLowerCase();   

        if ( !lowString.equals( "q" ) ) {
            if ( lowString.equals( "" ) ) {
                s = "You must enter a command";
            } else {
                wordList = WordList( lowString );
                s = parseCommand( wordList );
            }
        }
        return s;
    }
    
    public void roomChange() {
    	
    	this.roomChange = true;
    }
    
    public void update() throws IOException {
    	
    	if( Game.in != null ) {

//			Platform.runLater(() -> {
				
    		try {
    			
				input = Game.in.readLine();
				
	    		output = RunCommand( input );
	    		
				userInterface.println( output );
				
	    		userInterface.getDisplay().setRoom( Game.currentRoom );
	    		
	      		Game.in.close();
	      		Game.in = null;
	      		
	      		
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}

//			});

    	} else if( roomChange == true ) {

        	userInterface.getDisplay().setRoom( Game.currentRoom );
        	
        	roomChange = false;

    	}
    }
    
    public void render() {
    	
    	userInterface.getGuiController().setTileMap();
    	
    }
    
	public KnownVerbs getKnownVerbs() {
		return this.knownVerbs;
	}

	public void setKnownVerbs( KnownVerbs knownVerbs ) {
		this.knownVerbs = knownVerbs;
	}

	public KnownNouns getKnownNouns() {
		return knownNouns;
	}

	public void setKnownNouns(KnownNouns knownNouns) {
		this.knownNouns = knownNouns;
	}

	public KnownPrepositions getKnownPrepositions() {
		return knownPrepositions;
	}

	public void setKnownPrepositions(KnownPrepositions knownPrepositions) {
		this.knownPrepositions = knownPrepositions;
	}

	public KnownArticles getKnownArticles() {
		return knownArticles;
	}

	public void setKnownArticles(KnownArticles knownArticles) {
		this.knownArticles = knownArticles;
	}
	
	public KnownAdjectives getKnownAdjectives() {
		return knownAdjectives;
	}

	public void setKnownAdjectives( KnownAdjectives knownAdjectives ) {
		this.knownAdjectives = knownAdjectives;
	}
    
}
