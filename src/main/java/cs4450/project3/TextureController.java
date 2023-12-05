/** ***************************************************************
 * File: TextureController.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - Checkpoint 2
 * Date last modified: 11/07/2023
 *
 * Purpose: Defines texture for the cubes
 **************************************************************** */
package cs4450.project3;

import static cs4450.project3.BlockType.Snow;
import static cs4450.project3.BlockType.SnowSand;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import java.util.Random;

public class TextureController {

    private static Texture texture;
    private Random r;
    final private static float OFFSET = (1024f / 16) / 1024f;
    final private static int CUBE_LENGTH = 2;

    ///================================
    ///List of texture coordinates here
    final float grassTexCoords[] = new float[]{
        //Top
        OFFSET * 12, OFFSET * 12,
        OFFSET * 13, OFFSET * 12,
        OFFSET * 13, OFFSET * 13,
        OFFSET * 12, OFFSET * 13,
        //Bottom (x, y)
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Front
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Back (this one vertex mapping order is different than other faces)
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        //Left
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Right
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,};
    
    
     final float snowTexCoords[] = new float[]{
        //Top
//        OFFSET * 9, OFFSET * 4,
//        OFFSET * 10, OFFSET * 4,
//        OFFSET * 9, OFFSET * 5,
//        OFFSET * 10, OFFSET * 5,
        OFFSET * 2, OFFSET * 5,
        OFFSET * 3, OFFSET * 5,
        OFFSET * 3, OFFSET * 4,
        OFFSET * 2, OFFSET * 4,
        //Bottom (x, y)
        OFFSET * 2, OFFSET * 5,
        OFFSET * 3, OFFSET * 5,
        OFFSET * 3, OFFSET * 4,
        OFFSET * 2, OFFSET * 4,
        //Front
        OFFSET * 4, OFFSET * 4,
        OFFSET * 5, OFFSET * 4,
        OFFSET * 5, OFFSET * 5,
        OFFSET * 4, OFFSET * 5,
        //Back (this one vertex mapping order is different than other faces)
        OFFSET * 4, OFFSET * 5,
        OFFSET * 5, OFFSET * 5,
        OFFSET * 5, OFFSET * 4,
        OFFSET * 4, OFFSET * 4,
//        OFFSET * 4, OFFSET * 1,
//        OFFSET * 3, OFFSET * 1,
//        OFFSET * 3, OFFSET * 0,
//        OFFSET * 4, OFFSET * 0,
        //Left
        OFFSET * 4, OFFSET * 4,
        OFFSET * 5, OFFSET * 4,
        OFFSET * 5, OFFSET * 5,
        OFFSET * 4, OFFSET * 5,
        //Right
       OFFSET * 4, OFFSET * 4,
        OFFSET * 5, OFFSET * 4,
        OFFSET * 5, OFFSET * 5,
        OFFSET * 4, OFFSET * 5,}; 
     
      final float snowWaterTexCoords[] = new float[]{
        //Top
        OFFSET * 8, OFFSET * 1,
        OFFSET * 9, OFFSET * 1,
        OFFSET * 9, OFFSET * 2,
        OFFSET * 8, OFFSET * 2,
        //Bottom (x, y)
        OFFSET * 8, OFFSET * 1,
        OFFSET * 9, OFFSET * 1,
        OFFSET * 9, OFFSET * 2,
        OFFSET * 8, OFFSET * 2,
        //Front
        OFFSET * 8, OFFSET * 1,
        OFFSET * 9, OFFSET * 1,
        OFFSET * 9, OFFSET * 2,
        OFFSET * 8, OFFSET * 2,
        //Back (this one vertex mapping order is different than other faces)
        OFFSET * 8, OFFSET * 1,
        OFFSET * 9, OFFSET * 1,
        OFFSET * 9, OFFSET * 2,
        OFFSET * 8, OFFSET * 2,
        //Left
        OFFSET * 8, OFFSET * 1,
        OFFSET * 9, OFFSET * 1,
        OFFSET * 9, OFFSET * 2,
        OFFSET * 8, OFFSET * 2,
        //Right
        OFFSET * 8, OFFSET * 1,
        OFFSET * 9, OFFSET * 1,
        OFFSET * 9, OFFSET * 2,
        OFFSET * 8, OFFSET * 2,}; 

    final float sandTexCoords[] = new float[]{
        //Top
        OFFSET * 2, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 2,
        OFFSET * 2, OFFSET * 2,
        //Bottom
        OFFSET * 2, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 2,
        OFFSET * 2, OFFSET * 2,
        //Front
        OFFSET * 2, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 2,
        OFFSET * 2, OFFSET * 2,
        //Back
        OFFSET * 2, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 2,
        OFFSET * 2, OFFSET * 2,
        //Left
        OFFSET * 2, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 2,
        OFFSET * 2, OFFSET * 2,
        //Right
        OFFSET * 2, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 2,
        OFFSET * 2, OFFSET * 2,};
    
