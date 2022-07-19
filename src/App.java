import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.joml.Vector3f;

import Engine.EngineController;
import objects.Circle;
import objects.CosShape;
import objects.CosShape;
import objects.Cube;
import objects.GameObject;
import objects.Light;
import objects.Lines;
import objects.Projection;
import objects.Rectangle;
import objects.Sprite;
import objects.View;
import objects.Window;
import utils.Animation;
import utils.Color;
import utils.Frame;
import utils.Input;
import utils.Time;
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
        
        tex[0] = new Texture2D("src/Fall (32x32).png");
        tex[1] = new Texture2D("src/Jump (32x32).png");
        tex[2] = new Texture2D("src/player.png");
        tex[3] = new Texture2D("src/dirt_0.png");
        tex[4] = new Texture2D("src/grass.jpg");


        Projection proj = new Projection( (float)winWidth/(float)winHeight);
    
        View view = new View();

        window.setBackGroundColor(new Color(125, 125, 164));

 
 

        Lines line = new Lines();
        // view.setCameraIoMovement(true);


        Light l = new Light();
        l.transform.position = new Vector3f(2,8,3);
        l.color = new Color(240, 240, 240);
        Random rand =new Random();
        Cube[] cube = new Cube[100];
        for(int i=0; i<10; i++){
            for(int j=0 ;j<10; j++){
                cube[(i*10)+j] = new Cube();
                cube[(i*10)+j].loadTexture(tex[3]);
                cube[(i*10)+j].transform.position = new Vector3f(i, rand.nextInt(2), j);
            }
        }
  
        view.cameraPos.y = 0;
        view.cameraPos.x = 0;
        view.cameraPos.z = 15;

 
        Animation animation = new Animation();
        animation.addFrame(new Frame(tex[0], 0.1f));
        animation.addFrame(new Frame(tex[1], 0.25f));

        Sprite sprite = new Sprite(tex[0]);
        ArrayList<Circle> fires = new ArrayList<>();
        ArrayList<Integer> dir = new ArrayList<>();
        Time timer = new Time();
            while(!window.isRunning()){
            window.render();
            EngineController.useDefualtProgram();


            if(Input.getKeyDown(GLFW.GLFW_KEY_D)){
                sprite.transform.position.x +=0.1f;
                sprite.transform.scale.x = -1;

            }else if(Input.getKeyDown(GLFW.GLFW_KEY_A)){
                sprite.transform.position.x -=0.1f;
                sprite.transform.scale.x = 1;
            }
            else{
                animation.reset();
            }
            sprite.loadTexture(animation.animate().texture);

            if(Input.getKeyDown(GLFW.GLFW_KEY_F) && timer.getTime() > 0.5f){
                Circle c = new Circle();
                c.transform.scale =new Vector3f(0.2f,0.2f, 1);
                c.color =new Color(250, 140, 10);
                c.transform.position = new Vector3f(sprite.transform.position);
                fires.add(c);
                dir.add(-(int)sprite.transform.scale.x);
                timer.restart();
            }
            for(int i=0; i<fires.size(); i++){
                fires.get(i).transform.position.x += (0.1f+timer.getTime()) * dir.get(i) ;
                fires.get(i).draw();
            }
            view.render();
            proj.sendMatrix();

            // line.draw();


            sprite.draw();;
            l.draw();
 

    
            window.pollEvent();
            
        }        
        
        window.cleanUp();
    }
}