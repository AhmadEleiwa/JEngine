package objects;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import Engine.EngineController;
import utils.Color;
import utils.Transform;





public class Rectangle extends GameObject{
    private final float[] verteics={
        -0.5f, -0.5f, 0f, 0.0f, 0.0f,
        0.5f, -0.5f, 0f, -1.0f, 0.0f,
        0.5f, 0.5f, 0f, -1.0f, -1.0f,
        -0.5f, 0.5f, 0, 0f, -1.0f,
    };
    public Rectangle(){    
           
        model = new Mesh(EngineController.pushPorgram("defualt"), "model");
        model.setBuffer(EngineController.pushBuffer("rect"));
        transform = new Transform();
        color = new Color(220, 220, 220);

        glBindVertexArray(model.getBuffer()[0]);
        glBindBuffer(GL_ARRAY_BUFFER, model.getBuffer()[1]);
        glBufferData(GL_ARRAY_BUFFER, verteics, GL_STATIC_DRAW);
        

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5*4, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(2, 2, GL_FLOAT, false, 5*4,  3 * 4);
        glEnableVertexAttribArray(2); 
        texture = 0;
    }



    public void draw(){
        update();
        model.sendMatrix();

        glBindVertexArray(model.getBuffer()[0]);
        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

}