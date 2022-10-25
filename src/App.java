

import org.lwjgl.glfw.GLFW;
import org.joml.Vector3f;

import Engine.EngineController;
import objects.Circle;
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


public class App {
    private View view;
    private Projection proj;
    private Window window;

    public float movement;
    public float speed = 0.1f;

    public float jumbSpeed = 0.11f;
    public float jumbEnable = 0;
    public float gravity = 0.18f;

    public boolean onJumb = false;
    public boolean isTouch = false;

    public Vector3f force;

    public boolean onGround = false;

    Time time;

    Rectangle rect;
    Rectangle ground;
    Rectangle floor;
    Rectangle ground2;
    Rectangle ground3;


    Rectangle enemy;
    Rectangle circle;

    public static void main(String[] args) {
        new App().app();
    }

    private void init() {
        int winWidth = 720, winHeight = 720;
        String title = "My title";
        window = new Window(winWidth, winHeight, title);
        EngineController.initMainWindow(window);

        proj = new Projection((float) winWidth / (float) winHeight);

        view = new View();

        window.setBackGroundColor(new Color(25, 25, 35));

        time = new Time();
        force = new Vector3f();
    }

    private void createObjs() {

        rect = (Rectangle) GameObject.create(new Rectangle());
        rect.transform.position.x = 1;

        ground = (Rectangle) GameObject.create(new Rectangle());

        ground2 = (Rectangle) GameObject.create(new Rectangle());
        floor = (Rectangle) GameObject.create(new Rectangle());
        enemy = (Rectangle) GameObject.create(new Rectangle());
        circle = (Rectangle ) GameObject.create(new Rectangle());
        circle.transform.scale = new Vector3f(0.5f,0.5f, 1);
        floor.transform.position = new Vector3f(3, 1, 0);
        floor.transform.scale.x = 2;
        ground.transform.position.y = -1;
        ground.transform.scale.x = 10;
    

        ground2.transform.position.y = 2;
        ground2.transform.position.x = -1;
        ground2.transform.scale.x = 2;
        enemy.transform.position.x = -2;
        circle.collision = new Collision(new Vector3f(0.5f, 0.5f, 0));
        enemy.collision = new Collision(new Vector3f(1f, 1f, 0));
        rect.collision = new Collision(new Vector3f(1f, 1f, 0));
        floor.collision = new Collision(new Vector3f(2f, 1f, 0));
        ground.collision = new Collision(new Vector3f(10f, 1f, 0));


        ground2.collision = new Collision(new Vector3f(2f, 1f, 0));
        ground2.loadTexture("assets/platform.png");
        floor.loadTexture("assets/platform.png");
        ground.loadTexture("assets/platform.png");

   
  

    }

    void app() {
        init();

        createObjs();
        view.cameraPos.z = 10;

        view.setCameraIoMovement(false);

        time = new Time();
        isTouch = false;
        rect.physics = new Physics();
        enemy.physics = new Physics();
        circle.physics = new Physics();
        // circle.physics.gravity = 0;
    
        window.runRenderQueue(view.cameraPos, 3);
        while (!window.isRunning()) {
            window.render();
            EngineController.useDefualtProgram();
            if (Input.getKeyDown(GLFW.GLFW_KEY_ESCAPE))
                break;
            view.render();
            proj.sendMatrix();

            if (Input.getKeyDown(GLFW.GLFW_KEY_D)) {
                rect.physics.movement = movement = 1;


            } else if (Input.getKeyDown(GLFW.GLFW_KEY_A)) {
                rect.physics.movement = movement = -1;
        
            } else
                rect.physics.movement = 0;

            if (Input.getKeyDown(GLFW.GLFW_KEY_RIGHT)) {
                enemy.physics.movement  = 1;

            } else if (Input.getKeyDown(GLFW.GLFW_KEY_LEFT)) {
                enemy.physics.movement  = -1;

            } else
                enemy.physics.movement = 0;

            if (Input.getKeyDown(GLFW.GLFW_KEY_SPACE) && rect.physics.onGround) {
                rect.physics.onGround();
            }

            if (rect.transform.position.y > view.cameraPos.y + 4) {
                view.cameraPos.y = rect.transform.position.y;
            }
            if (rect.transform.position.y < view.cameraPos.y - 4) {
                view.cameraPos.y = rect.transform.position.y;;

            }
            if(Input.getKeyDown(GLFW.GLFW_KEY_F)){

                circle.physics.movement = -2;
            }
            view.cameraPos.x = rect.transform.position.x;

            window.renderAll();
            window.pollEvent();

        }

        window.cleanUp();
    }
}

/*
 * Notes :
 * when i solve collision problem there's new problem exist
 * it's a more than 2 objects exits
 */