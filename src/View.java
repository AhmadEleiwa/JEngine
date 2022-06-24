import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;


public class View{

    private static Vector3f addVecs(Vector3f v1 , Vector3f v2){
        Vector3f res = new Vector3f();
        res.x  =  v1.x+v2.x;
        res.y = v1.y+v2.y;
        res.z = v1.z+v2.z;

        return res;
    }

    public Mesh model;
    public Vector3f cameraPos;
    public Vector3f cameraFront;
    public Vector3f cameraUp;

    public Vector2f sensitivity;
    public float mouseSpeed;
    public Vector3f oldPos;

    private boolean first;

    public View(){

        model = new Mesh(EngineController.pushPorgram("defualt"), "view");
        model.setBuffer(EngineController.pushBuffer("view"));

        cameraPos = new Vector3f(0,0,3);
        cameraFront = new Vector3f(0,0,-1);
        cameraUp = new Vector3f(0,1,0);

        sensitivity = new Vector2f(0.1f,0.1f);
        oldPos = new Vector3f(0,0,0);

        mouseSpeed  = 0.1f;
        first = true;

        model.update();
    }

    public void update(){
        double x = Input.getMouse()[0];
        double y = Input.getMouse()[1];
        
        if(first){
            oldPos.x = (float)x;
            oldPos.y = (float)y;
            first= false;
        }

        float dx = (float) (x - oldPos.x);
        float dy = (float) (y - oldPos.y);
        oldPos.x = dx*sensitivity.x;
        oldPos.y = dy*sensitivity.y;
        if(oldPos.y > 89.0f)
            oldPos.y = 89.0f;
        if(oldPos.y < -89.0f)
            oldPos.y = -89.0f;

        cameraFront.x =  (float)(Math.cos(Math.toRadians(oldPos.x)) * Math.cos(Math.toRadians(oldPos.y))); 
        cameraFront.y =  -(float) Math.sin(Math.toRadians(oldPos.y)); 
        cameraFront.z =  (float)(Math.sin(Math.toRadians(oldPos.x)) * Math.cos(Math.toRadians(oldPos.y))); 


        model.identity();
        model.lookAt(cameraPos, View.addVecs(cameraPos,cameraFront), cameraUp);

        if(Input.getKetDown(GLFW_KEY_W)){
            cameraPos.add(new Vector3f(cameraFront.x * mouseSpeed,cameraFront.y * mouseSpeed,cameraFront.z * mouseSpeed));
    
        }
        if( Input.getKetDown(GLFW_KEY_S)){
            cameraPos.sub(new Vector3f(cameraFront.x * mouseSpeed,cameraFront.y * mouseSpeed,cameraFront.z * mouseSpeed));

        }
        if(Input.getKetDown(GLFW_KEY_A)){
            Vector3f veci = new Vector3f(cameraUp);
            veci.cross(cameraFront);
            cameraPos.add(veci.x * mouseSpeed, veci.y * mouseSpeed, veci.z * mouseSpeed);
        }
        if(Input.getKetDown(GLFW_KEY_D)){
            Vector3f veci = new Vector3f(cameraUp);
            veci.cross(cameraFront);
            cameraPos.sub(veci.x * mouseSpeed, veci.y * mouseSpeed, veci.z * mouseSpeed);
        }  

    }

    public float[] get() {

        return model.get();
    }
    public Mesh getMesh(){
        return model;
    }
    public void render(){
        update();
        model.sendMatrix();;
    }
}
