package components;

import imgui.ImGui;
import jade.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {

    private Vector4f color;
    private Sprite sprite;
    private transient Transform lastTransform;
    private transient boolean isDirty = false;


//    public SpriteRenderer( Vector4f color ) {
//        this.color = color;
//        this.sprite = new Sprite( null );
//        this.isDirty = true;
//    }

    public SpriteRenderer( Sprite sprite ) {
        this.color = new Vector4f(1, 1, 1, 1);
        this.sprite = sprite;
        this.isDirty = true;
    }

    @Override
    public void start() {
        this.lastTransform = gameImage.transform.copy();
    }

    @Override
    public void update( float dt ) {

        if( !this.lastTransform.equals( this.gameImage.transform ) ) {

//            if( !this.gameImage.isVisible() ) {
//
//                Transform nullTransform = new Transform( new Vector2f( 0, 0 ), new Vector2f( 0, 0 ) );
//                nullTransform.copy( this.gameImage.transform );
//            }

            System.out.println( this.gameImage.getName() + " " + this.gameImage.transform.position );
            this.gameImage.transform.copy( this.lastTransform );
            isDirty = true;
        } else if( this.gameImage.getName() != null ) {
            if( this.gameImage.getName().equals("Ring of Might and Madness")){
                System.out.println( this.gameImage.getName() + " " + this.gameImage.transform.position );
            }

        }
    }

    @Override
    public void imgui() {

        float[] imColor = { color.x, color.y, color.z, color.w };

        if( ImGui.colorPicker4( "Color Picker: ", imColor ) ) {

            this.color.set( imColor[ 0 ], imColor[ 1 ], imColor[ 2 ], imColor[ 3 ] );
            this.isDirty = true;
        }
    }

    public Vector4f getColor() {
        return this.color;
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }

    public void setSprite( Sprite sprite ) {

        this.sprite = sprite;
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

    public void setTexture( Texture texture ) { this.sprite.setTexture( texture ); }
}
