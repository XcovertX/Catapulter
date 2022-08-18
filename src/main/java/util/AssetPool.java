package util;

import components.SpriteSheet;
import components.TileSheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map< String, Shader > shaders = new HashMap<>();
    private static Map< String, Texture > textures = new HashMap<>();
    private static Map< String, SpriteSheet > spriteSheets = new HashMap<>();
    private static Map< String, TileSheet > tileSheets = new HashMap<>();

    public static Shader getShader( String resourceName ) {

        File file = new File( resourceName );

        if( AssetPool.shaders.containsKey( file.getAbsolutePath() ) ) {

            return AssetPool.shaders.get( file.getAbsolutePath() );

        } else {

            Shader shader = new Shader( resourceName );
            shader.compile();
            AssetPool.shaders.put( file.getAbsolutePath(), shader );
            return shader;
        }
    }

    public static Texture getTexture( String resourceName ) {

        File file = new File( resourceName );

        if( AssetPool.textures.containsKey( file.getAbsolutePath() ) ) {

            return AssetPool.textures.get( file.getAbsolutePath() );

        } else {

            Texture texture = new Texture( resourceName );
            AssetPool.textures.put( file.getAbsolutePath(), texture );
            return texture;
        }
    }

    public static void addSpriteSheet( String resourceName, SpriteSheet spriteSheet ) {

        File file = new File( resourceName );

        if( !AssetPool.spriteSheets.containsKey( file.getAbsolutePath() ) ) {

            AssetPool.spriteSheets.put( file.getAbsolutePath(), spriteSheet );
        }
    }

    public static SpriteSheet getSpriteSheet( String resourceName ) {

        File file = new File( resourceName );

        if( !AssetPool.spriteSheets.containsKey( file.getAbsolutePath() ) ) {

            assert false : "ERROR: Failed to get a spritesheet at '" + resourceName +"' ";
        }
        return AssetPool.spriteSheets.getOrDefault( file.getAbsolutePath(), null );
    }

    public static void addTileSheet( String resourceName, TileSheet tileSheet ) {

        File file = new File( resourceName );

        if( !AssetPool.tileSheets.containsKey( file.getAbsolutePath() ) ) {

            AssetPool.tileSheets.put( file.getAbsolutePath(), tileSheet );
        }
    }

    public static TileSheet getTileSheet( String resourceName ) {

        File file = new File( resourceName );

        if( !AssetPool.tileSheets.containsKey( file.getAbsolutePath() ) ) {

            assert false : "ERROR: Failed to get a tilesheet at '" + resourceName +"' ";
        }
        return AssetPool.tileSheets.getOrDefault( file.getAbsolutePath(), null );
    }
}
