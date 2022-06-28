package objects;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.joml.Vector3f;

import utils.Transform;

import Engine.EngineController;
import utils.Color;



public class Lines extends GameObject{
    
    private final float[] verteics={
        0,0,-5000,1,0,0,
        0,0,5000, 1,0,0,
        0,-5000,0,0,1,0,
        0,5000,0, 0,1,0,
        -5000,0,0,0,0,1,
        5000,0,0, 0,0,1
        };

    public Lines(){    
          
        model = new Mesh(EngineController.pushPorgram("defualt"), "model");
        model.setBuffer(EngineController.pushBuffer("lines"));
        transform = new Transform();
        color = new Color(10, 220, 220);

        glBindVertexArray(model.getBuffer()[0]);
        glBindBuffer(GL_ARRAY_BUFFER, model.getBuffer()[1]);
        glBufferData(GL_ARRAY_BUFFER, verteics, GL_STATIC_DRAW);
        

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6*4, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6*4,  3 * 4);
        glEnableVertexAttribArray(1); 


        texture = 0;
           
    }
    // private void update(){
        
    //     glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "colorFromIn"),1,0);
    //     glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "textureEnable"),0, 0);

    // }

    @Override
    public void update() {
        model.setTranslation(transform.position);
        model.setRotationXYZ(transform.rotation.x, transform.rotation.y, transform.rotation.z);
        model.scale(new Vector3f(transform.scale));
        glUniform1f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "lightOn"),0);
        glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "colorFromIn"),1,0);
        glUniform1f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "a"),color.a);
        glUniform2f(glGetUniformLocation(EngineController.pushPorgram("defualt"), "textureEnable"),0,0);


    }

    public void draw(){
        model.sendMatrix();
        update();

        glBindVertexArray(model.getBuffer()[0]);
        glDrawArrays(GL_LINES, 0, 6);
        
    }
}
