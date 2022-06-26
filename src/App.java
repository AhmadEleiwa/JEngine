import java.util.Random;


import org.joml.Vector3f;

import Engine.EngineController;
import objects.Cube;
import objects.Lines;
import objects.Projection;
import objects.Rectangle;
import objects.Sprite;
import objects.View;
import objects.Window;
import utils.Color;
import utils.Texture2D;


public class App{

    public static void main(String[] args) {
        new App().app();
    }

    void app(){

        int winWidth = 1900 , winHeight =  1200;
        String title = "My title";
        Window window = new Window(winWidth, winHeight, title);
 
        Texture2D[] tex = new Texture2D[4];
        
        tex[0] = new Texture2D("src/ss.jpg");
        tex[1] = new Texture2D("src/lava.jpg");
        tex[2] = new Texture2D("src/player.png");
        tex[3] = new Texture2D("src/dirt_0.png");



    
        Projection proj = new Projection( (float)winWidth/(float)winHeight);
    
        View view = new View();

  
        Random rand = new Random();
        Rectangle[] models = new Rectangle[1600];
        int c=0;
        for(int i=0; i<40; i++){
            for(int j=0; j<40; j++){
                models[c] = new Rectangle();
                models[c].transform.scale = new Vector3f(0.1f,0.1f,1f);
                models[c].transform.position = new Vector3f(rand.nextInt(1000) -rand.nextInt(1000) , -rand.nextInt(50) ,rand.nextInt(1000) -rand.nextInt(1000));
                models[c].color = new Color(100, 120, 130);
                models[c++].transform.rotation = new Vector3f(0,0,(float)Math.toRadians(45));
            }
        }

  
        float ci = 0;
        float radius = 10;

        Cube ml = new Cube();
        ml.color = new Color(180, 60, 60);
        ml.transform.scale = new Vector3f(3,3,3);
        ml.loadTexture(tex[3]);


        Lines line = new Lines();
        view.setCameraIoMovement(true);
        view.cameraPos = new Vector3f(0,0,5);
        Sprite s = new Sprite(tex[0]);
        Sprite rect = new Sprite(tex[3]);
        Sprite s2 = new Sprite(tex[2]);
        s2.transform.position = new Vector3f(-2,0,0);
        s2.transform.scale = new Vector3f(-1,1,1);
        rect.transform.position = new Vector3f(2,0,0);
        while(!window.isRunning()){
    
            window.render();
            EngineController.useDefualtProgram();
            


            view.render();

            proj.sendMatrix();
            line.draw(); 
            ml.draw();

            ml.transform.position = new Vector3f(radius * (float)Math.sin(ci*1.2),0,radius* (float)Math.cos(ci*1.2f));
        
            rect.draw();
            
            s.draw();
            s2.draw();
            int x = rand.nextInt(2);
            window.setBackGroundColor(new Color(x, x, x));
            ci += 0.01f;

            window.pollEvent();
            
        }        
        
        window.cleanUp();
    }
}