/** ***************************************************************
 * File: Project3.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - Final Checkpoint
 * Date last modified: 12/05/2023
 *
 * Purpose: This program starts the rendering process and gameloop
 *
 **************************************************************** */
package cs4450.project3;

//class dependencies
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class Project3 {

    final private CameraController camera = new CameraController(-100f, -50f, -100f); //Set the camera above chunk terrain
    final private TextureController texture = new TextureController();
    private Chunk chunk;
    private DisplayMode displayMode;
    final private static float OFFSET = (1024f / 16) / 1024f;
    private FloatBuffer lightPosition;
    private FloatBuffer whiteLight;

    // method: render
    // purpose: starts the chunk's rendering loop
    private void render() {
        chunk.render();
    }

    // method: gameLoop
    // purpose: defines movements of the mouse
    // calls: glLoadIdentity(), texture.loadTexture(), camera.yaw(),
    //        camera.pitch(), camera.checkJumping, camera.checkAirborne(),
    //        glLoadIdentity(), render() and few camera methods.
    public void gameLoop() {
        float dx = 0.0f;
        float dy = 0.0f;
        float dt = 0.0f; //length of frame
        float lastTime = 0.0f; //when the last frame was
        long time = 0;
        float mouseSensitivity = 0.09f;
        float movementSpeed = 0.35f;
        float sprintMultiplier = 1.5f;
        boolean isAirborne;
        boolean started = false;
        boolean flightMode = false;
        boolean e_toggled = false;

        //hide the mouse
        Mouse.setGrabbed(true);

        chunk = new Chunk(0, 0, 0);
        texture.loadTexture();

        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            time = Sys.getTime();
            lastTime = time;

            //distance in mouse movement from the last getDX() call
            dx = Mouse.getDX();

            //distance in mouse movement from the last getDY() call
            dy = Mouse.getDY();

            //control camera yaw from x movement from the mouse
            camera.yaw(dx * mouseSensitivity);

            //control camera pitch from y movement from the mouse
            camera.pitch(dy * mouseSensitivity);

            //Jumping and airborne logics.
            //If the camera is airborne, then grab it back down to surface.
            //Jumping is just the opposite of airborne in a sense.
            //If one of them is active then the other one is deactivated.
            isAirborne = camera.checkAirborne(flightMode, movementSpeed, chunk.getHeightMap());
            camera.checkJumping(movementSpeed, chunk.getHeightMap());

            //togglers. Prevent variable change per loop tick
            if (!e_toggled && Keyboard.isKeyDown(Keyboard.KEY_E)) {
                flightMode = !flightMode;
            }
            e_toggled = Keyboard.isKeyDown(Keyboard.KEY_E);

/*****
            The light position changes every 10 seconds creating a sunset feature.

            Use the following keys to activate/move the camera positions.

            C – to see winter season
            R – for summer season
            E – to switch between normal mode and gravity mode
            T – to see the top view
            W,S,A,D & UP,DOWN,LEFT,RIGHT keys to move front,back,left,right

            In normal mode:
            Left Shift – to move into/closer
            Space bar – to move up/away

            In gravity mode:
            Space bar – to jump 
            
*********/
            //when passing in the distance to move,
            //we multiply the movementSpeed with dt (a time scale),
            //so if it's a slow frame you move more than a fast frame,
            //so on a slow computer you move just as fast as on a fast computer.
            if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                    camera.forward(movementSpeed * sprintMultiplier, chunk.getHeightMap());
                }
                camera.forward(movementSpeed, chunk.getHeightMap());
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                camera.backward(movementSpeed, chunk.getHeightMap());
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                camera.left(movementSpeed, chunk.getHeightMap());
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                camera.right(movementSpeed, chunk.getHeightMap());
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                if (flightMode) {
                    camera.up(movementSpeed);
                } else if (!isAirborne) {
                    camera.jump(movementSpeed);
                }
            }
            if (!isAirborne && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                if (flightMode) {
                    camera.down(movementSpeed, chunk.getHeightMap());
                }
            }
            // moves camera view to the top
            if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
                camera.topView((chunk.CHUNK_SIZE));
            }
            // on press of key C changes to Winter Season 
            if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                chunk.rebuildWinterMesh(0, 0, 0);
            }
            // on press of key R changes to Summer Season 
            if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
                chunk.rebuildMesh(0, 0, 0);
            }

            //set the modelview matrix back to the identity
            glLoadIdentity();

            //look through the camera before you draw anything
            camera.lookThrough();

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            texture.bindTexture();

            render();

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    // method: initLightArrays
    // purpose: sets initial light positions
    private void initLightArrays() {
        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(0.0f).put(0.0f).put(0.0f).put(1.0f).flip();

        whiteLight = BufferUtils.createFloatBuffer(4);
        whiteLight.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();
    }

    // methos: initGL
    // purpose: Initiates GL process
    // calls: initLightArrays()
    private void initGL() {
        glClearColor(0.47f, 0.65f, 1.0f, 0.0f);

        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        GLU.gluPerspective(100.0f, (float) displayMode.getWidth() / (float) displayMode.getHeight(), 01f, 300.0f);

        initLightArrays();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition); //sets our light’s position
        glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);//sets our specular light
        glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);//sets our diffuse light
        glLight(GL_LIGHT0, GL_AMBIENT, whiteLight);//sets our ambient light
        glEnable(GL_LIGHTING);//enables our lighting
        glEnable(GL_LIGHT0);//enables light0

        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }

    // method: createWindow
    // purpose: Create and set a graphics window
    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        DisplayMode d[] = Display.getAvailableDisplayModes();

        for (DisplayMode element : d) {
            if (element.getWidth() == 640 && element.getHeight() == 480 && element.getBitsPerPixel() == 32) {
                displayMode = element;
                break;
            }
        }

        Display.setDisplayMode(displayMode);
        Display.setTitle("using le opengl desu");
        Display.create();
    }

    // method: start
    // purpose: creates & initializes the openGL and starts the loop
    // Calls createWindow(), initGL(), gameLoop()
    public void start() {
        try {
            createWindow();
            initGL();
            gameLoop(); //rendering
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    // method: main
    // purpose: It is the first method that gets called automatically
    // when the program is executed.
    // Calls start() method
    public static void main(String[] args) {
        Project3 proj = new Project3();
        proj.start();
    }
}
