/** ***************************************************************
 * File: Block.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - Checkpoint 2
 * Date last modified: 11/07/2023
 *
 * Purpose: This is a user defined Block class
 *
 **************************************************************** */
package cs4450.project3;

public class Block {
    private boolean isActive;
    private float x, y, z;
    private BlockType type;
    
    public Block(BlockType type){
        this.type = type;
    }
    
    public void setCoords(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void setActive(boolean isActive){
        this.isActive = isActive;
    }
    
    public boolean ifActive(){
        return this.isActive;
    }
    
    public BlockType getType(){
        return type;
    }
    
    public int getID(){
        return type.getID();
    }
}
