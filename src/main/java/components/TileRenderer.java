package components;

import jade.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class TileRenderer extends Component {

    private Vector4f color;
    private Tile tile;
    private Transform lastTransform;
    private boolean isDirty = false;

    public TileRenderer( Vector4f color ) {
        this.color = color;
        this.tile = new Tile( null );
        this.isDirty = true;
    }

    public TileRenderer( Tile tile ) {
        this.color = new Vector4f(1, 1, 1, 1);
        this.tile = tile;
        this.isDirty = true;
    }

    @Override
    public void start() {
        this.lastTransform = gameImage.transform.copy();
    }

    @Override
    public void update( float dt ) {

        if( !this.lastTransform.equals( this.gameImage.transform ) ) {

            this.gameImage.transform.copy( this.lastTransform );
            isDirty = true;
        }
    }

    public Vector4f getColor() {
        return this.color;
    }

    public Texture getTexture() {
        return tile.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return tile.getTexCoords();
    }

    public void setTile( Tile tile ) {

        this.tile = tile;
        this.isDirty = true;
    }

    public void setColor( Vector4f color ) {

        if ( !this.color.equals( color ) ) {

            this.color.set( color );
            this.isDirty = true;
        }
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    public void setClean() {
        this.isDirty = false;
    }
}