package jade;

import components.SpriteRenderer;
import components.SpriteSheet;
import components.TileRenderer;
import components.TileSheet;
import gameObjects.ThingList;
import org.joml.Vector2f;
import userInterface.Image;
import userInterface.TMX;
import userInterface.TMXParser;
import userInterface.TSX;
import util.AssetPool;
import world.GameRoom;
import world.GameTile;

public class RoomScene extends Scene {

//    private Image[] tileImages;
//    private Image[] thingImages;
//    private SpriteSheet[] spriteSheets;
    private Image gameImage;
    private SpriteSheet sprites;
    private TileSheet tiles;
    private SpriteSheet torch;

    private GameRoom gameRoom;

    public RoomScene() { }

    public RoomScene( GameRoom gameRoom ) { this.gameRoom = gameRoom; }

    @Override
    public void init() {

        TMXParser tmxParser = new TMXParser( gameRoom );
        TMX tmxFile = tmxParser.getTmx();
        TSX[] tsxFiles = tmxParser.getTSXs();

        loadResources( tsxFiles );

        this.camera = new Camera( new Vector2f( -250, 0 ) );

        sprites = AssetPool.getSpriteSheet( "assets/images/spritesheet.png" );

        torch = AssetPool.getSpriteSheet( "assets/images/torch_tileset.png" );
        gameImage = new Image( ":object 2", new Transform( new Vector2f( 0, 200 ),
                                                 new Vector2f( 100, 100 ) ),0 );
        gameImage.addComponent( new SpriteRenderer( torch.getSprite( 1 ) ) );
        this.addGameImageToScene( gameImage );
    }

    public void init( GameRoom gameRoom ) {

        this.gameRoom = gameRoom;

        TMXParser tmxParser = new TMXParser( gameRoom );
        TMX tmxFile = tmxParser.getTmx();
        TSX[] tsxFiles = tmxParser.getTSXs();

        loadResources( tsxFiles );

        this.camera = gameRoom.getCamera();

        sprites = AssetPool.getSpriteSheet( "assets/images/testcity_tileset.png" );
        ThingList gameTiles = gameRoom.getTiles();

        for( gameObjects.Thing gameTile : gameTiles ) {

            GameTile gt = ( GameTile ) gameTile;
            Image baseImage = gt.getBaseTileImage();
//            Image currentThingImage = gt.getCurrentThingImage();
            baseImage.addComponent( new SpriteRenderer( sprites.getSprite( 1) ) );
            this.addGameImageToScene( baseImage );

        }
//        sprites = AssetPool.getSpriteSheet( "assets/images/spritesheet.png" );
//        torch   = AssetPool.getSpriteSheet( "assets/images/torch_tileset.png" );
//
//        gameImage = new Image( ":object 2", new Transform( new Vector2f( 0, 200 ),
//                new Vector2f( 100, 100 ) ),0 );
//        gameImage.addComponent( new SpriteRenderer( torch.getSprite( 1 ) ) );
    }

    private void loadResources( TSX[] tsxFiles ) {

        // TODO determine how a shader is selected
        AssetPool.getShader( "assets/shaders/default.glsl" );

        for( int i = 0; i <  tsxFiles.length; i++ ) {

            TSX tsx = tsxFiles[ i ];
            String resourceName = tsx.getImageSourcePath();
            int spriteWidth     = tsx.getTileWidth();
            int spriteHeight    = tsx.getTileHeight();
            int spriteCount     = tsx.getTileCount();

            AssetPool.addSpriteSheet( resourceName,
                    new SpriteSheet( AssetPool.getTexture( resourceName ),
                                     spriteWidth, spriteHeight, spriteCount, 0 ) );
        }
    }

    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;

    @Override
    public void update( double  dt ) {
//
//        spriteFlipTimeLeft -= dt;
//
//        if( spriteFlipTimeLeft <= 0 ) {
//
//            spriteFlipTimeLeft = spriteFlipTime;
//            spriteIndex++;
//
//            if( spriteIndex > 3 ) {
//
//                spriteIndex = 0;
//            }
//            gameImage.getComponent( SpriteRenderer.class ).setSprite( torch.getSprite( spriteIndex ) );
//        }
//        dt = ( float ) dt;
////        obj1.transform.position.x += 50 * dt;
//        System.out.println( "FPS: " + ( 1.0f / dt ) );
//
//        for( Image gameImage : this.gameImages ) {
//
//            gameImage.update( ( float ) dt );
//        }
//        this.camera.position.x += 100;
//        this.camera.adjustProjection();
        this.gameImages.get(20).transform.position.x += 50 * dt;
        for( Image gameImage : this.gameImages ) {

            gameImage.update( ( float ) dt );
        }
        this.renderer.render();
    }

    @Override
    public void update(){

        ThingList gameTiles = gameRoom.getTiles();

    }
}
