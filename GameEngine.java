package com.originals.flappybird;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.Random;
/**
 * Created by User on 4/9/2020.
 */
public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    static int gameState;
    ArrayList<Tube> tubes;
    Random random;
    int level=1;
    int score; // Stores the score
    int scoringTube; // Keeps track of scoring tube
    Paint scorePaint;
    boolean tubeVelocityFlag  = false;

    public GameEngine(){
       backgroundImage = new BackgroundImage();
        bird = new Bird();
        // 0 = Not started
        // 1 = Playing
        // 2 = GameOver
        gameState = 0;
        tubes = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < AppConstants.numberOfTubes; i++) {
            int tubeX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanceBetweenTubes;
            // Get topTubeOffsetY
            int topTubeOffsetY = AppConstants.minTubeOffsetY +
                    random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
            // Now create Tube objects
            Tube tube = new Tube(tubeX, topTubeOffsetY);
            tubes.add(tube);
        }
        score = 0;
        scoringTube = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    public void updateAndDrawTubes(Canvas canvas) {
        if (gameState == 1) {
            if (level == 2 && tubeVelocityFlag == false) {
                AppConstants.tubeVelocity = 15;
                tubeVelocityFlag = true;
            } else if (level == 3 && tubeVelocityFlag == true) {
                AppConstants.tubeVelocity = 18;
                tubeVelocityFlag = false;
            }
            if ((tubes.get(scoringTube).getTubeX() < bird.getX() + AppConstants.getBitmapBank().getBirdWidth())
                    && (tubes.get(scoringTube).getTopTubeOffsetY() > bird.getY()
                    || tubes.get(scoringTube).getBottomTubeY() < (bird.getY() +
                    AppConstants.getBitmapBank().getBirdHeight()))) {
                // Go to GameOver screen
                gameState = 2;
                //Log.d("Game", "Over");
                AppConstants.getSoundBank().playHit();
                Context context = AppConstants.gameActivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else if (tubes.get(scoringTube).getTubeX() < bird.getX() - AppConstants.getBitmapBank().getTubeWidth()) {
                score++;
                scoringTube++;
                if (scoringTube > AppConstants.numberOfTubes - 1) {
                    scoringTube = 0;
                }
                AppConstants.getSoundBank().playPoint();
            }
            for (int i = 0; i < AppConstants.numberOfTubes; i++) {
                if (tubes.get(i).getTubeX() < -AppConstants.getBitmapBank().getTubeWidth()) {
                    tubes.get(i).setTubeX(tubes.get(i).getTubeX() +
                            AppConstants.numberOfTubes * AppConstants.distanceBetweenTubes);
                    int topTubeOffsetY = AppConstants.minTubeOffsetY +
                            random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
                    tubes.get(i).setTopTubeOffsetY(topTubeOffsetY);
                    tubes.get(i).setTubeColor();
                }
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() - AppConstants.tubeVelocity);
                if (tubes.get(i).getTubeColor() == 0) {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
                } else {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
                }
            }
            canvas.drawText("Pt: " + score, 0, AppConstants.SCREEN_HEIGHT - 10, scorePaint);
        }
    }

    public  void updateAndDrawBackgroundImage(Canvas canvas){
        if (score > 3 && score < 7) {
            level = 2;
        } else if (score >= 7) {
            level = 3;
        }
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        if (level == 1) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() +
                        AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
            }
        } else if (level == 2) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground2(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackground2Width() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground2(), backgroundImage.getX() +
                        AppConstants.getBitmapBank().getBackground2Width(), backgroundImage.getY(), null);
            }
        } else if (level == 3) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground3(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackground3Width() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground3(), backgroundImage.getX() +
                        AppConstants.getBitmapBank().getBackground3Width(), backgroundImage.getY(), null);
            }
        }
    }

    public void updateAndDrawBird(Canvas canvas) {
        if (gameState == 1) {
            if (bird.getY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight()) || bird.getVelocity() < 0) {
                bird.setVelocity(bird.getVelocity() + AppConstants.gravity);
                bird.setY(bird.getY() + bird.getVelocity());
            }
        }
        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getX(), bird.getY(), null);
        currentFrame++;
        // If it exceeds maxframe re-initialize to 0
        if (currentFrame > bird.maxFrame) {
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }
}
