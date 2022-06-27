package utils;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL30.*;


import java.nio.ByteBuffer;


import org.lwjgl.stb.STBImage;

    /**
    * <p> Texture2D is Storage of image from out side \ntake source and load the image </p>
    */
 

public class Texture2D {

    private int texture ;   
    private String src;
    private int width;
    private int height;
    private int channels_in_file;
 
    public Texture2D(){

    };
    public Texture2D(String src ){ 
   
        this.src = src;
        this.texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        setDefualt();
        int []width= new int[1];
        int []height= new int[1];
        int []channels_in_file= new int[1];
        ByteBuffer data  = STBImage.stbi_load(src, width, height, channels_in_file, 0);
        int format = GL_RGB;

        if(channels_in_file[0] == 4){
            format = GL_RGBA;
        }
        if(channels_in_file[0] == 3){
            format = GL_RGB;
        }
        if (data != null)
        {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width[0], height[0], 0, format, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
        }
        glBindTexture(GL_TEXTURE_2D, 0);

        this.width = width[0];
        this.height = height[0];
        this.channels_in_file = channels_in_file[0];
    }
    public void setDefualt(){
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND); // enable blending 
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);// for alpha 
        // glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);

    }
    public void setSetting(int target, int pname,int param){
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexParameteri(target, pname, param);
        glBindTexture(GL_TEXTURE_2D, 0);
        glEnable(GL_TEXTURE_2D);
    }
    public int getTexture() {
        return texture;
    }
   
    public String getSource() {
        return src;
    }
    public void setSource(String src) {
        this.src = src;
    }
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, texture);
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getFormat() {
        return channels_in_file;
    }
}
