package jade;

import renderer.Renderer;
import userInterface.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;

    protected List< Image > gameImages = new ArrayList<>();

    public Scene() { }

    public void init() { }

    public void start() {

        for( Image i : gameImages ) {

            i.start();
            this.renderer.add( i );
        }
        isRunning = true;
    }

    public void addGameImageToScene( Image gameImage ) {

        if( !isRunning ) {

            gameImages.add( gameImage );

        } else {

            gameImages.add( gameImage );
            gameImage.start();
            this.renderer.add( gameImage );
        }
    }

    public abstract void update( double dt );

    public abstract void update();

    public Camera camera() {
        return this.camera;
    }
}
