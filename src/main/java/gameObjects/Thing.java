package gameObjects;

import java.util.ArrayList;

import components.SpriteRenderer;
import components.SpriteSheet;
import game.Game;
import jade.RoomScene;
import jade.Transform;
import userInterface.TSX;
import userInterface.TileChar;
import light.Light;
import userInterface.Image;
import util.AssetPool;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;
import world.UpdateWorldMethods;

public class Thing {
	
	protected transient GameTile currentGameTile;

	protected String name;
    protected String description;
    private String readableLargeText;
    private String readableSmallText;
    private String readableLargeTextLocation;
    private String readableSmallTextLocation;
    protected String locationInRoom;
    protected String matterState; 
    protected String isOwnedBy;
    
    private Thing heldBy;
    
    protected String[] descriptors;
    protected String[] altNames;
    
    private ArrayList< Light > lightSources;
    
    protected int size;							// size scale in drops or grains (same base unit size)
    protected double weight;					// weight
    protected int value;						// value
    protected double height;					// height used to calculate how light reflects off the object
    
    protected boolean drinkable;
    protected boolean eatable;
    protected boolean holdable;
    protected boolean readable;
    protected boolean smellable;
    protected boolean visible;
    protected boolean wearable;
    protected boolean attackable;
    protected boolean wieldable;
    protected boolean donned;
	protected boolean isOnTopOf;
	protected boolean isUnderneath;
	protected boolean isContainedWithin;
    protected boolean isThingHolder;
    protected boolean isTranslucent;
    protected boolean isLightSource;
    protected boolean isActor;
    protected boolean isNPC;
    protected boolean isGameTile;
    protected boolean isGameRoom;
    protected boolean hasLargeText;
    protected boolean hasSmallText;
    protected boolean hasThingList;
    
//    protected String tileChar;
    private String tsxPath;
    private String tmx;

	private String imageResourcePath;
    private int imageXPosition;
    private int imageYPosition;
    private TileChar tileChar;
    private Image[] thingImages;

	private Image thingImage;
   
    
    public String type = "Thing";
    
    public Thing() {
    	this.name = "";
    	this.altNames = new String[0];
    	this.description = "";
    	this.tileChar = new TileChar();
    	this.readableLargeText = "";
    	this.readableSmallText = "";
    	this.readableLargeTextLocation = "";
    	this.readableSmallTextLocation = "";
    	this.setLocationInRoom( "" );
    	this.setMatterState( "" );
    	this.setIsOwnedBy( "" );
    	
    	this.setSize( 0 );
    	this.setWeight( 0.0 );
    	this.setValue( 0 );
    	this.setHeight( 0.0 );
    	
    	this.setDrinkable( false );
    	this.setTranslucence( false );
    	this.setHoldable( false );
    	this.setReadable( false );
    	this.setSmellable( false );
    	this.setHasLargeText(false);
    	this.setHasSmallText(false);
		this.setOnTopOf( false );
		this.setUnderneath( false );
		this.setContainedWithin( false );
		this.setDonned( false );
		this.setAttackable( false );
		this.setGameTile( false );
    }

    public Thing( String aName, String aDescription ) {
        // constructor
        this.name = aName;
        this.altNames = new String[0];
        this.description = aDescription;
        this.readableLargeText = "";
        this.readableSmallText = "";
        this.readableLargeTextLocation = "";
        this.readableSmallTextLocation = "";
        this.setLocationInRoom( "" );
    	this.setMatterState( "" );
    	this.setIsOwnedBy( "" );
    	
    	this.setSize( 0 );
    	this.setWeight( 0.0 );
    	this.setHeight( 0.0 );
    	
        this.setDrinkable( false );
        this.setTranslucence( false );
        this.setHoldable( false );
        this.setReadable( false );
    	this.setSmellable( false );
    	this.setHasLargeText(false);
    	this.setHasSmallText(false);
		this.setOnTopOf( false );
		this.setUnderneath( false );
		this.setContainedWithin( false );
		this.setAttackable( false );
		this.setGameTile( false );
    }

    public String getName() {
        return name;
    }
    
    public String[] getAltNames() {
    	return altNames;
    }
    
    public void setAltNames( String[] altNames ) {
    	this.altNames = altNames;
    }

