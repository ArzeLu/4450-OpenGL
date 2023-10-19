/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

// Made by Arze, Harshitha, Rucha
package cs4450.project3;

//class dependencies
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

//my own imports

public class Project3 {   
    final private CameraController camera = new CameraController(0f, 0f, 0f);
    private DisplayMode displayMode;

    //Initiate GL process
    private void initGL(){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        glEnable(GL_DEPTH_TEST);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth() / (float)displayMode.getHeight(), 01f, 300.0f);
        
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    
    //Create and set a graphics window
    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        
        for(DisplayMode element : d){
            if(element.getWidth() == 640 && element.getHeight() == 480 && element.getBitsPerPixel() == 32){
                displayMode = element;
                break;
            }
        }
        
        Display.setDisplayMode(displayMode);
        Display.setTitle("using le opengl desu");
        Display.create();
    }
    
    public void start(){
        try{
            createWindow();
            initGL();
            camera.gameLoop(); //rendering
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public static void main(String[] args) {
        Project3 proj = new Project3();
        proj.start();
    }
}
