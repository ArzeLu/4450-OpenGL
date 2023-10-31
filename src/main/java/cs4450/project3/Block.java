/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs4450.project3;

public class Block {
    private boolean isActive;
    private BlockType type;
    private float x, y, z;
    
    public enum BlockType{
        Grass(0),
        Sand(1),
        Water(2),
        Dirt(3),
        Stone(4),
        Bedrock(5);
        
        private int blockID;
        
        BlockType(int id){
            blockID = id;
        }
        
        public int getID(){
            return blockID;
        }
        
        public void setID(int id){
            blockID = id;
        }
    }
    
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
    
    public int getID(){
        return type.getID();
    }
}
