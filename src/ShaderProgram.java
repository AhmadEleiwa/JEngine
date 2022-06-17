
import static org.lwjgl.opengl.GL20.*;


public class ShaderProgram {
    private int program;
    private String name;
    public ShaderProgram(String name){
        this.name = name;
        program = glCreateProgram();
    }
    public void add(Shader shader){
        glAttachShader(program, shader.getShader());
    }
    public void link(){
        glLinkProgram(program);
    }
    public int getProgram() {
        return program;
    }
    public String getName() {
        return name;
    }
    
}
