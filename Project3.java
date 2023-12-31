/** ***************************************************************
 * File: Project3.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - Checkpoint 2
 * Date last modified: 11/07/2023
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

public class Project3 {   
    final private CameraController camera = new CameraController(0f, 0f, 0f);
    final private TextureController texture = new TextureController();
    private Chunk chunk;
    private DisplayMode displayMode;
    final private static float OFFSET = (1024f / 16) / 1024f;
    
    // method: render
    // purpose: starts the chunk's rendering loop
    private void render(){
        chunk.render();
    }
    
    // method: gameLoop
    // purpose: defines movements of the mouse
    public void gameLoop(){
        float dx = 0.0f;
        float dy = 0.0f;
        float dt = 0.0f; //length of frame
        float lastTime = 0.0f; //when the last frame was
        long time = 0;
        float mouseSensitivity = 0.09f;
        float movementSpeed = 0.35f;
        //hide the mouse
        Mouse.setGrabbed(true);
        
        chunk = new Chunk(0, 0, 0);
        texture.loadTexture();
        
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
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
            
            //when passing in the distance to move,
            //we multiply the movementSpeed with dt (a time scale),
            //so if it's a slow frame you move more than a fast frame,
            //so on a slow computer you move just as fast as on a fast computer.
            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                camera.forward(movementSpeed);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                camera.backward(movementSpeed);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A)){
                camera.left(movementSpeed);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                camera.right(movementSpeed);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
                camera.up(movementSpeed);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                camera.down(movementSpeed);
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

    
    // method: createWindow
    // purpose: Initiate GL process
    private void initGL(){
        glClearColor(0.47f, 0.65f, 1.0f, 0.0f);
        
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth() / (float)displayMode.getHeight(), 01f, 300.0f);
        
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    // method: createWindow
    // purpose: Create and set a graphics window
    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        
        for(DisplayMode element : d){
            if(element.getWidth() == 640 && element.getHeight() == 480 && element.getBitsPerPixel() == 32){
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
    public void start(){
        try{
            createWindow();
            initGL();
            gameLoop(); //rendering
        }catch(Exception e){
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
