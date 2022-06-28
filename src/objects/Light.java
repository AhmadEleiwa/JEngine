package objects;



import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import Engine.EngineController;
import utils.Color;
import utils.Transform;
import org.joml.Vector3f;

public class Light extends GameObject{
    private final float[] verticesCube={
        // positions // normals // texture coords
        -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
        0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f,
        0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 1.0f, 1.0f,
        0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 1.0f, 1.0f,
        -0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
        -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
        0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
        -0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f,
        -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
        -0.5f, 0.5f, -0.5f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
        -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.5f, -0.5f, 0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
        -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
        0.5f, 0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
        0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f,
        0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f,
        0.5f, -0.5f, 0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f,
        0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f, 1.0f, 1.0f,
        0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f,
        0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f,
        -0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f,
        -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
        0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f,
        0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
        -0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
        -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f
        };
    public Light(){    
           
            model = new Mesh(EngineController.pushPorgram("defualt"), "model");
            // buffer = EngineController.pushBuffer("cube");
            model.setBuffer(EngineController.pushBuffer("light"));
            transform = new Transform();
            color = new Color(220, 220, 220);

            glBindVertexArray(model.getBuffer()[0]);
            glBindBuffer(GL_ARRAY_BUFFER, model.getBuffer()[1]);
            glBufferData(GL_ARRAY_BUFFER, verticesCube, GL_STATIC_DRAW);

            glVertexAttribPointer(0, 3, GL_FLOAT, false, 8*4, 0);
            glEnableVertexAttribArray(0);
    
            // glVertexAttribPointer(2, 2, GL_FLOAT, false, 8*4,  6 * 4);
            // glEnableVertexAttribArray(2); 
            
            glVertexAttribPointer(3, 3, GL_FLOAT, false, 8*4, 3*4);
            glEnableVertexAttribArray(3);
    

            texture = 0;

    }
    @Override
    public void update() {
        model.setTranslation(transform.position);
        model.setRotationXYZ(transform.rotation.x, transform.rotation.y, transform.rotation.z);
        model.scale(new Vector3f(transform.scale));

        glUniform3f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "lightPos"), transform.position.x,transform.position.y,transform.position.z);

        glUniform3f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "lightColor"), (float)color.r/255, (float)color.g/255, (float)color.b/255);

        glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "colorFromIn"),0,0);

        int loc = glGetUniformLocation(EngineController.pushPorgram("defualt"), "color");
        glUniform3f(loc,(float)color.r/255, (float)color.g/255, (float)color.b/255);
        if(texture == 0)
            glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "textureEnable"),0,0);
        else{
            glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "textureEnable"),1, 0);
            glBindTexture(GL_TEXTURE_2D, texture);
        }
        glUniform1f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "lightOn"),0);

    }
    

    public void draw(){
        update();
        model.sendMatrix();

        glBindVertexArray(model.getBuffer()[0]);
        glDrawArrays(GL_TRIANGLES, 0, 36);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
