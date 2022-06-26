package objects;
import org.joml.Matrix4f;
import static org.lwjgl.opengl.GL20.*;


public class Mesh extends Matrix4f{
    private float[] vertices;
    private int program ;
    private String name;
    private int model;
    private int[] buffer = new int[3];
    public Mesh(int program, String name ){
        super();
        this.name = name;
        this.program = program;

        this.model = glGetUniformLocation(program, name);

        vertices = new float[16];
        update();
    }
    public void update(){
        get(vertices);
    }
    public float[] get() {
        update();
        return vertices;
    }
    public int getProgram() {
        return program;
    }
    public String getName() {
        return name;
    };
    public void sendMatrix(){
        update();
        glUniformMatrix4fv(this.model, false, this.vertices);
    }

    public void setBuffer(int[] buffer) {
        this.buffer[0] = buffer[0];
        this.buffer[1] = buffer[1];
        this.buffer[2] = buffer[2];
        
    }
    public int[] getBuffer(){
        return buffer;
    }
}
