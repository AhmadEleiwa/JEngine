// import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;



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
        BackGroundColor = new Color(155, 155, 155);
        glfwWindowHint(GLFW_VISIBLE, 1);

        window = glfwCreateWindow(width, height , title, 0 ,0);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);

    }
    public void render(){
        glClearColor((float)BackGroundColor.r, (float)BackGroundColor.g/255, (float)BackGroundColor.b/255, 1.0f);
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
}
