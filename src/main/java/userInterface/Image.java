package userInterface;

import components.SpriteRenderer;
import components.SpriteSheet;
import components.Component;
import jade.Transform;
import jade.Transition;

import java.util.ArrayList;
import java.util.List;

public class Image {

	private static int ID_COUNTER = 0;
	private int uid = -1;
	private String name;
	private int tileImageNumber;
	private String ImageResourcePath;
	private SpriteSheet spriteSheet;
	private boolean isAnimated;
	private float timeAccumulation; // in milliseconds
	private float lastUpdate;
	private int activeTilesetPosition;
	private int imageWidth;
	private int imageHeight;
	private int imageFrameCount;
	private List< Component > components;
	public Transform transform;
	public Transition transition;
	private int zIndex;

	private float[] frameDuration;

	public Image() {

		this.components = new ArrayList<>();
		this.transition = null;
		this.uid = ID_COUNTER++;
	}

	public Image( String name, Transform transform, int zIndex ) {

		this.name = name;
		this.components = new ArrayList<>();
		this.transform = transform;
		this.zIndex = zIndex;
		this.uid = ID_COUNTER++;
	}

	public < T extends Component > T getComponent( Class< T > componentClass ) {

		if( components != null ) {

			for ( Component c : components ) {

				if ( componentClass.isAssignableFrom( c.getClass() ) ) {

					try {

						return componentClass.cast( c );

					} catch ( ClassCastException e ) {

						e.printStackTrace();
						assert false : "ERROR: Casting component.";
					}
				}
			}
		}
		return null;
	}

	public < T extends  Component > void removeComponent( Class< T > componentClass ) {

		for( int i = 0; i < components.size(); i++ ) {

			Component C = components.get( i );

			if( componentClass.isAssignableFrom( components.getClass() ) ) {

				components.remove( i );
				return;
			}
		}
	}

	public void addComponent( Component c ) {

		c.generateId();
		this.components.add( c );
		c.gameImage = this;
	}

	public void update( float dt ) {

		if( this.isAnimated() ) {

			boolean swapReady = frameSwapTimeCheck( dt );

			if( swapReady ) {

				cycleActiveFrame();
			}
		}
		if( transition != null ) {

			transition.update();

			if( transition.isComplete() ) {

				transition = null;
			}
		}
		for (Component component : components) {

			component.update( dt );
		}
	}

	public void start() {

		this.setTimeAccumulation( 0 );

		if( components != null ) {

			for (Component component : components) {

				component.start();
			}
		}
	}

	public int zIndex() {
		return this.zIndex;
	}

	public boolean frameSwapTimeCheck( float dt ) {

		updateTimeAccumulation( dt );

		if( timeAccumulation >= frameDuration[ activeTilesetPosition ] ) {
			lastUpdate = dt;
			timeAccumulation = 0;
			return true;
		}
		return false;
	}

	public static void init( int maxId ) { ID_COUNTER = maxId; }

	public int getUid() { return this.uid; }

	private void updateTimeAccumulation( float dt ) { timeAccumulation += dt; }

	public void increaseTimeAccumulation( float dt ) { timeAccumulation += dt; }

	public void setTimeAccumulation( float dt ) { timeAccumulation = dt; }

	public void cycleActiveFrame() {

		if( !( activeTilesetPosition + 1 >= imageFrameCount ) ) {

			activeTilesetPosition += 1;

		} else {

			activeTilesetPosition = 0;
		}

		this.getComponent( SpriteRenderer.class ).setSprite( this.getSpriteSheet().getSprite( activeTilesetPosition ) );
	}

	public void imgui() {

		for( Component component : components ) {

			component.imgui();
		}
	}

	public List<Component> getAllComponents() { return this.components; }

	public int getTileImageNumber() { return tileImageNumber; }

	public void setTileImageNumber( int tileImageNumber ) { this.tileImageNumber = tileImageNumber; }

	public int getImageWidth() { return imageWidth; }

	public void setImageWidth( int imageWidth ) { this.imageWidth = imageWidth; }

	public int getImageHeight() { return imageHeight; }

	public void setImageHeight( int imageHeight ) { this.imageHeight = imageHeight; }

	public String getName() { return name; }

	public void setName( String name ) { this.name = name; }

	public int getzIndex() { return zIndex; }

	public void setzIndex( int zIndex ) { this.zIndex = zIndex; }

	public int getActiveTilesetPosition() { return activeTilesetPosition; }

	public void setActiveTilesetPosition( int tilesetPosition ) { this.activeTilesetPosition = tilesetPosition; }

	public String getImageResourcePath() { return ImageResourcePath; }

	public void setImageResourcePath( String imageResourcePath ) { ImageResourcePath = imageResourcePath; }

	public int getImageFrameCount() { return imageFrameCount; }

	public void setImageFrameCount( int imageFrameCount ) { this.imageFrameCount = imageFrameCount; }

	public SpriteSheet getSpriteSheet() { return spriteSheet; }

	public void setSpriteSheet( SpriteSheet spriteSheet ) { this.spriteSheet = spriteSheet; }

	public boolean isAnimated() { return isAnimated; }

	public void setAnimated( boolean isAnimated ) { this.isAnimated = isAnimated; }

	public float[] getFrameDuration() { return frameDuration; }

	public void setFrameDuration( float[] frameDuration ) { this.frameDuration = frameDuration; }

}
