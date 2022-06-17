import org.joml.Matrix4f;
import static org.lwjgl.opengl.GL20.*;


public class Model extends Matrix4f{
    private float[] vertices;
    private int program ;
    private String name;
    private int model;
    public Model(int program, String name ){
        super();
        this.name = name;
        this.program = program;

        this.model = glGetUniformLocation(program, name);

        vertices = new float[16];
        update();
    }
    private void update(){
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
    
}
