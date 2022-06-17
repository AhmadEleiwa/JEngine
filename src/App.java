import static org.lwjgl.glfw.GLFW.*;
import java.util.Random;
import org.joml.Vector3f;


public class App{
    public static void main(String[] args) {
        new App().app();
    }

    void app(){
        if(!glfwInit()){
            System.out.println("Failed to init glfw");
        } 
        
        int winWidth = 1280 , winHeight =  720;
        String title = "My title";
        Window window = new Window(winWidth, winHeight, title);
 
        Texture2D[] tex = new Texture2D[3];
        tex[0] = new Texture2D("src/image.jpg");
        tex[1] = new Texture2D("src/dirt_0.png");
        tex[2] = new Texture2D("src/grass.jpg");


        Projection proj = new Projection( (float)winWidth/(float)winHeight);
    
        View view = new View();

        view.translate(new Vector3f(0,-1,-4));

   
        Random rand = new Random();
        Cube[] models = new Cube[1600];
        int[] ls = new int[1600];
        int c=0;
        for(int i=0; i<40; i++){
            for(int j=0; j<40; j++){
                int y  = 1;
                int r = rand.nextInt(30);
                if(r  > 0)
                    y = 0;
                int rc  = rand.nextInt(20);
                if(rc > 18)
                    ls[c] = 0;
                else if(rc >12)
                    ls[c] = 1;
                else
                    ls[c] = 2;
                models[c] = new Cube();
                models[c].loadTexture(tex[ls[c]]);
                models[c++].model.translate(new Vector3f(-i+2, y , -j+2)); 
            }
        }

        view.lookAt(new Vector3f(0,2,3) ,new Vector3f(0,1,0), new Vector3f(0,1,0));


        while(!window.isRunning()){
    
            window.render();

            EngineController.useDefualtProgram();

            view.sendMatrix();
            proj.sendMatrix();;

            for(int i=0;i<models.length;i++){
                models[i].draw();
            }
            window.pollEvent();
   
        }        
        
        window.cleanUp();
    }
}