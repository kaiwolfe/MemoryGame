package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Credits extends AppCompatActivity {

    //Variables used to know whether user turned sound on/off
    boolean musicOn = true;
    boolean sfxOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        getSettings();
    }

    //Activates when "Back" button is pressed, takes user to 'Main Menu' screen
    public void endActivity(View view){
        //Play sound
        AudioPlay.playButtonSFX(sfxOn);
        //End activity
        finish();
    }

    //Sets the music volume based on value
    public void setMusic(boolean on){
        if(on) {
            //Add code here to turn music on
        }

        else{
            //Add code here to turn music off
        }
    }

    private void getSettings (){
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        musicOn = settings.getBoolean("musicOn", true);
        sfxOn = settings.getBoolean("sfxOn", true);
        }
}