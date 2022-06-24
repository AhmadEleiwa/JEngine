// import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class Input {
    private static double[] pos = new double[2];

    public static double[] getMouse(){
        
        
        glfwSetCursorPosCallback(EngineController.getWindow(0), new GLFWCursorPosCallbackI(){
            
            @Override
            public void invoke(long window, double xpos, double ypos) {
                pos[0] = xpos;
                pos[1] = ypos;
                System.out.println(pos[0]);
            }

        });
        return pos;
    }
    public static boolean getKetDown(int key){
        return glfwGetKey(EngineController.getWindow(0), key) == GLFW_PRESS;
    }
    public static boolean getKetUp(int key){
        return glfwGetKey(EngineController.getWindow(0), key) == GLFW_RELEASE;
    }

}
