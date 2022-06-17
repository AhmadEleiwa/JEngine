import org.joml.Matrix4f;
import static org.lwjgl.opengl.GL20.*;


public class Projection extends Matrix4f {
    private float[] vertices;
    private int program ;
    private String name;
    private int model;
    public Projection( float aspect){
        super();
        this.name = "projection";
        this.program = EngineController.pushPorgram("defualt");

        this.model = glGetUniformLocation(program, name);
        perspective((float)Math.toRadians(45f), aspect,0.1f, 100f);
        vertices = new float[16];
        update();
    }
    public Projection(int program, String name , float aspect){
        super();
        this.name = name;
        this.program = program;

        this.model = glGetUniformLocation(program, name);
        perspective((float)Math.toRadians(45f), aspect,0.1f, 100f);
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
