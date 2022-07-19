package utils;

import java.util.ArrayList;

public class Animation {
    private ArrayList<Frame> frames;
    private Time time;
    private float totalInterval = 0;
    private int currentFrame = 0;
    public Animation(){
        this.frames = new ArrayList<>();
        time = new Time();
    }
    public void addFrame(Frame frame) {
        this.frames.add(frame);
        totalInterval += frame.interval;
    }
    
    public Frame getFrame(int index){
        return frames.get(index);
    }
    public void reset(){
        currentFrame = 0;
        time.restart();
    }
    public Frame animate(){
 
        if(time.getTime() >= frames.get(currentFrame).interval){
            currentFrame++;
        }
        if(time.getTime() > totalInterval){
            time.restart();
        }
  
        if(currentFrame >= frames.size()){
            currentFrame = 0;
            time.restart();
        }
        return frames.get(currentFrame);

    }
}