     final float snowSandTexCoords[] = new float[]{
        //Top
        OFFSET * 5, OFFSET * 7,
        OFFSET * 6, OFFSET * 7,
        OFFSET * 6, OFFSET * 8,
        OFFSET * 5, OFFSET * 8,
        //Bottom
       OFFSET * 5, OFFSET * 7,
        OFFSET * 6, OFFSET * 7,
        OFFSET * 6, OFFSET * 8,
        OFFSET * 5, OFFSET * 8,
        //Front
        OFFSET * 5, OFFSET * 7,
        OFFSET * 6, OFFSET * 7,
        OFFSET * 6, OFFSET * 8,
        OFFSET * 5, OFFSET * 8,
        //Back
      OFFSET * 5, OFFSET * 7,
        OFFSET * 6, OFFSET * 7,
        OFFSET * 6, OFFSET * 8,
        OFFSET * 5, OFFSET * 8,
        //Left
        OFFSET * 5, OFFSET * 7,
        OFFSET * 6, OFFSET * 7,
        OFFSET * 6, OFFSET * 8,
        OFFSET * 5, OFFSET * 8,
        //Right
        OFFSET * 5, OFFSET * 7,
        OFFSET * 6, OFFSET * 7,
        OFFSET * 6, OFFSET * 8,
        OFFSET * 5, OFFSET * 8,};

    final float waterTexCoords[] = new float[]{
        //Top
        OFFSET * 13, OFFSET * 12,
        OFFSET * 14, OFFSET * 12,
        OFFSET * 14, OFFSET * 13,
        OFFSET * 13, OFFSET * 13,
        //Bottom
        OFFSET * 13, OFFSET * 12,
        OFFSET * 14, OFFSET * 12,
        OFFSET * 14, OFFSET * 13,
        OFFSET * 13, OFFSET * 13,
        //Front
        OFFSET * 13, OFFSET * 12,
        OFFSET * 14, OFFSET * 12,
        OFFSET * 14, OFFSET * 13,
        OFFSET * 13, OFFSET * 13,
        //Back
        OFFSET * 13, OFFSET * 12,
        OFFSET * 14, OFFSET * 12,
        OFFSET * 14, OFFSET * 13,
        OFFSET * 13, OFFSET * 13,
        //Left
        OFFSET * 13, OFFSET * 12,
        OFFSET * 14, OFFSET * 12,
        OFFSET * 14, OFFSET * 13,
        OFFSET * 13, OFFSET * 13,
        //Right
        OFFSET * 13, OFFSET * 12,
        OFFSET * 14, OFFSET * 12,
        OFFSET * 14, OFFSET * 13,
        OFFSET * 13, OFFSET * 13,};

    final float dirtTexCoords[] = new float[]{
        //Top
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Bottom
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Front
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Back
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Left
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Right
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,};

    final float stoneTexCoords[] = new float[]{
        //Top
        OFFSET * 1, OFFSET * 0,
        OFFSET * 2, OFFSET * 0,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 1, OFFSET * 1,
        //Bottom
        OFFSET * 1, OFFSET * 0,
        OFFSET * 2, OFFSET * 0,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 1, OFFSET * 1,
        //Front
        OFFSET * 1, OFFSET * 0,
        OFFSET * 2, OFFSET * 0,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 1, OFFSET * 1,
        //Back
        OFFSET * 1, OFFSET * 0,
        OFFSET * 2, OFFSET * 0,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 1, OFFSET * 1,
        //Left
        OFFSET * 1, OFFSET * 0,
        OFFSET * 2, OFFSET * 0,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 1, OFFSET * 1,
        //Right
        OFFSET * 1, OFFSET * 0,
        OFFSET * 2, OFFSET * 0,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 1, OFFSET * 1,};

    final float bedrockTexCoords[] = new float[]{
        //Top
        OFFSET * 1, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 2, OFFSET * 2,
        OFFSET * 1, OFFSET * 2,
        //Bottom
        OFFSET * 1, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 2, OFFSET * 2,
        OFFSET * 1, OFFSET * 2,
        //Front
        OFFSET * 1, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 2, OFFSET * 2,
        OFFSET * 1, OFFSET * 2,
        //Back
        OFFSET * 1, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 2, OFFSET * 2,
        OFFSET * 1, OFFSET * 2,
        //Left
        OFFSET * 1, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 2, OFFSET * 2,
        OFFSET * 1, OFFSET * 2,
        //Right
        OFFSET * 1, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 2, OFFSET * 2,
        OFFSET * 1, OFFSET * 2,};
    ///================================

