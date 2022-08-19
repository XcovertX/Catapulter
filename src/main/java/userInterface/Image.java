package userInterface;

import jade.Component;
import jade.Transform;

import java.util.ArrayList;
import java.util.List;

public class Image {

	private String name;
	private int tileImageNumber;

	private int tilesetPosition;
	private int imageWidth;
	private int imageHeight;
	private List< Component > components;
	public Transform transform;
	private int zIndex;
	private ImageLayer[] imageLayers; // 0 = base layer
	
	public Image() { }
	
	public Image( int layerCount, int tileImageNum ) {
		
		this.imageLayers = new ImageLayer[ layerCount ];
		this.tileImageNumber = tileImageNum;
		this.components = new ArrayList<>();
	}

	public Image( String name, Transform transform, int zIndex ) {

		init( name, new ArrayList<>(), transform, zIndex );
	}

	public < T extends Component > T getComponent( Class< T > componentClass ) {

		if( components != null ) {

			for (Component c : components) {

				if (componentClass.isAssignableFrom(c.getClass())) {

					try {

						return componentClass.cast(c);

					} catch (ClassCastException e) {

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

		this.components.add( c );
		c.gameImage = this;
	}

	public void update( float dt ) {

		for( int i = 0; i < components.size(); i++ ) {

			components.get( i ).update( dt );
		}
	}

	public void start() {

		if( components != null ) {

			for (int i = 0; i < components.size(); i++) {

				components.get(i).start();
			}
		}
	}

	public void init( String name, List<Component> components, Transform transform, int zIndex ) {

		this.name = name;
		this.components = components;
		this.transform = transform;
		this.zIndex = zIndex;
	}

	public int zIndex() {
		return this.zIndex;
	}
	
	public ImageLayer[] getImageLayers() { return imageLayers; }
	
	public void setImageLayers( ImageLayer[] imageLayers ) { this.imageLayers = imageLayers; }
	
	public ImageLayer getImageLayer( int index ) { return imageLayers[ index ]; }
	public void setImageLayer( int index, ImageLayer imageLayer ) { this.imageLayers[ index ] = imageLayer; }

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

	public int getTilesetPosition() { return tilesetPosition; }

	public void setTilesetPosition( int tilesetPosition ) { this.tilesetPosition = tilesetPosition; }

}
