/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs4450.project3;

//class dependencies
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.Sys;

public class CameraController {
    //3D vector for storing the camera's position
    private CameraPosition position = null;
    private CameraPosition IPosition = null;
    
    //the rotation around the Y axis of the camera
    private float yaw = 0.0f;
    
    //the rotation around the X axis of the camera
    private float pitch = 0.0f;
    
    private CameraPosition me;
    
    //==========PRIVATE CLASSES============
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
    //=====================================
    
    
    //===========PUBLIC CLASSES============
    //Constructor
    public CameraController(float x, float y, float z){
        position = new CameraPosition(x, y, z);
        IPosition = new CameraPosition(x, y, z);
        IPosition.x = 0f;
        IPosition.y = 15f;
        IPosition.z = 0f;
    }
    
    //increment the camera's current left right rotation
    public void yaw(float amount){
        yaw += amount;
    }
    
    //increment the camera's current up down rotation
    public void pitch(float amount){
        pitch -= amount;
    }
    
    //moves the camera forward relative to its current rotation (yaw)
    public void forward(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    //moves the camera backward relative to its current rotation (yaw)
    public void backward(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
    }
    
    //strafes the camera left relative to its current rotation (yaw)
    public void left(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw - 90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw - 90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    //strafes the camera right relative to its current rotation (yaw)
    public void right(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw + 90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw + 90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    //moves the camera up relative to its current rotation (yaw)
    public void up(float distance){
        position.y -= distance;
    }
    
    //moves the camera down
    public void down(float distance){
        position.y += distance;
    }
    
    //translates and rotate the matrix so that it looks through the camera
    //this does basically what gluLookAt() does
    public void lookThrough(){
        //rotate the pitch around the X axis
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        //rotate the yaw around the Y axis
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        //translate to the position vector's location
        glTranslatef(position.x, position.y, position.z);
    }
    
    public void gameLoop(){
        CameraController camera = new CameraController(0, 0, 0);
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
    //=====================================
}
