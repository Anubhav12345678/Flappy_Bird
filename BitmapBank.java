package com.originals.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by User on 4/8/2020.
 */
public class BitmapBank {

    Bitmap background,background2,background3;
    Bitmap[] bird;
    Bitmap tubeTop, tubeBottom;
    Bitmap redTubeTop, redTubeBottom;

    public BitmapBank(Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background2 = BitmapFactory.decodeResource(res, R.drawable.background2);
        background3 = BitmapFactory.decodeResource(res, R.drawable.background3);
        background = scaleImage(background);
        background2 = scaleImage(background2);
        background3 = scaleImage(background3);
        bird = new Bitmap[4];
        bird[0] = BitmapFactory.decodeResource(res, R.drawable.bird_frame1);
        bird[1] = BitmapFactory.decodeResource(res, R.drawable.bird_frame2);
        bird[2] = BitmapFactory.decodeResource(res, R.drawable.bird_frame3);
        bird[3] = BitmapFactory.decodeResource(res, R.drawable.bird_frame4);
        tubeTop = BitmapFactory.decodeResource(res, R.drawable.tube_top);
        tubeBottom = BitmapFactory.decodeResource(res, R.drawable.tube_bottom);
        redTubeTop = BitmapFactory.decodeResource(res, R.drawable.red_tube_top);
        redTubeBottom = BitmapFactory.decodeResource(res, R.drawable.red_tube_bottom);
    }

    // Return Red Tube-Top Bitmap
    public Bitmap getRedTubeTop(){
        return redTubeTop;
    }

    // Return Red Tube-Bottom Bitmap
    public Bitmap getRedTubeBottom(){
        return redTubeBottom;
    }

    // Return Tube-Top Bitmap
    public Bitmap getTubeTop(){
        return tubeTop;
    }

    // Return Tube-Bottom Bitmap
    public Bitmap getTubeBottom(){
        return tubeBottom;
    }

    //Return Tube-width
    public int getTubeWidth(){
        return tubeTop.getWidth();
    }

    //Return Tube-height
    public int getTubeHeight(){
        return tubeTop.getHeight();
    }

    // Return bird bitmap of frame
    public Bitmap getBird(int frame){
        return bird[frame];
    }

    public int getBirdWidth(){
        return bird[0].getWidth();
    }

    public int getBirdHeight(){
        return bird[0].getHeight();
    }




    //return background bitmap
        public Bitmap getBackground(){
        return background;
    }

       public int getBackgroundWidth(){
           return background.getWidth();
       }

       public int getBackgroundHeight(){
           return background.getHeight();
       }


    //Return background2 bitmap
    public Bitmap getBackground2(){
        return background2;
    }

    //Return background2 width
    public int getBackground2Width(){
        return background2.getWidth();
    }

    //Return background2 height
    public int getBackground2Height(){
        return background2.getHeight();
    }

    //Return background3 bitmap
    public Bitmap getBackground3(){
        return background3;
    }

    //Return background3 width
    public int getBackground3Width(){
        return background3.getWidth();
    }

    //Return background3 height
    public int getBackground3Height(){
        return background3.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        /*
        We'll multiply widthHeightRatio with screenHeight to get scaled width of the bitmap.
        Then call createScaledBitmap() to create a new bitmap, scaled from an existing bitmap, when possible.
         */
        int backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
