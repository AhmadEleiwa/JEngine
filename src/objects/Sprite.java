package objects;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.joml.Vector3f;

import Engine.EngineController;
import utils.Color;
import utils.Texture2D;
import utils.Transform;



public class Sprite extends GameObject {
    public Sprite(Texture2D tex){
        texture = EngineController.loadTexture(tex.getSource());
        float wbyh =(float)tex.getWidth() / (float)tex.getHeight(); 
        float hbyw =(float)tex.getHeight() / (float)tex.getWidth() ; 
        float[] verteics={
            -0.5f*wbyh, -0.5f*hbyw, 0f, 0.0f, 0.0f,
            0.5f*wbyh, -0.5f*hbyw, 0f, -1.0f, 0.0f,
            0.5f*wbyh, 0.5f*hbyw, 0f,  -1.0f, -1.0f,
            0.5f*wbyh, 0.5f*hbyw, 0f,  -1.0f, -1.0f,
            -0.5f*wbyh, 0.5f*hbyw, 0f,  0.0f, -1.0f,
            -0.5f*wbyh, -0.5f*hbyw, 0f, 0.0f, 0.0f,
        };
        this.name = tex.getSource();
        model = new Mesh(EngineController.pushPorgram("defualt"), "model");
        model.setBuffer(EngineController.pushBuffer(name));
        transform = new Transform();
        color = new Color(220, 220, 220);
        this.transform.scale = new Vector3f(hbyw, wbyh, 1);

        glBindVertexArray(model.getBuffer()[0]);
        glBindBuffer(GL_ARRAY_BUFFER, model.getBuffer()[1]);
        glBufferData(GL_ARRAY_BUFFER, verteics, GL_STATIC_DRAW);
        

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5*4, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(2, 2, GL_FLOAT, false, 5*4,  3 * 4);
        glEnableVertexAttribArray(2); 
    }

    public void draw(){
        update();
        model.sendMatrix();

        glBindVertexArray(model.getBuffer()[0]);
        glDrawArrays(GL_TRIANGLES, 0, 6);
        glBindTexture(GL_TEXTURE_2D, texture);
    }

}
