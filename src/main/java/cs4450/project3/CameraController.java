/** ***************************************************************
 * File: CameraController.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - Checkpoint 2
 * Date last modified: 11/07/2023
 *
 * Purpose: This program defines camera controls
 *
 **************************************************************** */
package cs4450.project3;

//class dependencies
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import java.lang.Math;
import static org.lwjgl.opengl.GL11.*;

public class CameraController {
    //3D vector for storing the camera's position
    private CameraPosition position = null;
    private CameraPosition IPosition = null;
    private boolean isAirborne = false;
    private int maxJump = 20;
    private int jumpProgress = 0;
    
    //the rotation around the Y axis of the camera
    private float yaw = 0.0f;
    
    //the rotation around the X axis of the camera
    private float pitch = 0.0f;
    
    private CameraPosition me;
    FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
    //===========PUBLIC CLASSES============
    //Constructor
    public CameraController(float x, float y, float z){
        position = new CameraPosition(x, y, z);
        IPosition = new CameraPosition(x, y, z);
        IPosition.x = 100f;
        IPosition.y = 15f;
        IPosition.z = 100f;
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
    public void forward(float distance, int[][] heightMap){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        
        //Collision logic:
        //Precalculates x and z future coordinates,
        //if they go through terrain, 
        //then teleport camera to the wall of the offending x or z.
        float futureX = position.x - xOffset;
        float futureZ = position.z + zOffset;
        int futureGridX = (int) Math.floor(futureX / 2 * -1);
        int futureGridZ = (int) Math.floor(futureZ / 2 * -1);
        int gridX = (int) Math.floor(position.x / 2 * -1);
        int gridZ = (int) Math.floor(position.z / 2 * -1);
        int gridY = (int) Math.floor(position.y / 2 * -1);
        if (heightMap[futureGridX][gridZ] >= gridY){
            if(xOffset < 0){
                position.x = (float) Math.floor(futureX);
            }else{
                position.x = (float) Math.ceil(futureX);
            }
            position.z = futureZ;
        }else if (heightMap[gridX][futureGridZ] >= gridY){
            if(zOffset >= 0){
                position.z = (float) Math.floor(futureZ);
            }else{
                position.z = (float) Math.ceil(futureZ);
            }
            position.x = futureX;
        }else{
            position.x = futureX;
            position.z = futureZ;
        }
        
//        lightPosition.put(IPosition.x-=xOffset).put(IPosition.y).put(IPosition.z+=zOffset).put(1.0f).flip();
//        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

    }
    
    //moves the camera backward relative to its current rotation (yaw)
    public void backward(float distance, int[][] heightMap){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
        
//        lightPosition.put(IPosition.x-=xOffset).put(IPosition.y).put(IPosition.z+=zOffset).put(1.0f).flip();
//        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

    }
    
    //strafes the camera left relative to its current rotation (yaw)
    public void left(float distance, int[][] heightMap){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw - 90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw - 90));
        position.x -= xOffset;
        position.z += zOffset;
        
//        lightPosition.put(IPosition.x-=xOffset).put(IPosition.y).put(IPosition.z+=zOffset).put(1.0f).flip();
//        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

    }
    
    //strafes the camera right relative to its current rotation (yaw)
    public void right(float distance, int[][] heightMap){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw + 90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw + 90));
        position.x -= xOffset;
        position.z += zOffset;
        
//        lightPosition.put(IPosition.x-=xOffset).put(IPosition.y).put(IPosition.z+=zOffset).put(1.0f).flip();
//        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

    }
    
    //moves the camera up relative to its current rotation (yaw)
    public void up(float distance){
        position.y -= distance;
    }
    
    //mark jumpProgress to start the jumping sequence
    public void jump(float distance){
        if(jumpProgress == -1){
            jumpProgress = 0;
        }
    }
    
    //moves the camera down
    public void down(float distance, int[][] heightMap){
        position.y += distance;
    }
    
    public void topView(float chunkSize){
        position.x = -chunkSize;
        position.y = -chunkSize;
        position.z = -chunkSize;
        pitch = 90f;
    }
    
    //if currently jumping, then edit y position and return true
    //else reset jumping sequence and return false
    public boolean checkJumping(float distance, int heightMap[][]){
        if(jumpProgress >= 0 && jumpProgress <= maxJump){
            position.y -= distance;
            jumpProgress++;
            return true;
        }
        jumpProgress = -1;
        return false;
    }
    
    //If camera is in the air (not touching the ground), 
    //then edit y position move camera downwards.
    //Disabled if camera is flying mode or in the process of jumping.
    public boolean checkAirborne(boolean flightMode, float distance, int heightMap[][]){
        if(!flightMode){
            int gridX = (int) Math.floor(position.x / 2 * -1);
            int gridZ = (int) Math.floor(position.z / 2 * -1);
            int gridY = (int) Math.floor(position.y / 2 * -1);
            int groundHeight = heightMap[gridX][gridZ];

            if(groundHeight < gridY - 1 && jumpProgress == -1){
                position.y += distance;
                isAirborne = true;
                return true;
            }
        }
        isAirborne = false;
        return false;
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
        
      
        lightPosition.put(IPosition.x).put(IPosition.y).put(IPosition.z).put(1.0f).rewind();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

    }
    //=====================================
}
