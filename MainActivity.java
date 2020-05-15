package com.originals.flappybird;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppConstants.initialization(this.getApplicationContext());
        // Your app needs to call MobileAds.initialize() method only once,
        // ideally in launcher activity. In our case MainActivity.
        // There's no need to call it twice during a single execution of the app,
        // or in every single activity.
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        // Replace this with your app ID
    }


    public void startGame(View view){
        //Log.i("ImageButton","clicked");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();

    }
}
