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
    //=====================================
}
