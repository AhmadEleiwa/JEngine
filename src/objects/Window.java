package objects;

// import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.opengl.GL;

import Engine.EngineController;
import utils.Collision;
import utils.Color;
import utils.KeyListener;
import utils.MouseListener;
import utils.MouseMotionListener;

public class Window {

    private int width;
    private int height;
    private String title;
    private long window;
    private Color BackGroundColor;
    private ArrayList<GameObject> Objs;
    private Thread thread;
    private Vector3f renderPos = new Vector3f();
    ArrayList<Integer> unrendering = new ArrayList<>();
    private int renderRange = 10;
    ArrayList<Integer> renderingObjects = new ArrayList<>();

    public Window(int width, int height, String title) {
        if (!glfwInit()) {
            System.out.println("Failed to init glfw");
        }
        this.Objs = new ArrayList<GameObject>();
        this.width = width;
        this.height = height;
        this.title = title;
        BackGroundColor = new Color(120, 125, 145);
        glfwWindowHint(GLFW_VISIBLE, 1);

        window = glfwCreateWindow(width, height, title, 0, 0);

        EngineController.addWindow(window);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);

        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        glfwSetCursorPos(window, width / 2, height / 2);

    }

    public void disabledCursor() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public void setCursorHidden() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
    }

    public void render() {
        glClearColor((float) BackGroundColor.r / 255, (float) BackGroundColor.g / 255, (float) BackGroundColor.b / 255,
                1.0f);
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    }

    public void pollEvent() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void cleanUp() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public boolean isRunning() {
        return glfwWindowShouldClose(window);
    }

    public long getWindow() {
        return window;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getBackGroundColor() {
        return BackGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        BackGroundColor = backGroundColor;
    }

    public boolean keyPressed(int key) {
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    public boolean mouseButtonClicked(int button) {
        return glfwGetMouseButton(window, button) == GLFW_PRESS;

    }
    // listeners

    public void addKeyListener(KeyListener keyListener) {
        glfwSetKeyCallback(window, new GLFWKeyCallbackI() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == 1)// key pressed
                    keyListener.keyPressed(key);
                if (action == 0)// key released
                    keyListener.keyReleased(key);
                if (action == 1 && mods > 0)
                    keyListener.modKey(mods, key);
            }

        });
    }

    public void addMouseListener(MouseListener mouseListener) {
        glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallbackI() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (action == 1)// mouse pressed
                    mouseListener.mousePressed(button);
                if (action == 0)
                    mouseListener.mouseReleased(button);
            }

        });
    }

    public void addMouseMotionListener(MouseMotionListener mouseMotionListener) {

        glfwSetCursorPosCallback(window, new GLFWCursorPosCallbackI() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseMotionListener.mouseMove(xpos, ypos);
            }

        });

    }

    public void add(GameObject gameObject) {
        this.Objs.add(gameObject);
    }

    private void checkCollision(GameObject target, int index) {
        if (target.physics == null)
            return;
        if (target.physics.onGround) {
            target.physics.jumbEnable = 0;
        }
        for (int i = 0; i < Objs.size(); i++) {
            GameObject g = Objs.get(i);
            if (g.collision == null || i == index)
                continue;
            if (Collision.CheckBottomCollision(target, g)) {

                target.physics.jumbEnable = 0;

            } else if (Collision.CheckYCollision(target, g)) {
                target.physics.onGround = true;

                target.physics.time.restart();

            }
            boolean c = false;
            if (Collision.CheckRightCollision(target, g)) {
                if (g.physics != null) {
                    g.physics.force.x = (target.physics.speed * target.physics.movement);
                    c = true;

                } else if (target.physics.movement == 1) {
                    target.physics.movement = 0;
                }

            } else if (Collision.CheckLeftCollision(target, g)) {
                if (g.physics != null) {
                    g.physics.force.x = (target.physics.speed * target.physics.movement);
                    c = true;

                } else if (target.physics.movement == -1) {
                    target.physics.movement = 0;
                }

            } else {
                if (g.physics != null) {
                    g.physics.force.x = 0;
                    target.physics.force.x = 0;

                }
            }
            if (c == false && g.physics != null) {
                g.physics.force.x = 0;
                target.physics.force.x = 0;

            }

        }
        target.transform.position.x = target.physics.move(target.transform.position.x);
        target.transform.position.y = target.physics.yMovment(target.transform.position.y);

    }

    public GameObject[] getRenderingObjects() {
        return (GameObject[]) Objs.toArray();
    }

    public void runRenderQueue(Vector3f pos, int range) {
        unrendering = new ArrayList<>();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Objs.size(); i++) {
                    if ((Objs.get(i).transform.position.x < renderPos.x + renderRange
                            && Objs.get(i).transform.position.x > renderPos.x - renderRange)
                            || (Objs.get(i).transform.position.y < renderPos.x + renderRange
                                    && Objs.get(i).transform.position.y > renderPos.y - renderRange)) {
                        unrendering.add(i);

                    }
                }

            }

        });
        thread.start();
    }

    public void renderAll() {
        for (int i = 0; i < Objs.size(); i++) {
            checkCollision(Objs.get(i), i);
            this.Objs.get(i).draw();
        }
    }

    public void renderAll(Vector3f pos, int range) {
        for (int i = 0; i < renderingObjects.size(); i++) {
            checkCollision(this.Objs.get(renderingObjects.get(i)), i);
            this.Objs.get(renderingObjects.get(i)).draw();
        }
        try {
            System.out.println(renderingObjects.size());
            renderPos = new Vector3f(pos);
            renderRange = range;
            if (!thread.isAlive()) {
                renderingObjects = unrendering;
                runRenderQueue(pos, range);

            }

        } catch (Exception exception) {

        }

    }
}
