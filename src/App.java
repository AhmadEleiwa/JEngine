import static org.lwjgl.glfw.GLFW.*;
import java.util.Random;

import javax.swing.text.Position;

import static org.lwjgl.opengl.GL20.*;

import org.joml.Vector3f;


public class App{

    public static void main(String[] args) {
        new App().app();
    }

    void app(){
        if(!glfwInit()){
            System.out.println("Failed to init glfw");
        } 
        
        int winWidth = 1900 , winHeight =  1200;
        String title = "My title";
        Window window = new Window(winWidth, winHeight, title);
 
        Texture2D[] tex = new Texture2D[3];
        
        tex[0] = new Texture2D("src/image.jpg");
        tex[1] = new Texture2D("src/lava.jpg");
        tex[2] = new Texture2D("src/grass.jpg");


    
        Projection proj = new Projection( (float)winWidth/(float)winHeight);
    
        View view = new View();

  
        Random rand = new Random();
        Cube[] models = new Cube[1600];
        int c=0;
        for(int i=0; i<40; i++){
            for(int j=0; j<40; j++){
                models[c] = new Cube();
                models[c].transform.scale = new Vector3f(0.2f,0.3f,0.2f);
                models[c++].transform.position = new Vector3f(rand.nextInt(1000) -rand.nextInt(1000) , -rand.nextInt(50) ,rand.nextInt(1000) -rand.nextInt(1000));
            }
        }
   
        Cube cube = new Cube();
     
        Lines line = new Lines();
  
        float ci = 0;
        float radius = 10;
        models[0].color = new Color(180, 60, 60);
        models[0].transform.scale = new Vector3f(3,3,3);
        models[0].loadTexture(tex[1]);

        models[1].transform.scale = new Vector3f(100,100,100);
        models[1].color = new Color(200, 80, 90);
        models[1].loadTexture(tex[1]);


        while(!window.isRunning()){
    
            window.render();
            EngineController.useDefualtProgram();
            


            view.render();

            proj.sendMatrix();
            // line.draw(); 
            models[0].draw();

            models[0].transform.position = new Vector3f(radius * (float)Math.sin(ci*1.2),0,radius* (float)Math.cos(ci*1.2f));
            models[1].draw();

            models[1].transform.position = new Vector3f(radius*4 * (float)Math.sin(ci),0,radius*4* (float)Math.cos(ci));


            int x = rand.nextInt(5);
            window.setBackGroundColor(new Color(x, x, x));

            for(int i=2; i < 1600 ; i++){
                models[i].draw();
            }
            ci += 0.01f;

            window.pollEvent();
            
        }        
        
        window.cleanUp();
    }
}