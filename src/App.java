import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.joml.Vector3f;

import Engine.EngineController;
import objects.Circle;
import objects.Cube;
import objects.Lines;
import objects.Projection;
import objects.Rectangle;
import objects.Sprite;
import objects.View;
import objects.Window;
import utils.Color;
import utils.Input;
import utils.Texture2D;

public class App{

    public static void main(String[] args) {
        new App().app();
    }

    void app(){

        int winWidth = 1900 , winHeight =  1200;
        String title = "My title";
        Window window = new Window(winWidth, winHeight, title);
 
        Texture2D[] tex = new Texture2D[5];
        
        tex[0] = new Texture2D("src/ss.jpg");
        tex[1] = new Texture2D("src/lava.jpg");
        tex[2] = new Texture2D("src/player.png");
        tex[3] = new Texture2D("src/dirt_0.png");
        tex[4] = new Texture2D("src/ball.png");

        Circle circle = new Circle();
        circle.loadTexture(tex[4]);
    
        Projection proj = new Projection( (float)winWidth/(float)winHeight);
    
        View view = new View();

  
        Random rand = new Random();
        Rectangle[] models = new Rectangle[1600];
        int c=0;
        int x = 120;
        window.setBackGroundColor(new Color(x, x, x));
        for(int i=0; i<40; i++){
            for(int j=0; j<40; j++){
                models[c] = new Rectangle();
                models[c].transform.scale = new Vector3f(0.1f,0.1f,1f);
                models[c].transform.position = new Vector3f(rand.nextInt(1000) -rand.nextInt(1000) , -rand.nextInt(50) ,rand.nextInt(1000) -rand.nextInt(1000));
                models[c].color = new Color(100, 120, 130);
                models[c++].transform.rotation = new Vector3f(0,0,(float)Math.toRadians(45));
            }
        }

  
 
        Cube ml = new Cube();
        ml.color = new Color(180, 60, 60);
        ml.transform.scale = new Vector3f(3,3,3);
        ml.loadTexture(tex[3]);


        Lines line = new Lines();
        // view.setCameraIoMovement(true);

        Sprite sprite = new Sprite(tex[4]);
        Cube cube = new Cube();
        cube.loadTexture(tex[3]);
        cube.transform.position = new Vector3f(0,0,-1);   
        view.cameraPos.z = 10;
        while(!window.isRunning()){
    
            window.render();
            EngineController.useDefualtProgram();
            


            view.render();

            proj.sendMatrix();
          
            sprite.transform.rotation = new Vector3f(0,0,0);
            sprite.draw();
            cube.draw();
            line.draw();
            if(Input.getKeyDown(GLFW.GLFW_KEY_D)){
                sprite.transform.position.x += 0.08; 
            }
            if(Input.getKeyDown(GLFW.GLFW_KEY_A)){
                sprite.transform.position.x -= 0.08; 
            }

            view.cameraPos.x =  sprite.transform.position.x ;

            window.pollEvent();
            
        }        
        
        window.cleanUp();
    }
}