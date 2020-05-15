package com.originals.flappybird;

/**
 * Created by User on 4/9/2020.
 */
public class BackgroundImage {

    private int backgroundImageX,backgroundImageY,backgroundImageVelocity;;
    public  BackgroundImage(){
        backgroundImageX=0;
        backgroundImageY=0;
        backgroundImageVelocity=3;
    }
    // getter method for getting the x coodinate
    public int getX(){
        return backgroundImageX;
    }

    // getter method for getting the y coordinate
    public  int getY()
    {
        return backgroundImageY;
    }

    // setter function for setting the x coordinate
    public void setX(int backgroundImageX){
        this.backgroundImageX = backgroundImageX;
    }

    // setter function for setting the y coordinate
    public void setY(int backgroundImageY){
        this.backgroundImageY = backgroundImageY;
    }

    //getter method for getting the velocity
    public int getVelocity(){
        return  backgroundImageVelocity;
    }

}
