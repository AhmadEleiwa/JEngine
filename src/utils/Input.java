package utils;
// import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import Engine.EngineController;

public class Input {
    private static double[] pos = new double[2];

    public static double[] getMouse(){
        
        
        glfwSetCursorPosCallback(EngineController.getWindow(0), new GLFWCursorPosCallbackI(){
            
            @Override
            public void invoke(long window, double xpos, double ypos) {
                pos[0] = xpos;
                pos[1] = ypos;
            }

        });
        return pos;
    }
    public static boolean getKeyDown(int key){
        return glfwGetKey(EngineController.getWindow(0), key) == GLFW_PRESS;
    }
    public static boolean getKeyUp(int key){
        return glfwGetKey(EngineController.getWindow(0), key) == GLFW_RELEASE;
    }

    public static boolean getMouseButtonDown(int key){
        return glfwGetMouseButton(EngineController.getWindow(0), key) == GLFW_PRESS;
    }

    public static boolean getMouseButtonUP(int key){
        return glfwGetMouseButton(EngineController.getWindow(0), key) == GLFW_RELEASE;
        // glfwCreateCursor(new GLFWImage(""), xhot, yhot)
    }


}
