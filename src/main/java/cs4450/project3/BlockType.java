/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs4450.project3;

/**
 *
 * @author arze7
 */
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