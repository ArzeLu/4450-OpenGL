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

    ;
    
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
    public Block getBlockTexture() {
        float chance = r.nextFloat();

        if (chance > 0.6f) {
            return new Block(BlockType.Grass);
        } else if (chance > 0.5f) {
            return new Block(BlockType.Dirt);
        } else if (chance > 0.4f) {
            return new Block(BlockType.Sand);
        } else if (chance > 0.2f) {
            return new Block(BlockType.Stone);
        } else if (chance > 0.1f) {
            return new Block(BlockType.Bedrock);
        }
        return new Block(BlockType.Water);
    }

    // creates a cube
    public float[] createCube(float x, float y, float z) {
        int offset = CUBE_LENGTH / 2;
        return new float[]{
            // TOP QUAD
            x + offset, y + offset, z,
            x - offset, y + offset, z,
            x - offset, y + offset, z - CUBE_LENGTH,
            x + offset, y + offset, z - CUBE_LENGTH,
            // BOTTOM QUAD
            x + offset, y - offset, z - CUBE_LENGTH,
            x - offset, y - offset, z - CUBE_LENGTH,
            x - offset, y - offset, z,
            x + offset, y - offset, z,
            // FRONT QUAD
            x + offset, y + offset, z - CUBE_LENGTH,
            x - offset, y + offset, z - CUBE_LENGTH,
            x - offset, y - offset, z - CUBE_LENGTH,
            x + offset, y - offset, z - CUBE_LENGTH,
            // BACK QUAD
            x + offset, y - offset, z,
            x - offset, y - offset, z,
            x - offset, y + offset, z,
            x + offset, y + offset, z,
            // LEFT QUAD
            x - offset, y + offset, z - CUBE_LENGTH,
            x - offset, y + offset, z,
            x - offset, y - offset, z,
            x - offset, y - offset, z - CUBE_LENGTH,
            // RIGHT QUAD
            x + offset, y + offset, z,
            x + offset, y + offset, z - CUBE_LENGTH,
            x + offset, y - offset, z - CUBE_LENGTH,
            x + offset, y - offset, z
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
        }

        return grassTexCoords;
    }
}
