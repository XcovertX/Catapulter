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
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImString;
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

import java.io.*;

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

    private ImString textInput = new ImString();
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

        sprites = AssetPool.getSpriteSheet( "assets/images/walls.png" );
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
        this.removeFlaggedComponents();
    }

    @Override
    public void update(){ }

    public void imgui() {

//        ImGui.setCursorPos(700, 0);
//        ImGui.begin("test");
        ImGui.text("Input");

        ImGui.inputText( "string", textInput, ImGuiInputTextFlags.CallbackResize | ImGuiInputTextFlags.AutoSelectAll );
        if(ImGui.button("Enter", 100, 50)){
            inputText();
        }
        if( ImGui.isKeyPressed( GLFW_KEY_ENTER ) ){
            if( textInput != null ) {
                inputText();
            }
        }
        ImGui.text( "result: " + textInput.get() );
        ImGui.sliderFloat("float", new float[10],0.0f,100.0f);

//        ImGui.end();
    }

    public InputStream convertToInputStream( String text ) throws IOException {

        return new ByteArrayInputStream( text.getBytes() );
    }
    private void inputText() {

        try {

            Game.in = new BufferedReader( new InputStreamReader( convertToInputStream( textInput.get() ) ) );

        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    private void removeFlaggedComponents() {
        for( Image gameImage : this.gameImages ) {

            for( int i = 0; i < gameImage.getAllComponents().size(); i++ ){

                Component c = gameImage.getAllComponents().get( i );
                if( c.isFlaggedForRemoval() ){
                    gameImage.getAllComponents().remove( i );
                    i -= 1;
                }
            }
        }
    }
}
