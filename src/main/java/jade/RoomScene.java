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

        sprites = AssetPool.getSpriteSheet( "assets/images/center-sheet.png" );
        ThingList gameTiles = gameRoom.getTiles();

        for( gameObjects.Thing gameTile : gameTiles ) {

            GameTile gt = ( GameTile ) gameTile;
            Image baseImage = gt.getBaseTileImage();
//            Image currentThingImage = gt.getCurrentThingImage();
            System.out.println( "tilenum: " + gt.getTileNumber() + " " +
                                "tileposX: " + gt.getBaseTileImage().transform.position.x + " " +
                                "tileposY: " + gt.getBaseTileImage().transform.position.y + " " +
                                "tilesetpos " + gt.getBaseTileImage().getTilesetPosition() );

            baseImage.addComponent( new SpriteRenderer( sprites.getSprite( baseImage.getTilesetPosition() ) ) );
            this.addGameImageToScene( baseImage );
            if( !gt.getThings().isEmpty() ) {
                System.out.println( "torch: " + gt.getTileNumber());
                torch = AssetPool.getSpriteSheet( "assets/images/torch_tileset.png" );
                Image image = new Image( "torch", new Transform( baseImage.transform.position,
                        baseImage.transform.scale ),0 );
                image.addComponent( new SpriteRenderer( torch.getSprite( 1 ) ) );
                this.addGameImageToScene( image );
            }


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

        AssetPool.addSpriteSheet( "assets/images/torch_tileset.png",
                new SpriteSheet( AssetPool.getTexture( "assets/images/torch_tileset.png" ),
                        32, 32, 4, 0 ) );

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
//        this.gameImages.get(10).transform.position.x += 1.01;

//        this.gameImages.get(10).update( ( float) dt );

        for( Image gameImage : this.gameImages ) {
//            gameImage.transform.position.x *= 1.01 ;
//            gameImage.transform.position.y *= 1.01;
//            gameImage.transform.scale.x *= 1.01;
//            gameImage.transform.scale.y *= 1.01;
            gameImage.update( ( float ) dt );
        }
        this.renderer.render();
    }

    @Override
    public void update(){

        ThingList gameTiles = gameRoom.getTiles();

    }
}
