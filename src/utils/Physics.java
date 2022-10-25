package utils;

import org.joml.Vector3f;

public class Physics{
    public int jumbEnable;
    public float  speed =0.1f;
    public float  movement = 0;
    public float jumbSpeed = 0.1f;
    public float gravity = 0.12f;
    public float mass =  1f;
    public Vector3f velocity ;
    public boolean onJumb = false;
    public boolean isTouch = false;
    public Vector3f  force;
    public Time time;
    public int dir;
   


    public boolean onGround = false;
    public Physics(){
        time = new Time();
        force = new Vector3f();
        velocity = new Vector3f();
    }

    public float move(float v){
        velocity.x =  (movement * speed) + force.x;
        return v  + velocity.x;

    }
    public float yMovment(float y){
        return y + (float)((jumbSpeed * jumbEnable) + (-gravity * time.getTime()))+ force.y;

    }
    public void jumb(){
        jumbEnable = 1;
    }
    public void onGround(){
        jumbEnable = 1;
        onGround = false;
    }



}