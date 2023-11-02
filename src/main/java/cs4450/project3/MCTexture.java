/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs4450.project3;

/**
 *
 * @author arze7
 */
public class MCTexture {
    final private static float OFFSET = (1024f / 16) / 1024f;
    
    final static float grassTexCoords[] = new float[]{
        //Bottom (x, y)
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Top
        OFFSET * 12, OFFSET * 12,
        OFFSET * 13, OFFSET * 12,
        OFFSET * 13, OFFSET * 13,
        OFFSET * 12, OFFSET * 13,
        //Front
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Back
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Left
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Right
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
    };
    
    final static float sandTexCoords[] = new float[]{
        //Bottom
        OFFSET * 2, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 3, OFFSET * 2,
        OFFSET * 2, OFFSET * 2,
        //Top
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
        OFFSET * 2, OFFSET * 2,
    };
    
    final static float waterTexCoords[] = new float[]{
        //Bottom
        OFFSET * 13, OFFSET * 12,
        OFFSET * 14, OFFSET * 12,
        OFFSET * 14, OFFSET * 13,
        OFFSET * 13, OFFSET * 13,
        //Top
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
        OFFSET * 13, OFFSET * 13,
    };
    
    final static float dirtTexCoords[] = new float[]{
        //Bottom
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Top
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
        OFFSET * 2, OFFSET * 1,
    };
    
    final static float stoneTexCoords[] = new float[]{
        //Bottom
        OFFSET * 1, OFFSET * 0,
        OFFSET * 2, OFFSET * 0,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 1, OFFSET * 1,
        //Top
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
        OFFSET * 1, OFFSET * 1,
    };
    
    final static float bedrockTexCoords[] = new float[]{
        //Bottom
        OFFSET * 1, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        OFFSET * 2, OFFSET * 2,
        OFFSET * 1, OFFSET * 2,
        //Top
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
        OFFSET * 1, OFFSET * 2,
    };
    
    final static float defaultTexCoords[] = new float[]{
        //Bottom (x, y)
        OFFSET * 2, OFFSET * 0,
        OFFSET * 3, OFFSET * 0,
        OFFSET * 3, OFFSET * 1,
        OFFSET * 2, OFFSET * 1,
        //Top
        OFFSET * 12, OFFSET * 12,
        OFFSET * 13, OFFSET * 12,
        OFFSET * 13, OFFSET * 13,
        OFFSET * 12, OFFSET * 13,
        //Front
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Back
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Left
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
        //Right
        OFFSET * 3, OFFSET * 0,
        OFFSET * 4, OFFSET * 0,
        OFFSET * 4, OFFSET * 1,
        OFFSET * 3, OFFSET * 1,
    };
}
