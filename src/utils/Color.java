package utils;

public class Color {
    public int r;
    public int g;
    public int b;
    public float a;
    public Color(int r, int g, int b, Float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        check();
    }
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1;
        check();
    }
    public void setColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1;
        check();
    }
    private void check(){
        if(r>255) r=255;
        if(b>255) b=255;
        if(g>255) g=255;
    }
}
