package objects;


import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


import Engine.EngineController;
import utils.Color;
import utils.Transform;


public class Circle extends GameObject{
    
    private float[] verteics;
    private int edges = 24; 

    public void loadVerteces(){
        verteics = new float[edges*5];
        int c = 360/edges;
        int row = 0;
        for(int i=0; i<edges; i++){
            verteics[row] = 0.5f*(float)Math.cos(Math.toRadians(c*i));
            verteics[row+1] =0.5f* (float)Math.sin(Math.toRadians(c*i));
            verteics[row+2] = 0;

            row += 5;

        }

    }
    public Circle(){
        model = new Mesh(EngineController.pushPorgram("defualt"), "model");
        model.setBuffer(EngineController.pushBuffer("circle"));
        transform = new Transform();
        color = new Color(220, 220, 220);
        loadVerteces();

        glBindVertexArray(model.getBuffer()[0]);
        glBindBuffer(GL_ARRAY_BUFFER, model.getBuffer()[1]);
        glBufferData(GL_ARRAY_BUFFER, verteics, GL_STATIC_DRAW);
        

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5*4, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(2, 3, GL_FLOAT, false, 5*4, 0);
        glEnableVertexAttribArray(2);

    }
    public Circle(int edges){
            model = new Mesh(EngineController.pushPorgram("defualt"), "model");
            model.setBuffer(EngineController.pushBuffer("circle"));
            transform = new Transform();
            color = new Color(220, 220, 220);
            this.edges = edges;
            loadVerteces();

            glBindVertexArray(model.getBuffer()[0]);
            glBindBuffer(GL_ARRAY_BUFFER, model.getBuffer()[1]);
            glBufferData(GL_ARRAY_BUFFER, verteics, GL_STATIC_DRAW);
            

            glVertexAttribPointer(0, 3, GL_FLOAT, false, 3*4, 0);
            glEnableVertexAttribArray(0);

    }

    public void draw(){
        update();
        model.sendMatrix();

        glBindVertexArray(model.getBuffer()[0]);
        glDrawArrays(GL_TRIANGLE_FAN, 0, edges);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