    public void setName( String aName ) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        this.description = aDescription;
    }
    
    public boolean isDrinkable() {
    	return this.drinkable;
    }
    
    public void setDrinkable( boolean isDrinkable ) {
    	this.drinkable = isDrinkable;
    }
    
    public boolean isEatable() {
    	return this.eatable;
    }
    
    public void setEatable( boolean isEatable ) {
    	this.eatable = isEatable;
    }
    
    public boolean isReadable() {
    	return this.readable;
    }
    
    public void setReadable( boolean isReadable ) {
    	this.readable = isReadable;
    }
    
    public String getReadableLargeText() {
    	return this.readableLargeText;
    }
    
    public void setReadableLargeText( String readableLargeText ) {
    	this.readableLargeText = readableLargeText;
    }
    
    public String getReadableLargeTextLocation() {
    	return this.readableLargeTextLocation;
    }
    
    public void setReadableLargeTextLocation( String readableLargeTextLocation ) {
    	this.readableLargeTextLocation = readableLargeTextLocation;
    }
    
    public String getReadableSmallText() {
    	return this.readableSmallText;
    }
    
    public void setReadableSmallText( String readableSmallText ) {
    	this.readableSmallText = readableSmallText;
    }
    
    public String getReadableSmallTextLocation() {
    	return this.readableSmallTextLocation;
    }
    
    public void setReadableSmallTextLocation( String readableSmallTextLocation ) {
    	this.readableSmallTextLocation = readableSmallTextLocation;
    }
    
    public String getMatterState() {
    	return this.matterState;
    }
    
    public void setMatterState( String matterState ) {
    	this.matterState = matterState;
    }
    
    public int getSize() {
    	return this.size;
    }
    
    public void setSize( int aSize ) {
    	this.size = aSize;
    }
    
	@Override
	public String toString() {
		return this.name;
	}

	public String getLocationInRoom() {
		return locationInRoom;
	}

	public void setLocationInRoom(String locationInRoom) {
		this.locationInRoom = locationInRoom;
	}

	public boolean hasLargeText() {
		return hasLargeText;
	}

	public void setHasLargeText(boolean hasLargeText) {
		this.hasLargeText = hasLargeText;
	}

	public boolean hasSmallText() {
		return hasSmallText;
	}

	public void setHasSmallText(boolean hasSmallText) {
		this.hasSmallText = hasSmallText;
	}

	public boolean isHoldable() {
		return holdable;
	}

	public void setHoldable(boolean holdable) {
		this.holdable = holdable;
	}

	public boolean isTranslucent() {
		return isTranslucent;
	}

	public void setTranslucence(boolean isTranslucent) {
		this.isTranslucent = isTranslucent;
	}

	public boolean isSmellable() {
		return smellable;
	}

	public void setSmellable(boolean smellable) {
		this.smellable = smellable;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isThingHolder() {
		return isThingHolder;
	}

	public void setThingHolder(boolean isThingHolder) {
		this.isThingHolder = isThingHolder;
	}
	
	public boolean isUnderneath() {
		return isUnderneath;
	}

	public void setUnderneath(boolean isUnderneath) {
		this.isUnderneath = isUnderneath;
	}

	public boolean isOnTopOf() {
		return isOnTopOf;
	}

	public void setOnTopOf(boolean isOnTopOf) {
		this.isOnTopOf = isOnTopOf;
	}

	public boolean isContainedWithin() {
		return isContainedWithin;
	}

	public void setContainedWithin(boolean isContainedWithin) {
		this.isContainedWithin = isContainedWithin;
	}

	public boolean isWearable() {
		return wearable;
	}

	public void setWearable(boolean wearable) {
		this.wearable = wearable;
	}

	public String isOwnedBy() {
		return isOwnedBy;
	}

	public void setIsOwnedBy(String isOwnedBy) {
		this.isOwnedBy = isOwnedBy;
	}
	
	public int getValue() {
		
		return value;
	}
	
	public void setValue( int aValue ) {
		this.value = aValue;
	}

	public boolean isDonned() {
		return donned;
	}

	public void setDonned(boolean donned) {
		this.donned = donned;
	}
	
	public boolean isAttackable() {
		return attackable;
	}

	public void setAttackable( boolean attackable ) {
		this.attackable = attackable;
	} 
	
	public GameTile getCurrentGameTile() {
		return currentGameTile;
	}

	public void setCurrentGameTile(GameTile currentGameTile) {
		this.currentGameTile = currentGameTile;
	}
	
	// Adds a reference to the thingholder
	public void setLocations() {
		
		if( this.isThingHolder ) {
			ThingHolder tHolder = ( ThingHolder ) this;
			ThingList t = tHolder.getThings();
			
			for( int i = 0; i < t.size(); i++ ) {
				Thing aThing = t.get( i );
				if( aThing.isThingHolder() ) {
					
					aThing.setLocations();
				}
				
				if( aThing.type.equals( "World" ) ) {
					
					return;
					
				} else if( aThing.type.equals( "Map" ) ) {
					
					GameMap aMap = ( GameMap ) aThing;
					aMap.setWorld( ( GameWorld ) tHolder );
					
				} else if( aThing.type.equals( "Room" ) ) {
					
					GameRoom aRoom = ( GameRoom ) aThing;
					aRoom.setMap( ( GameMap ) tHolder );
				
				} else if( aThing.type.equals( "Tile" ) ) {
					
					GameTile aTile = ( GameTile ) aThing;
					aTile.setRoom( ( GameRoom ) tHolder );
					
					for( int k = 0; k < aTile.getNPCs().size(); k++ ) {
						
						aTile.getNPCs().get( k ).setCurrentGameTile( aTile );
						this.setHeldBy( aTile );
					}
					
				} else {
					
					if(tHolder.type.equals( "Tile" ) ) {
					
						GameTile tile = ( GameTile ) tHolder;
						aThing.setCurrentGameTile( tile );
						
					}	
					aThing.setHeldBy( tHolder );
				}
			}
		}
	}
	/*
	 * Recursively locates all thing objects in the world and .
	 */
	public void allLists() {
		
		UpdateWorldMethods commands = new UpdateWorldMethods();

		if( this.isThingHolder ) {
			ThingHolder tHolder = ( ThingHolder ) this;
			ThingList t = tHolder.getThings();
			
			for( int i = 0; i < t.size(); i++ ) {
				Thing aThing = t.get( i );
				if( aThing.isThingHolder() ) {
					
					aThing.allLists();
				}
				
				if( aThing.type.equals( "Tile" ) ) {
					
					GameTile aTile = ( GameTile ) aThing;
					aTile.setRoom( ( GameRoom ) tHolder );
					
					for( int j = 0; j < aTile.getNPCs().size(); j++ ) {
						Thing thing = aTile.getNPCs().get( j );
						commands.getUpdateCommand( "actors" ).run( thing );
					}
					
				} else {
					
					commands.getUpdateCommand( "environment" ).run( aThing );		
				}
			}
		}
	}
	
	public void allLists( String updateMethodName ) {
		
		UpdateWorldMethods commands = new UpdateWorldMethods();

		if( this.isThingHolder ) {
			ThingHolder tHolder = ( ThingHolder ) this;
			ThingList t = tHolder.getThings();
			
			for( int i = 0; i < t.size(); i++ ) {
				Thing aThing = t.get( i );
				if( aThing.isThingHolder() ) {
					
					aThing.allLists( updateMethodName );
				}
				
				if( aThing.type.equals( "Tile" ) ) {
					
					GameTile aTile = ( GameTile ) aThing;
					aTile.setRoom( ( GameRoom ) tHolder );
					
					for( int j = 0; j < aTile.getNPCs().size(); j++ ) {
						Thing thing = aTile.getNPCs().get( j );
						commands.getUpdateCommand( updateMethodName ).run( thing );
					}
					
				} else {
					
					commands.getUpdateCommand( updateMethodName ).run( aThing );		
				}
			}
		}
	}

	public void initAllImages( Image baseImage ) {

		if( this.isThingHolder ) {

			ThingHolder tHolder = ( ThingHolder ) this;
			ThingList things = tHolder.getThings();

			for( int i = 0; i < things.size(); i++ ) {

				Thing aThing = things.get( i );

				aThing.initAllImages( baseImage );
			}

			if( tHolder.type.equals( "Tile" ) ) {

				GameTile gt = ( GameTile ) tHolder;

				if( gt.equals( Game.currentTile ) ) {

					Game.currentGame.getPlayer().initAllImages( baseImage );
					System.out.println( "currentPlayer init" );
				}

				for( int j = 0; j < gt.getNPCs().size(); j++ ) {

					Thing npc = gt.getNPCs().get( j );
					npc.initImage( baseImage );
				}

			} else {

				this.initImage( baseImage );
			}

		} else {

			this.initImage( baseImage );
		}
	}

	private void initImage( Image baseImage ) {

		System.out.println( this.getName() );

		if( this.getTSXPath() != null ) {

			TSX tsx = new TSX( this.getTSXPath() );

			Image thingImage = new Image();
			thingImage.setName( this.getName() );
			thingImage.setImageResourcePath( tsx.getImageSourcePath() );
			thingImage.setImageWidth( tsx.getTileWidth() );
			thingImage.setImageHeight( tsx.getTileHeight() );
			thingImage.setImageFrameCount( tsx.getFrames().getLength() );
			thingImage.transform = new Transform();
			thingImage.setActiveTilesetPosition( 0 );
			thingImage.setAnimated( tsx.isAnimated() );
			thingImage.setFrameDuration( tsx.getFrameDuration() );

			System.out.println( thingImage.getImageResourcePath() );

			AssetPool.addSpriteSheet( thingImage.getImageResourcePath(),
					new SpriteSheet( AssetPool.getTexture( thingImage.getImageResourcePath() ),
							thingImage.getImageWidth(),
							thingImage.getImageHeight(),
							thingImage.getImageFrameCount(),
							0) );

			thingImage.setSpriteSheet( AssetPool.getSpriteSheet( thingImage.getImageResourcePath() ) );
			thingImage.setzIndex( 0 );
			thingImage.addComponent(
					new SpriteRenderer(
							thingImage.getSpriteSheet().getSprite(
									thingImage.getActiveTilesetPosition() ) ) );

			baseImage.transform.copy( thingImage.transform );
			this.setThingImage( thingImage );
		}
	}

	public boolean isNPC() {
		return isNPC;
	}

	public void setNPC( boolean isNPC ) {
		this.isNPC = isNPC;
	}

	public boolean isWieldable() {
		return wieldable;
	}

	public void setWieldable( boolean wieldable ) {
		this.wieldable = wieldable;
	}

	public boolean isActor() {
		return isActor;
	}

	public void setActor( boolean isActor ) {
		this.isActor = isActor;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible( boolean visible ) {
		this.visible = visible;
	}

	public String[] getDescriptors() {
		return descriptors;
	}

	public void setDescriptors(String[] descriptors) {
		this.descriptors = descriptors;
	}

	public TileChar getTileChar() {
		return tileChar;
	}

	public void setTileChar( TileChar tileChar ) {
		this.tileChar = tileChar;
	}

	public boolean isGameTile() {
		
		return isGameTile;
	}

	public void setGameTile( boolean isGameTile ) {
		
		this.isGameTile = isGameTile;
	}

	public boolean isGameRoom() {
		
		return isGameRoom;
	}
	
	public Image[] getThingImages() {
		
		return thingImages;
	}
	
	public void setThingImages( Image[] tileImages ) {
		
		this.thingImages = tileImages;
	}

	public String getTSXPath() {
		
		return tsxPath;
	}

	public void setTSXPath( String tsxPath ) {
		
		this.tsxPath = tsxPath;
	}

	public String getTMX() {
		
		return tmx;
	}

	public void setTMX( String tmx ) {
		
		this.tmx = tmx;
	}

	public ArrayList< Light > getLightSources() {
		return lightSources;
	}

	public void setLightSources( ArrayList< Light > lightSources) {
		this.lightSources = lightSources;
	}

	public int getImageXPosition() {
		return imageXPosition;
	}

	public void setImageXPosition(int imageXPosition) {
		this.imageXPosition = imageXPosition;
	}

	public int getImageYPosition() {
		return imageYPosition;
	}

	public void setImageYPosition(int imageYPosition) {
		this.imageYPosition = imageYPosition;
	}

	public boolean isLightSource() {
		
		return isLightSource;
	}

	public void setLightSource( boolean isLightSource ) {
		
		this.isLightSource = isLightSource;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight( double height ) {
		
		this.height = height;
	}

	public Thing getHeldBy() {
		
		return heldBy;
	}

	public void setHeldBy( Thing heldBy ) {
		
		this.heldBy = heldBy;
	}

	public String getImageResourcePath() { return imageResourcePath; }

	public void setImageResourcePath( String imageResourcePath ) {

		this.imageResourcePath = imageResourcePath;
	}

	public Image getThingImage() { return thingImage; }

	public void setThingImage( Image thingImage ) { this.thingImage = thingImage; }
}
