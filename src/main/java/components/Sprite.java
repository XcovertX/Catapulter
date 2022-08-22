package components;

import org.joml.Vector2f;
import renderer.Texture;

public class Sprite {

    private float width, height;
    private Texture texture;
    private Vector2f[] texCoords;

    public Sprite( Texture texture ) {

        Vector2f[] texCoords = {

                new Vector2f( 1, 1 ),
                new Vector2f( 1, 0 ),
                new Vector2f( 0, 0 ),
                new Vector2f( 0, 1 )
        };
        init( texture, texCoords );
    }

    public Sprite( Texture texture, Vector2f[] texCoords ) {
        init( texture, texCoords );
    }

    public void init( Texture texture, Vector2f[] texCoords ) {

        this.texture = texture;
        this.texCoords = texCoords;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Vector2f[] getTexCoords() {
        return this.texCoords;
    }

    public void setTextureCoords( Vector2f[] textureCoords ) {
        this.texCoords = textureCoords;
    }

    public float getWidth() { return width; }

    public void setWidth( float width ) { this.width = width; }

    public float getHeight() { return height; }

    public void setHeight( float height ) { this.height = height; }

    public int getTextureId() { return texture == null ? -1 : texture.getId(); }
}
