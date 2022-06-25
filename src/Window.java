// import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.opengl.GL;

public class Window {

    private int width; 
    private int height;
    private String title;
    private long window;
    private Color BackGroundColor;
    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        BackGroundColor = new Color(120, 125, 145);
        glfwWindowHint(GLFW_VISIBLE, 1);

        window = glfwCreateWindow(width, height , title, 0 ,0);
        EngineController.addWindow(window);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        
		// Make the window visible
		glfwShowWindow(window);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);

        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetCursorPos(window, width/2, height/2);
        

    }
    public void render(){
        glClearColor((float)BackGroundColor.r/255, (float)BackGroundColor.g/255, (float)BackGroundColor.b/255, 1.0f);
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    }
    public void pollEvent(){
        glfwSwapBuffers(window);
        glfwPollEvents();
    }
    public void cleanUp(){
        glfwDestroyWindow(window);
        glfwTerminate();
    }
    public boolean isRunning(){
        return glfwWindowShouldClose(window);
    }
    public long getWindow(){
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


    public boolean keyPressed(int key){
        return glfwGetKey(window, key) == GLFW_PRESS;
    }
    public boolean mouseButtonClicked(int button){
        return glfwGetMouseButton(window, button) == GLFW_PRESS;
         
    }
    // listeners  


    public void addKeyListener(KeyListener keyListener){
        glfwSetKeyCallback(window,new  GLFWKeyCallbackI(){
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if(action == 1)// key pressed
                    keyListener.keyPressed(key);
                if(action == 0)//key released
                    keyListener.keyReleased(key);
                if (action == 1 && mods > 0 )
                    keyListener.modKey(mods, key);
            }

        });
    }
    public void addMouseListener(MouseListener mouseListener){
        glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallbackI() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if(action == 1 )// mouse pressed 
                    mouseListener.mousePressed(button);
                if(action == 0)
                    mouseListener.mouseReleased(button);
            }
            
        });
    }
    public void addMouseMotionListener(MouseMotionListener mouseMotionListener){

        glfwSetCursorPosCallback(window, new GLFWCursorPosCallbackI(){
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseMotionListener.mouseMove(xpos, ypos);
            }

        });
  
    }
}