    ///Constructor
    public TextureController() {
        r = new Random((int) System.currentTimeMillis());
    }

    ///Binds Texture object
    public void bindTexture() {
        texture.bind();
    }

    ///Loads texture "terrain.png" from the assets folder
    public void loadTexture() {
        texture = null;
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("terrain.png"));
        } catch (Exception e) {
            System.out.println("Exception in Chunk constructor");
        }
    }

    ///Randomly pick a block texture
    public Block getBlockTexture(int x, int y, int z, int maxHeight) {
        float chance = r.nextFloat();
        if (y == maxHeight) {
            if (y <= 8) {
                return new Block(BlockType.Water);
            } else if (y == 9) {
                return new Block(BlockType.Sand);
            } else {
                return new Block(BlockType.Grass);
            }
        } else if (y < maxHeight && y > maxHeight - 3) {
            return new Block(BlockType.Dirt);
        } else if (y <= maxHeight - 3 && y > maxHeight - 6) {
            return new Block(BlockType.Stone);
        } else if (y == 0) {
            return new Block(BlockType.Bedrock);
        }
        return new Block(BlockType.Bedrock);
    }
    
    ///Randomly pick a block texture for Winter Season
     public Block getWinterTexture(int x, int y, int z, int maxHeight) {
        float chance = r.nextFloat();
        if (y == maxHeight) {
            if (y <= 8) {
                return new Block(BlockType.Water);
            } else if (y == 9) {
                return new Block(BlockType.SnowSand);
            } else {
                return new Block(BlockType.Snow);
            }
        } else if (y < maxHeight && y > maxHeight - 3) {
            return new Block(BlockType.Dirt);
        } else if (y <= maxHeight - 3 && y > maxHeight - 6) {
            return new Block(BlockType.Stone);
        } else if (y == 0) {
            return new Block(BlockType.Bedrock);
        }
        return new Block(BlockType.Bedrock);
    }

    // creates a cube
    // Edited this so that the first cube is created nicely extending from the starting point and not around it.
    // The edit was for ease of physics calculations
    public float[] createCube(float x, float y, float z) {
        int offset = CUBE_LENGTH / 2;
        return new float[]{
            // TOP QUAD
            x + CUBE_LENGTH, y + CUBE_LENGTH, z + CUBE_LENGTH,
            x, y + CUBE_LENGTH, z + CUBE_LENGTH,
            x, y + CUBE_LENGTH, z,
            x + CUBE_LENGTH, y + CUBE_LENGTH, z,
            
            // BOTTOM QUAD
            x + CUBE_LENGTH, y, z,
            x, y, z,
            x, y, z + CUBE_LENGTH,
            x + CUBE_LENGTH, y, z + CUBE_LENGTH,
            
            // FRONT QUAD
            x + CUBE_LENGTH, y + CUBE_LENGTH, z,
            x, y + CUBE_LENGTH, z,
            x, y, z,
            x + CUBE_LENGTH, y, z,
            
            // BACK QUAD
            x + CUBE_LENGTH, y, z + CUBE_LENGTH,
            x, y, z + CUBE_LENGTH,
            x, y + CUBE_LENGTH, z + CUBE_LENGTH,
            x + CUBE_LENGTH, y + CUBE_LENGTH, z + CUBE_LENGTH,
            
            // LEFT QUAD
            x, y + CUBE_LENGTH, z,
            x, y + CUBE_LENGTH, z + CUBE_LENGTH,
            x, y, z + CUBE_LENGTH,
            x, y, z,
            
            // RIGHT QUAD
            x + CUBE_LENGTH, y + CUBE_LENGTH, z + CUBE_LENGTH,
            x + CUBE_LENGTH, y + CUBE_LENGTH, z,
            x + CUBE_LENGTH, y, z,
            x + CUBE_LENGTH, y, z + CUBE_LENGTH
        };
    }

    // creates texture cube
    public float[] createTexCube(Block block) {
        switch (block.getType()) {
             case Grass:
                return grassTexCoords;
            case Sand:
                return sandTexCoords;
            case Water:
                return waterTexCoords;
            case Dirt:
                return dirtTexCoords;
            case Stone:
                return stoneTexCoords;
            case Bedrock:
                return bedrockTexCoords;
                case Snow:
                return snowTexCoords;
            case SnowSand:
                return snowSandTexCoords;
            
        }

        return grassTexCoords;
    }
}
