package components;

import org.joml.Vector2f;
import renderer.Texture;

import java.util.ArrayList;
import java.util.List;

public class TileSheet {

    public static int NORMAL_TILE_WIDTH = 32;
    public static int NORMAL_TILE_HEIGHT = 32;

    private Texture texture;
    private List< Tile > tiles;

    public TileSheet( Texture texture, int tileWidth, int tileHeight, int numTiles, int spacing ) {

        this.tiles = new ArrayList<>();
        this.texture = texture;
        int currentX = 0;
        int currentY = texture.getHeight() - tileHeight;

        for( int i = 0; i < numTiles; i++ ) {

            float topY    = ( currentY + tileHeight ) / ( float ) texture.getHeight();
            float rightX  = ( currentX + tileWidth )  / ( float ) texture.getWidth();
            float leftX   =   currentX / ( float ) texture.getWidth();
            float bottomY =   currentY / ( float ) texture.getHeight();

            Vector2f[] texCoords = {

                    new Vector2f( rightX, topY ),
                    new Vector2f( rightX, bottomY ),
                    new Vector2f( leftX,  bottomY ),
                    new Vector2f( leftX,  topY )
            };

            Tile tile = new Tile( this.texture, texCoords );
            this.tiles.add( tile );

            currentX += tileWidth + spacing;

            if( currentX >= texture.getWidth() ) {

                currentX = 0;
                currentY -= tileHeight + spacing;
            }
        }
    }

    public Tile getTile( int index ) {
        return this.tiles.get( index );
    }
}
