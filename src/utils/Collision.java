package utils;

import org.joml.Vector3f;

import objects.GameObject;

public class Collision  {
    public Vector3f size;
    public Vector3f position;
    
    public Collision(Vector3f size){
        this.size = new Vector3f(size);
        this.position = new Vector3f();
    }
    public static boolean CheckCollision(GameObject one, GameObject two) // AABB - AABB collision
    {

        boolean collisionX = one.collision.position.x + one.collision.size.x >= two.collision.position.x &&
            two.collision.position.x + two.collision.size.x >= one.collision.position.x;
        boolean collisionY = one.collision.position.y + one.collision.size.y >= two.collision.position.y &&
            two.collision.position.y + two.collision.size.y >= one.collision.position.y;
        return collisionX && collisionY;
    }
}
