/** ***************************************************************
 * File: Chunk.java
 * Authors: Arze, Harshitha, Rucha
 * Class: CS 4450 - Computer Graphics
 *
 * Assignment: Group Project - Checkpoint 3
 * Date last modified: 11/23/2023
 *
 * Purpose: This program creates chunk of cubes
 *
 **************************************************************** */
package cs4450.project3;

//class imports
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Chunk {
    static final int CHUNK_SIZE = 100;
    static final int CUBE_LENGTH = 2;
    static final int MAX_HEIGHT = CHUNK_SIZE / 2;
    private Block[][][] blocks;
    private SimplexNoise simplexNoise;
    private TextureController texture;
    private int VBOVertexHandle;
    private int VBOTextureHandle;
    private int startX, startY, startZ;
    
    // method: render
    // purpose: Starts rendering loop
    public void render(){
        glPushMatrix();
        glBindBuffer(GL_ARRAY_BUFFER, VBOTextureHandle);
        glBindTexture(GL_TEXTURE_2D, 1);
        glTexCoordPointer(2, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, VBOVertexHandle);
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glDrawArrays(GL_QUADS, 0, CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE * 24);
        glPopMatrix();
    }
    
    // method: rebuildMesh
    // purpose: creates chunk of cubes
    public void rebuildMesh(float startX, float startY, float startZ){
        VBOVertexHandle = glGenBuffers();
        VBOTextureHandle = glGenBuffers();
        FloatBuffer vertexTextureData = BufferUtils.createFloatBuffer(CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE * 6 * 12); //6 sets of coords 12 each. Refer to TextureMaker.java
        FloatBuffer vertexPositionData = BufferUtils.createFloatBuffer(CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE * 6 * 12);
        
        for(int x = 0; x < CHUNK_SIZE; x++){
            
            for(int z = 0; z < CHUNK_SIZE; z++){
                
                int maxHeight = (int)((simplexNoise.getNoise(x, z)) * 20 + 10);
                
                for(int y = 0; y <= maxHeight; y++){
                    blocks[x][y][z] = texture.getBlockTexture(x,y,z,maxHeight);

                    vertexTextureData.put(texture.createTexCube(blocks[x][y][z]));
                    vertexPositionData.put(texture.createCube((float)(startX + x * CUBE_LENGTH), (float)(y * CUBE_LENGTH + startY), (float)(startZ + z * CUBE_LENGTH)));
                }
            }
        }

        vertexTextureData.flip();
        vertexPositionData.flip();
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOTextureHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexTextureData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexPositionData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);        
    }
    
    // method: rebuildWinterMesh
    //purpose: creates chunk of cubes for Season change : Winter 
    public void rebuildWinterMesh(float startX, float startY, float startZ){
        VBOVertexHandle = glGenBuffers();
        VBOTextureHandle = glGenBuffers();
        FloatBuffer vertexTextureData = BufferUtils.createFloatBuffer(CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE * 6 * 12); //6 sets of coords 12 each. Refer to TextureMaker.java
        FloatBuffer vertexPositionData = BufferUtils.createFloatBuffer(CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE * 6 * 12);
        
        for(int x = 0; x < CHUNK_SIZE; x++){
            
            for(int z = 0; z < CHUNK_SIZE; z++){
                
                int maxHeight = (int)((simplexNoise.getNoise(x, z)) * 20 + 10);
                
                for(int y = 0; y <= maxHeight; y++){
                    blocks[x][y][z] = texture.getWinterTexture(x,y,z,maxHeight);

                    vertexTextureData.put(texture.createTexCube(blocks[x][y][z]));
                    vertexPositionData.put(texture.createCube((float)(startX + x * CUBE_LENGTH), (float)(y * CUBE_LENGTH + startY), (float)(startZ + z * CUBE_LENGTH)));
                }
            }
        }

        vertexTextureData.flip();
        vertexPositionData.flip();
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOTextureHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexTextureData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexPositionData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);        
    }
    
    // Chunk class
    // Calls rebuildMesh() method
    public Chunk(int startX, int startY, int startZ){
        texture = new TextureController();
        blocks = new Block[CHUNK_SIZE][CHUNK_SIZE][CHUNK_SIZE];
        simplexNoise = new SimplexNoise(MAX_HEIGHT, 0.15f, (int)System.currentTimeMillis());
        
        VBOVertexHandle = glGenBuffers();
        VBOTextureHandle = glGenBuffers();
        
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;

        rebuildMesh(startX, startY, startZ);
    }
}
