package objects;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import Engine.EngineController;



public class Lines {
    
    private int[] buffer= new int[2];
    public Mesh model;
    public String name = "lines";

    private final float[] verticesCube={
        0,0,-5000,1,0,0,
        0,0,5000, 1,0,0,
        0,-5000,0,0,1,0,
        0,5000,0, 0,1,0,
        -5000,0,0,0,0,1,
        5000,0,0, 0,0,1
        };

    public Lines(){    

            model = new Mesh(EngineController.pushPorgram("defualt"), "model");

            buffer = EngineController.pushBuffer("lines");
            
            glBindVertexArray(buffer[0]);
            glBindBuffer(GL_ARRAY_BUFFER, buffer[1]);
            glBufferData(GL_ARRAY_BUFFER, verticesCube, GL_STATIC_DRAW);

            glVertexAttribPointer(0, 3, GL_FLOAT, false, 6*4, 0);
            glEnableVertexAttribArray(0);


            glVertexAttribPointer(1, 3, GL_FLOAT, false, 6*4, 3*4);
            glEnableVertexAttribArray(1);
           
    }
    private void update(){
        
        glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "colorFromIn"),1,0);
        glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "textureEnable"),0, 0);

    }
    public void draw(){
        model.sendMatrix();
        update();

        glBindVertexArray(buffer[0]);
        glDrawArrays(GL_LINES, 0, 6);
        
    }
}
