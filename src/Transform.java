import org.joml.Vector3f;

public class Transform {

    public Vector3f position;
    public Vector3f rotation;
    public Vector3f scale;
    public Transform(){
        position = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
        scale = new Vector3f(1,1,1);
    }
}
