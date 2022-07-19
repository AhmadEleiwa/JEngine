package objects;


import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


import Engine.EngineController;
import utils.Color;
import utils.Transform;


public class CosShape extends GameObject {

        private float[] verteics;
        private int edges = 360*500; 
    
        public void loadVerteces(){
            verteics = new float[edges*5];
            int c = 360/edges;
            int row = 0;
            for(int i=-edges/2; i<edges/2; i++){
                verteics[row] = (float)i*0.008f;
                verteics[row+1] =1.5f* (float)Math.sin(Math.toRadians(i));
                verteics[row+2] = 0;
    
                row += 5;
    
            }
    
    
        }
        public CosShape(){
            model = new Mesh(EngineController.pushPorgram("defualt"), "model");
            model.setBuffer(EngineController.pushBuffer("cos"));
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

    
        public void draw(){
            update();
            model.sendMatrix();
    
            glBindVertexArray(model.getBuffer()[0]);
            glDrawArrays(GL_LINE_STRIP, 0, edges);
            glBindTexture(GL_TEXTURE_2D, 0);
        }
}
