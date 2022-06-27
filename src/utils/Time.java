package utils;
import org.lwjgl.glfw.GLFW;



public class Time {
    private  double oldTime  = 0;

    public Time(){
        oldTime = GLFW.glfwGetTime();
        
    }
    public void restart(){
        oldTime = GLFW.glfwGetTime();
    }
    public double getTime(){
        return GLFW.glfwGetTime() - oldTime;
    }
    public double setTimer(double time){
        if(getTime() > time){
            return 0;
        }
        return time -  getTime();
    }



    public static double deltaTime(){
        return GLFW.glfwGetTime();
    }

}