package utils;

public class Frame {
    public int texture;
    public float interval;
    public Frame(Texture2D texture2d, float interval){
        this.texture = texture2d.getTexture();
        this.interval = interval;
    }
}
