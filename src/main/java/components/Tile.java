package components;

import org.joml.Vector2f;
import renderer.Texture;

public class Tile {

    private Texture texture;
    private Vector2f[] texCoords;

    public Tile( Texture texture ) {

        Vector2f[] texCoords = {

                new Vector2f( 1, 1 ),
                new Vector2f( 1, 0 ),
                new Vector2f( 0, 0 ),
                new Vector2f( 0, 1 )
        };
        init( texture, texCoords );
    }

    public Tile( Texture texture, Vector2f[] texCoords ) {
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


}
