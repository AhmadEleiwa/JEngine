import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;

import org.lwjgl.stb.STBImage;
;

public class Texture2D {
    private int texture ;   
    private String src;
    public Texture2D(){

    };
    public Texture2D(String src ){
        this.texture  = glGenTextures();
        this.src = src;

        glBindTexture(GL_TEXTURE_2D, texture);
        setDefualt();
        int[] width =new int[1], height = new int[1], nrChannels = new int[1];
        ByteBuffer data = STBImage.stbi_load(src, width, height,nrChannels, 0);
        
        if (data != null)
        {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width[0], height[0], 0, GL_RGB, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
        }

        STBImage.stbi_image_free(data);

    }
    public void setDefualt(){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    }
    public void setSetting(int target, int pname,int param){
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexParameteri(target, pname, param);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    public int getTexture() {
        return texture;
    }
    public void loadTexture(String src) {
        this.src = src;
        glBindTexture(GL_TEXTURE_2D, texture);
        setDefualt();
        int[] width =new int[1], height = new int[1], nrChannels = new int[1];
        ByteBuffer data = STBImage.stbi_load(src, width, height,nrChannels, 0);
        
        if (data != null)
        {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width[0], height[0], 0, GL_RGB, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
        }
        glBindTexture(GL_TEXTURE_2D, 0);
        STBImage.stbi_image_free(data);
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
}
