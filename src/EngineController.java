import java.util.ArrayList;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;



public class EngineController {
    private static ArrayList<BufferLoader> loaders =new ArrayList<>();
    private static ArrayList<Texture2D> texture2ds = new ArrayList<>();
    private static ArrayList<ShaderProgram> programs = new ArrayList<>();
    private static ArrayList<Long> windows = new ArrayList<>();

    public static  void addWindow(long window){
        windows.add(window);
    }
    public static long getWindow(int index){
        return windows.get(index);
    }
    public static int[] pushBuffer(String name){
        for(int i=0; i<loaders.size(); i++){
            if(loaders.get(i).getName() == name){
                return new int[]{loaders.get(i).getVAO(),loaders.get(i).getVBO()};
            }
        }
        BufferLoader l = new BufferLoader(name,glGenVertexArrays(), glGenBuffers());
        loaders.add(l);
        return new int[]{l.getVAO(), l.getVBO()};

    }
    public static int loadTexture(String path){
        for(int i=0; i<texture2ds.size(); i++){
            if(texture2ds.get(i).getSource() == path){
                return texture2ds.get(i).getTexture();
            }
        }
        Texture2D l = new Texture2D(path);
        texture2ds.add(l);
        return l.getTexture();
    }

    public static int pushPorgram(String name){
        if(programs.size() == 0){
            ShaderProgram defualtProgram = new ShaderProgram("defualt");
            defualtProgram.add(new Shader(GL_VERTEX_SHADER, "src/vertexShaderSource.vs"));
            defualtProgram.add(new Shader(GL_FRAGMENT_SHADER, "src/fragShaderSource.fs"));
            defualtProgram.link();
            programs.add(defualtProgram);
        }
        for(int i=0; i<programs.size(); i++){
            if(programs.get(i).getName() == name){
                return programs.get(i).getProgram();
            }
        }
        ShaderProgram program = new ShaderProgram(name);
        return program.getProgram();
    }
    public static void useDefualtProgram(){
        glUseProgram(programs.get(0).getProgram());
    }
    public static void useProgram(String name){
        for(int i=0; i<programs.size(); i++){
            if(programs.get(i).getName() == name){
                glUseProgram(programs.get(i).getProgram());
                return;
            }
        }
    }
}
