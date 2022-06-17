import static org.lwjgl.opengl.GL20.*;

import java.io.File;
import java.util.Scanner;

public class Shader {
    private int type;
    private String path;
    private String source;
    private int shader;
    public Shader(int type, String path) {
        this.type = type;
        this.path = path;
        this.shader = glCreateShader(type);

        readFromFile(path);
        glShaderSource(shader, source);
        glCompileShader(shader);
    }


    private void readFromFile(String path){
        String str  =  "";
        try{
            Scanner scan = new Scanner(new File(path));
            while(scan.hasNextLine()){
                str += scan.nextLine()+'\n';
            }
            source = str;
            scan.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


    public int getType() {
        return type;
    }


    public String getPath() {
        return path;
    }


    public String getSource() {
        return source;
    }


    public int getShader() {
        return shader;
    }
}
