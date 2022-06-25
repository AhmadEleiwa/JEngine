
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


import org.joml.Vector3f;



public class Cube {


    public Mesh model;
    private int texture;
    public String name = "cube";
    public Transform transform;
    public Color color;
    private final float[] verticesCube={
        -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
        0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
        0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
        0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
        -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
        -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
        0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
        -0.5f, 0.5f, 0.5f, 0.0f, 1.0f,
        -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
        -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
        -0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
        -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
        -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
        0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
        0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
        0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
        0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
        0.5f, -0.5f, -0.5f, 1.0f, 1.0f,
        0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
        0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
        -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
        -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
        -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
        0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
        0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
        -0.5f, 0.5f, 0.5f, 0.0f, 0.0f,
        -0.5f, 0.5f, -0.5f, 0.0f, 1.0f
        };

    public Cube(){    
           
            model = new Mesh(EngineController.pushPorgram("defualt"), "model");
            // buffer = EngineController.pushBuffer("cube");
            model.setBuffer(EngineController.pushBuffer("cube"));
            transform = new Transform();
            color = new Color(220, 220, 220);

            glBindVertexArray(model.getBuffer()[0]);
            glBindBuffer(GL_ARRAY_BUFFER, model.getBuffer()[1]);
            glBufferData(GL_ARRAY_BUFFER, verticesCube, GL_STATIC_DRAW);

            glVertexAttribPointer(0, 3, GL_FLOAT, false, 5*4, 0);
            glEnableVertexAttribArray(0);
    
            glVertexAttribPointer(2, 2, GL_FLOAT, false, 5*4,  3 * 4);
            glEnableVertexAttribArray(2); 
            texture = 0;


        
    }

    public void loadTexture(String src){
        this.texture =  EngineController.loadTexture(src);
    }
    public void loadTexture(Texture2D texture){
        this.texture = EngineController.loadTexture(texture.getSource());
    }
    public void update(){
        model.setTranslation(transform.position);
        model.setRotationXYZ(transform.rotation.x, transform.rotation.y, transform.rotation.z);
        // model.scale(new Vector3f(transform.scale));


        glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "colorFromIn"),0,0);

        int loc = glGetUniformLocation(EngineController.pushPorgram("defualt"), "color");
        glUniform3f(loc,(float)color.r/255, (float)color.g/255, (float)color.b/255);
        if(texture == 0)
            glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "textureEnable"),0,0);
        else{
            glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "textureEnable"),1, 0);
            glBindTexture(GL_TEXTURE_2D, texture);
        }
        
    }

    public void draw(){
        update();
        model.sendMatrix();

        glBindVertexArray(model.getBuffer()[0]);
        glDrawArrays(GL_TRIANGLES, 0, 36);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
