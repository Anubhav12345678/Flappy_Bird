package com.originals.flappybird;

/**
 * Created by User on 4/8/2020.
 */

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    GameThread gameThread;
//    SurfaceHolder holder = getHolder();

    public GameView(Context context) {
        super(context);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(!gameThread.isRunning()){
           gameThread = new GameThread(holder);
            gameThread.start();
        }
        else
        {
            gameThread.start();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
      // here we dont do anything as this is not in our interest
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // we destroy the currently running thread

        if(gameThread.isRunning()){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry)
            {
              try{
                  gameThread.join();
                  retry=false;
              }catch(InterruptedException e){}
            }
        }

    }

    void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        // Tap is detected
        if(action == MotionEvent.ACTION_DOWN){

            if(AppConstants.getGameEngine().gameState==0)
            {
                AppConstants.getGameEngine().gameState=1;
                AppConstants.getSoundBank().playSwoosh();
            }else{
                AppConstants.getSoundBank().playWing();
            }


//            AppConstants.getGameEngine().gameState=1;
            AppConstants.getGameEngine().bird.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
        }
        return true;
    }
}
