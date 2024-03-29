package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Instructions extends AppCompatActivity {

    //Variables used to know whether user turned sound on/off
    boolean musicOn;
    boolean sfxOn;

    //Create media players
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //Assigns specific sound files to each media player
        buttonSound = MediaPlayer.create(this, R.raw.button);

        //Set sound/music based on value
        getSettings();
    }

    //Activates when "Back" button is pressed, takes user to 'Main Menu' screen
    public void endActivity(View view){
        //Play sound
        buttonSound.start();

        //End activity
        finish();
    }

    //Sets the sound volume based on value
    public void setSound(boolean on){
        if(on)
            buttonSound.setVolume(1,1);
        else
            buttonSound.setVolume(0,0);
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

        setMusic(musicOn);
        setSound(sfxOn);
    }
}