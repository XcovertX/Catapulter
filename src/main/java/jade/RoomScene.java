package jade;

import actor.Actor;
import components.*;
import game.Game;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import globals.Direction;
import imgui.ImGui;
//import imgui.enums.ImGuiKey;
import imgui.ImVec2;
import org.joml.Vector2f;
import userInterface.Image;
import userInterface.TMX;
import userInterface.TMXParser;
import userInterface.TSX;
import util.AssetPool;
import verbs.Go;
import world.GameRoom;
import world.GameTile;
import world.UpdateWorldMethods;

import static org.lwjgl.glfw.GLFW.*;

public class RoomScene extends Scene {

//    private Image[] tileImages;
//    private Image[] thingImages;
//    private SpriteSheet[] spriteSheets;
    private Image gameImage;
    private SpriteSheet sprites;
    private TileSheet tiles;
    private SpriteSheet torch;
    Image levelEditorStuff = new Image("LevelEditor", new Transform(new Vector2f()), 0);

    private GameRoom gameRoom;

    public RoomScene() { }

    public RoomScene( GameRoom gameRoom ) { this.gameRoom = gameRoom; }

    @Override
    public void init() {

        levelEditorStuff.addComponent( new MouseControls() );
        levelEditorStuff.addComponent( new GridLines() );

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

            AssetPool.addSpriteSheet( baseImage.getImageResourcePath(),
                    new SpriteSheet( AssetPool.getTexture( baseImage.getImageResourcePath() ),
                            baseImage.getImageWidth(),
                            baseImage.getImageHeight(),
                            baseImage.getImageFrameCount(),
                            0 ) );

            SpriteSheet spriteSheet = AssetPool.getSpriteSheet( baseImage.getImageResourcePath() );
            baseImage.setSpriteSheet( spriteSheet );
            baseImage.addComponent( new SpriteRenderer( spriteSheet.getSprite( baseImage.getActiveTilesetPosition() ) ) );
            this.addGameImageToScene( baseImage );
        }

        for( gameObjects.Thing gameTile : gameTiles ) {

            GameTile gt = ( GameTile ) gameTile;
            Image baseImage = gt.getBaseTileImage();

            gt.initAllImages( baseImage );

            Image currentThingImage = gt.getCurrentThingImage();
            if( currentThingImage != null ) {

                this.addGameImageToScene( currentThingImage );
            }

        }
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
//            gameImage.transform.position.x *= 1.01 ;
//            gameImage.transform.position.y *= 1.01;
//            gameImage.transform.scale.x *= 1.01;
//            gameImage.transform.scale.y *= 1.01;


        if ( KeyListener.isKeyPressed( GLFW_KEY_RIGHT ) ) {

           new Go().run( "e" );

        } else if ( KeyListener.isKeyPressed( GLFW_KEY_LEFT ) ) {

            new Go().run( "w" );
        }
        if ( KeyListener.isKeyPressed( GLFW_KEY_UP ) ) {

            new Go().run( "n" );

        } else if ( KeyListener.isKeyPressed( GLFW_KEY_DOWN ) ) {

            new Go().run( "s" );
        }

        for( Image gameImage : this.gameImages ) {

            gameImage.update( ( float ) dt );
        }

        this.renderer.render();
    }

    @Override
    public void update(){

        ThingList gameTiles = gameRoom.getTiles();

    }

    public void imgui() {
        ImGui.begin("Test Window");

        ImVec2 windowPos = new ImVec2();
        ImGui.getWindowPos(windowPos);
        ImVec2 windowSize = new ImVec2();
        ImGui.getWindowSize(windowSize);
        ImVec2 itemSpacing = new ImVec2();
        ImGui.getStyle().getItemSpacing(itemSpacing);

        float windowX2 = windowPos.x + windowSize.x;

        for( int i = 0; i < gameImages.size(); i++  ) {

            Image image = gameImages.get(i);
            SpriteRenderer sr = image.getComponent( SpriteRenderer.class );
            SpriteSheet ss = image.getSpriteSheet();
            Sprite sprite = ss.getSprite(3);
            float spriteWidth = sprite.getWidth() * 4;
            float spriteHeight = sprite.getHeight() * 4;
            int id = sprite.getTextureId();
            Vector2f[] textureCoords = sprite.getTexCoords();

            ImGui.pushID(i);
            if(ImGui.imageButton(id, spriteWidth, spriteHeight, textureCoords[2].x, textureCoords[0].y, textureCoords[0].x, textureCoords[2].y)) {
//                Image object = Prefabs.generateSpriteObject(sprite, 32, 32);
                // Attach this to the mouse cursor
                levelEditorStuff.getComponent(MouseControls.class).pickupObject(image);
            }
            ImGui.popID();
        }

//        for( int i = 0; i < sprites.size(); i++ ) {
//
//            Sprite sprite = sprites.getSprite(i);
//            float spriteWidth = sprite.getWidth() * 4;
//            float spriteHeight = sprite.getHeight() * 4;
//            int id = sprite.getTextureId();
//            Vector2f[] textureCoords = sprite.getTexCoords();
//
//            ImGui.pushID(i);
//            if(ImGui.imageButton(id, spriteWidth, spriteHeight, textureCoords[2].x, textureCoords[0].y, textureCoords[0].x, textureCoords[2].y)) {
//                Image object = Prefabs.generateSpriteObject(sprite, 32, 32);
//                // Attach this to the mouse cursor
//                levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
//            }
//            ImGui.popID();
//
//            ImVec2 lastButtonPos = new ImVec2();
//            ImGui.getItemRectMax(lastButtonPos);
//            float lastButtonX2 = lastButtonPos.x;
//            float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
//            if(i + 1 < spriteSheet.size() && nextButtonX2 < windowX2) {
//                ImGui.sameLine();
//            }
//        }

        ImGui.end();
    }
}
