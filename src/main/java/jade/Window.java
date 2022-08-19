package jade;

import game.Game;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import start.Catapulter;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
//    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
//    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
//
//    private String glslVersion = null;

    private int width, height;
    private String title;
    private long glfwWindow;

    public float r, g, b, a;

    private static Window window = null;

    private static Scene currentScene;

    private ImGuiLayer imGuiLayer;

    private Window() {

        this.width = 500;
        this.height = 500;
        this.title = "Game";
        r = 0.8f;
        g = 0.8f;
        b = 0.2f;
        a = 1;
    }

    public static void changeScene( int newScene ) {
        switch( newScene ) {
            case 0:
                currentScene = new RoomScene();
                currentScene.init();
                currentScene.start();
                break;
            case 1:
                currentScene = new LevelScene();
                currentScene.init();
                currentScene.start();
                break;
            case 2:
                currentScene = Game.currentRoom.getScene();
                ( ( RoomScene ) currentScene ).init( Game.currentRoom );
                currentScene.start();
                break;
            default:
                assert false : "Unknown scene: '" + newScene + "'";
                break;
        }
    }

    public static Window get() {

        if( Window.window == null ) {

            Window.window = new Window();
        }
        return Window.window;
    }

    public void run() {

        System.out.println( "Hello LWJGL " + Version.getVersion() + "!" );
        init();
    }

    private void init() {

        initWindow();
    }

    public void loop() {
        double beginTime = glfwGetTime();

        double endTime;
        double dt = -1.0f;
        while(!glfwWindowShouldClose(glfwWindow)) {
            // poll events
            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if(dt >= 0) {
                currentScene.update(dt);
            }

            this.imGuiLayer.update((float) dt);

            glfwSwapBuffers(glfwWindow);

            endTime = glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

    public void update( double dt ) {

        if( glfwWindowShouldClose( glfwWindow ) ) {

            exit();
        }

        // poll events
        glfwPollEvents();

        glClearColor(r, g, b, a);
        glClear(GL_COLOR_BUFFER_BIT);

        if( dt >= 0 ) {
            currentScene.update( dt );
        }

        this.imGuiLayer.update( ( float ) dt);

        glfwSwapBuffers( glfwWindow );
    }

    public void exit() {
        // Free the memory
        glfwFreeCallbacks( glfwWindow );
        glfwDestroyWindow( glfwWindow );

        // Terminate GLFW and free error callback memory
        glfwTerminate();
        glfwSetErrorCallback( null ).free();
        System.exit( 0 );
    }

    public static Scene getScene() {
        return get().currentScene;
    }

//    public void destroy() {
//        imGuiGl3.dispose();
//        imGuiGlfw.dispose();
//        ImGui.destroyContext();
//        Callbacks.glfwFreeCallbacks(glfwWindow);
//        glfwDestroyWindow(glfwWindow);
//        glfwTerminate();
//    }

    private void initWindow() {
        // setup error callback
        GLFWErrorCallback.createPrint( System.err ).set();

        // initialize GLFW
        if( !glfwInit() ) {

            throw new IllegalStateException( "Unable to initialize GLFW." );
        }
        // Config GLFW
        glfwDefaultWindowHints();

        glfwWindowHint( GLFW_CONTEXT_VERSION_MAJOR, 3 );
        glfwWindowHint( GLFW_CONTEXT_VERSION_MINOR, 3 );

        glfwWindowHint( GLFW_VISIBLE, GLFW_FALSE );
        glfwWindowHint( GLFW_RESIZABLE, GLFW_TRUE );
        glfwWindowHint( GLFW_MAXIMIZED, GLFW_TRUE );


        // Create the window
        glfwWindow = glfwCreateWindow( this.width, this.height,this.title, NULL, NULL );
        if( glfwWindow == NULL ) {
            throw new IllegalStateException( "Failed to create the GLFW window." );
        }

//        glfwSetWindowSize( glfwWindow, width, height );
        // Set cursor callbacks
        glfwSetCursorPosCallback( glfwWindow, MouseListener::mousePosCallback );
        glfwSetMouseButtonCallback( glfwWindow, MouseListener::mouseButtonCallback );
        glfwSetScrollCallback( glfwWindow, MouseListener::mouseScrollCallback );
        glfwSetWindowSizeCallback( glfwWindow, ( w, newWidth, newHeight ) -> {
            Window.setWidth( newWidth );
            Window.setHeight( newHeight );
        });
        // Set key callbacks
        glfwSetKeyCallback( glfwWindow, KeyListener::keyCallback );

        // Make openGL context current
        glfwMakeContextCurrent( glfwWindow );
        // Enable v-sync
        glfwSwapInterval( 1 );

        // Make window visible
        glfwShowWindow( glfwWindow );

        GL.createCapabilities();

        glEnable( GL_BLEND );
        glBlendFunc( GL_ONE, GL_ONE_MINUS_SRC_ALPHA );
        this.imGuiLayer = new ImGuiLayer( glfwWindow );
        this.imGuiLayer.initImGui();

        Window.changeScene( 2 );
    }

    public static int getWidth() {
        return get().width;
    }

    public static int getHeight() {
        return get().height;
    }

    public static void setWidth(int newWidth) {
        get().width = newWidth;
    }

    public static void setHeight(int newHeight) {
        get().height = newHeight;
    }
}