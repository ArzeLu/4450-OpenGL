/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

// Made by Arze, Harshitha, Rucha
package cs4450.project3;

//class dependencies
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

//my own imports

public class Project3 {   
    final private CameraController camera = new CameraController(0f, 0f, 0f);
    private DisplayMode displayMode;
    
    private void render(){
        try{
            glBegin(GL_QUADS);
            
            glScalef(5.0f, 5.0f, 1.0f);
            
            //Top
            glColor4f(0.0f,1.0f,0.0f, 1.0f);
            glVertex3f( 1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f( 1.0f, 1.0f, 1.0f);
            
            //Bottom
            glColor4f(0.0f,0.0f,1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f( 1.0f,-1.0f,-1.0f);
            
            //Front
            glColor4f(1.0f,0.0f,0.0f, 1.0f);
            glVertex3f( 1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f, 1.0f);
            
            //Back
            glColor4f(1.0f,0.0f,1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f( 1.0f, 1.0f,-1.0f);
            
            //Left
            glColor4f(0.0f,1.0f,1.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            
            //Right
            glColor4f(1.0f,1.0f,0.0f, 1.0f);
            glVertex3f( 1.0f, 1.0f,-1.0f);
            glVertex3f( 1.0f, 1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f,-1.0f);

            glEnd();
            
            glColor3f(0.0f,0.0f,0.0f); //line color
            
            glBegin(GL_LINE_LOOP);
            //Top
            glVertex3f( 1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f( 1.0f, 1.0f, 1.0f);
            glEnd();
            
            glBegin(GL_LINE_LOOP);
            
            //Bottom
            glVertex3f( 1.0f,-1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f( 1.0f,-1.0f,-1.0f);
            glEnd();
            
            glBegin(GL_LINE_LOOP);
            //Front
            glVertex3f( 1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f, 1.0f);
            glEnd();
            
            glBegin(GL_LINE_LOOP);
            //Back
            glVertex3f( 1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f( 1.0f, 1.0f,-1.0f);
            glEnd();
            
            glBegin(GL_LINE_LOOP);
            //Left
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f,-1.0f);
            glVertex3f(-1.0f,-1.0f,-1.0f);
            glVertex3f(-1.0f,-1.0f, 1.0f);
            glEnd();
            
            glBegin(GL_LINE_LOOP);
            //Right
            glVertex3f( 1.0f, 1.0f,-1.0f);
            glVertex3f( 1.0f, 1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f, 1.0f);
            glVertex3f( 1.0f,-1.0f,-1.0f);
            glEnd();
        }catch(Exception e){
            //handles le eggcceppccioonn
            System.out.print(e);
        } 
    }
    
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
            
            render();
            
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    //Initiate GL process
    private void initGL(){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        glEnable(GL_DEPTH_TEST);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth() / (float)displayMode.getHeight(), 01f, 300.0f);
        
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    
    //Create and set a graphics window
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
    
    public void start(){
        try{
            createWindow();
            initGL();
            gameLoop(); //rendering
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public static void main(String[] args) {
        Project3 proj = new Project3();
        proj.start();
    }
}
