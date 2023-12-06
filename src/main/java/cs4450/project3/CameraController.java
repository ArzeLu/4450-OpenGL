/** ***************************************************************
 * File: CameraController.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - final checkpoint
 * Date last modified: 12/05/2023
 *
 * Purpose: This program defines camera controls
 *
 **************************************************************** */
package cs4450.project3;

//class dependencies
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import java.lang.Math;
import org.lwjgl.Sys;
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
    
    //Constructor
    public CameraController(float x, float y, float z) {
        position = new CameraPosition(x, y, z);
        IPosition = new CameraPosition(x, y, z);
        IPosition.x = 100f;
        IPosition.y = 15f;
        IPosition.z = 210f;
    }

    // method: yaw
    // purpose: increment the camera's current left right rotation
    public void yaw(float amount) {
        yaw += amount;
    }

    // method: pitch
    // purpose: increment the camera's current up down rotation
    public void pitch(float amount) {
        pitch -= amount;
    }

    // method: forward
    // purpose: moves the camera forward relative to its current rotation (yaw)
    public void forward(float distance, int[][] heightMap) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw));

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
        if (heightMap[futureGridX][gridZ] >= gridY) {
            if (xOffset < 0) {
                position.x = (float) Math.floor(futureX);
            } else {
                position.x = (float) Math.ceil(futureX);
            }
            position.z = futureZ;
        } else if (heightMap[gridX][futureGridZ] >= gridY) {
            if (zOffset >= 0) {
                position.z = (float) Math.floor(futureZ);
            } else {
                position.z = (float) Math.ceil(futureZ);
            }
            position.x = futureX;
        } else {
            position.x = futureX;
            position.z = futureZ;
        }
    }

    // method: backward
    // purpose: moves the camera backward relative to its current rotation (yaw)
    public void backward(float distance, int[][] heightMap) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
    }

    // method: left
    // purpose: strafes the camera left relative to its current rotation (yaw)
    public void left(float distance, int[][] heightMap) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw - 90));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw - 90));
        position.x -= xOffset;
        position.z += zOffset;
    }

    // method: right
    // purpose: strafes the camera right relative to its current rotation (yaw)
    public void right(float distance, int[][] heightMap) {
        float xOffset = distance * (float) Math.sin(Math.toRadians(yaw + 90));
        float zOffset = distance * (float) Math.cos(Math.toRadians(yaw + 90));
        position.x -= xOffset;
        position.z += zOffset;
    }

    // method: up
    // purpose: moves the camera up relative to its current rotation (yaw)
    public void up(float distance) {
        position.y -= distance;
    }

    // method: jump
    // purpose: mark jumpProgress to start the jumping sequence
    public void jump(float distance) {
        if (jumpProgress == -1) {
            jumpProgress = 0;
        }
    }

    // method: down
    // purpose: moves the camera down
    public void down(float distance, int[][] heightMap) {
        position.y += distance;
    }

    // method: down
    // purpose: moves to the top
    public void topView(float chunkSize) {
        position.x = -chunkSize;
        position.y = -chunkSize;
        position.z = -chunkSize;
        pitch = 90f;
    }

    //method: checkJumping
    //purpose: if currently jumping, then edit y position and return true
    //          else reset jumping sequence and return false
    public boolean checkJumping(float distance, int heightMap[][]) {
        if (jumpProgress >= 0 && jumpProgress <= maxJump) {
            position.y -= distance;
            jumpProgress++;
            return true;
        }
        jumpProgress = -1;
        return false;
    }

    // method: checkAirborne
    //purpose: If camera is in the air (not touching the ground), 
    //          then edit y position move camera downwards.
    //          Disabled if camera is flying mode or in the process of jumping.
    public boolean checkAirborne(boolean flightMode, float distance, int heightMap[][]) {
        if (!flightMode) {
            int gridX = (int) Math.floor(position.x / 2 * -1);
            int gridZ = (int) Math.floor(position.z / 2 * -1);
            int gridY = (int) Math.floor(position.y / 2 * -1);
            int groundHeight = heightMap[gridX][gridZ];

            if (groundHeight < gridY - 1 && jumpProgress == -1) {
                position.y += distance;
                isAirborne = true;
                return true;
            }
        }
        isAirborne = false;
        return false;
    }

    // method: lookthrough
    // purpose: translates and rotate the matrix so that it looks through the camera
    //          this does basically what gluLookAt() does
    public void lookThrough() {
        //rotate the pitch around the X axis
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        //rotate the yaw around the Y axis
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        //translate to the position vector's location
        glTranslatef(position.x, position.y, position.z);

        callDayNightLight();
    }

    // method: getTime
    // purpose: gets the current time in milliseconds
    public long getTime() {
        // getTime() - Gets the current value of the hires timer, in ticks.
        // getTimerResolution() - Obtains the number of ticks that the hires timer does in a second.
        // If the number we get from Sys.getTime() is already in milliseconds, 
        // it doesn't require *1000/Sys.getTimerResolution()...
        // but when you are offered from a lib such methods use them, 
        // because you don’t have a guarantee that you will always get 1000 from Sys.getTimerResolution(), 
        // maybe you get on another PC another value. Or they change it with a newer version of the libary.
        // Your programm should never depend on internals of a lib, 
        // always depend on the contracts the libary is offering you.
        // hence, Sys.getTime() * 1000 / Sys.getTimerResolution()
        // Extra calcultions are done to fetch the seconds.
        return ((Sys.getTime() * 1000 / (Sys.getTimerResolution() * 10000))) % 10;
    }

    // method: callDayNightLight
    // purpose: Changes the light position every 10 seconds. Gives a sunset kind of effect.
    public void callDayNightLight() {
        if (getTime() % 10 == 0 || getTime() % 10 == 5) {
            lightPosition.put(IPosition.x).put(IPosition.y).put(IPosition.z).put(1.0f).rewind();
            glLight(GL_LIGHT0, GL_POSITION, lightPosition);
        } else if (getTime() % 10 == 1 || getTime() % 10 == 6) {
            lightPosition.put(IPosition.x).put(IPosition.y).put((IPosition.z * 3) / 4).put(1.0f).rewind();
            glLight(GL_LIGHT0, GL_POSITION, lightPosition);
        } else if (getTime() % 10 == 2 || getTime() % 10 == 7) {
            lightPosition.put(IPosition.x).put(IPosition.y).put((IPosition.z * 2) / 4).put(1.0f).rewind();
            glLight(GL_LIGHT0, GL_POSITION, lightPosition);
        } else if (getTime() % 10 == 3 || getTime() % 10 == 8) {
            lightPosition.put(IPosition.x).put(IPosition.y).put((IPosition.z * 1) / 4).put(1.0f).rewind();
            glLight(GL_LIGHT0, GL_POSITION, lightPosition);
        } else if (getTime() % 10 == 4 || getTime() % 10 == 9) {
            lightPosition.put(0).put(0).put(0).put(1.0f).rewind();
            glLight(GL_LIGHT0, GL_POSITION, lightPosition);
        }
    }
}
