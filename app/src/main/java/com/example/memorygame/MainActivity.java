package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Need to create separate class for music player so it can be stopped in options

    //Fix bug
    //Even when sound is off, button sound still plays the first time
    //Ex: Menu > Options: Sound Off > Menu > Button=Sound Plays
    //Only happens the first time after changing the setting?

    //Variables used to know whether user turned sound on/off
    boolean musicOn;
    boolean sfxOn;


    //Variable code used for receiving information from Options Activity
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigns specific sound files to each media player

        //Get music, sfx, and texture settings based on last use. Set's default to true if the first time playing.


        AudioPlay.createGamePlayAudio(this, R.raw.music_gameplay);
        getSettings();
        AudioPlay.startGamePlayAudio(musicOn);

        AudioPlay.createButtonSFX(this, R.raw.button);
        AudioPlay.createToggleSFX(this, R.raw.toggle);
        AudioPlay.createFlipUpSFX(this, R.raw.card_up);
        AudioPlay.createFlipDownSFX(this, R.raw.card_down);
        //Starts music; will play through all activities
        //music.start();
        //NEED TO LOOP THE MUSIC OR START ANOTHER RAW FILE UPON COMPLETION.
    }

    //Activates when "Play" button is pressed, takes user to 'Game Mode Selection' screen
    public void launchGameModeSelection(View view){
        //Play sound
        AudioPlay.playButtonSFX(sfxOn);
        //Create GameMode Activity
        Intent intent = new Intent(this, GameMode.class);

        //Start activity
        startActivity(intent);
    }

    //Activates when "Help" button is pressed, takes user to 'Instructions' screen
    public void launchInstructions(View view){
        //Play sound
        AudioPlay.playButtonSFX(sfxOn);
        //Create Instructions Activity
        Intent intent = new Intent(this, Instructions.class);

        //Start activity
        startActivity(intent);
    }

    //Activates when "Options" button is pressed, takes user to 'Options' screen
    public void launchOptions(View view){
        //Play sound
        AudioPlay.playButtonSFX(sfxOn);
        //Create Options Activity
        Intent intent = new Intent(this, Options.class);

        //Start activity; this activity will expect data to be passed back
        startActivityForResult(intent, REQUEST_CODE);
    }

    //Activates when "Credits" button is pressed, takes user to 'Credits' screen
    public void launchCredits(View view){
        //Play sound
        AudioPlay.playButtonSFX(sfxOn);
        //Create Credits Activity
        Intent intent = new Intent(this, Credits.class);

        //Start activity
        startActivity(intent);
    }

    //Method for receiving information from Options Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            //Get data from activity
            getSettings();
        }
    }

    private void getSettings (){
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        musicOn = settings.getBoolean("musicOn", true);
        sfxOn = settings.getBoolean("sfxOn", true);
        FlipCard.setflipCardSFX(sfxOn);

        //Toggle music/sound based on settings
        if(!musicOn)
            AudioPlay.stopGamePlayAudio();
        else{
            AudioPlay.resetGamePlayAudio(this, R.raw.music_gameplay);
            AudioPlay.startGamePlayAudio(true);
        }
    }

}