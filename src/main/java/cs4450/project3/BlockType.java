/** ***************************************************************
 * File: BlockType.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - Checkpoint 2
 * Date last modified: 11/07/2023
 *
 * Purpose: This is a user defined class with Block Types
 *
 **************************************************************** */
package cs4450.project3;

public enum BlockType{
    Grass(0),
    Sand(1),
    Water(2),
    Dirt(3),
    Stone(4),
    Bedrock(5),
    Snow(6),
    SnowSand(7);

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