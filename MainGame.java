
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import Engine.EngineController;
import objects.GameObject;
import objects.Projection;
import objects.Rectangle;
import objects.View;
import objects.Window;
import utils.Collision;
import utils.Color;
import utils.Input;
import utils.Physics;
import utils.Time;

import java.util.Random;

public class MainGame {
    private Window window;
    private View camera;
    private Projection projection;
    private int width = 1920;
    private int height = 1280;

    public Time timer;
    public int dir = 1;

    public Rectangle player;
    public Rectangle[][] ground;
    public int[] map;


    public Time colTimmer;

    private void genMap() {
        Random rand = new Random();
        map = new int[ground.length];
        for (int i = 0; i < map.length; i++) {
            int x = rand.nextInt(10);
            if (x <= 7)
                map[i] = -1;
            else
                map[i] = 0;
            map[i] = -1;
        }
    }

    MainGame() {
        window = new Window(width, height, "Game");
        ;
        EngineController.initMainWindow(window);
        init();
    }

    public void init(){
        camera = new View();
        camera.cameraPos.z = 12;
        projection = new Projection((float)width/(float)height);
        player = new Rectangle();

        ground = new Rectangle[150][6];
        
   

        genMap();


        timer = new Time();
   
        colTimmer = new Time();

        GameObject.create((Rectangle)player);



        for(int i=0; i<ground.length; i++){
            for(int j =0; j<ground[i].length; j++){
                ground[i][j] = new Rectangle();
                ground[i][j].collision = new Collision(new Vector3f(1,1,0));
                ground[i][j].transform.position = new Vector3f(i, map[i]-j ,0);
                ground[i][j].loadTexture("assets/dirt.png");

                GameObject.create((Rectangle)ground[i][j]);
            }
        }
        



        player.collision = new Collision(new Vector3f(1,1,0));
     
        player.loadTexture("assets/player.png");

        player.transform.position.y = 1;

        player.physics = new Physics();

        player.physics.dir = 1;



    }

    public void controls() {
        if (Input.getKeyDown(GLFW.GLFW_KEY_D)) {
            player.physics.movement = 1f;
            player.physics.dir = 1;
        }

        else if (Input.getKeyDown(GLFW.GLFW_KEY_A)) {
            player.physics.movement = -1f;
            player.physics.dir = -1;
        } else {
            player.physics.movement = 0;
        }

        if (Input.getKeyDown(GLFW.GLFW_KEY_SPACE) && player.physics.onGround) {
            player.physics.onGround();
        }

    }

    public void run() {
        window.runRenderQueue(camera.cameraPos, 10);

        while (!window.isRunning()) {

            window.render();
            EngineController.useDefualtProgram();

            controls();

            if (player.transform.position.x > camera.cameraPos.x + 4) {
                timer.restart();
                dir = 1;
            }
            if (player.transform.position.x < camera.cameraPos.x - 4) {
                timer.restart();
                dir = -1;

            }
            player.transform.scale.x = player.physics.dir;
            if (timer.setTimer(1.5) != 0 && player.transform.position.x != camera.cameraPos.x) {
                camera.cameraPos.x += (timer.setTimer(1.5) / 30) * dir * 2f;
            }

 

            camera.render();
            window.renderAll(camera.cameraPos, 8);
            projection.sendMatrix();
            window.pollEvent();
        }
        window.cleanUp();
    }

    public static void main(String[] args) {
        MainGame mainGame = new MainGame();
        mainGame.run();

    }
}