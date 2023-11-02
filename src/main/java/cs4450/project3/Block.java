/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
