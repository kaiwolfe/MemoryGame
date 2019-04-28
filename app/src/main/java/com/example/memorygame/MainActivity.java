package com.example.memorygame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Need to create separate class for music player so it can be stopped in options
    //Need to add card back option to intent (must pass to and obtain from options, must pass to Game Mode

    //Fix bug
    //Even when sound is off, button sound still plays the first time
    //Ex: Menu > Options: Sound Off > Menu > Button=Sound Plays
    //Only happens the first time after changing the setting?

    //Variables used to know whether user turned sound on/off
    boolean musicOn = true;
    boolean sfxOn = true;

    //Create media players
    MediaPlayer music;
    MediaPlayer buttonSound;

    //Variable code used for receiving information from Options Activity
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigns specific sound files to each media player
        buttonSound = MediaPlayer.create(this, R.raw.button);
        music = MediaPlayer.create(this, R.raw.music_gameplay);

        //Starts music; will play through all activities
        music.start();
    }

    //Activates when "Play" button is pressed, takes user to 'Game Mode Selection' screen
    public void launchGameModeSelection(View view){
        //Play sound
        buttonSound.start();

        //Pass data to activity -- Will need more information
        Intent intent = new Intent(this, GameMode.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);

        //Start activity
        startActivity(intent);
    }

    //Activates when "Help" button is pressed, takes user to 'Instructions' screen
    public void launchInstructions(View view){
        //Play sound
        buttonSound.start();

        //Pass data to activity
        Intent intent = new Intent(this, Instructions.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);

        //Start activity
        startActivity(intent);
    }

    //Activates when "Options" button is pressed, takes user to 'Options' screen
    public void launchOptions(View view){
        //Play sound
        buttonSound.start();

        //Pass data to activity
        Intent intent = new Intent(this, Options.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);

        //Start activity; this activity will expect data to be passed back
        startActivityForResult(intent, REQUEST_CODE);
    }

    //Activates when "Credits" button is pressed, takes user to 'Credits' screen
    public void launchCredits(View view){
        //Play sound
        buttonSound.start();

        //Pass data to activity
        Intent intent = new Intent(this, Credits.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);

        //Start activity
        startActivity(intent);
    }

    //Method for receiving information from Options Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            //Get data from activity
            musicOn = intent.getExtras().getBoolean("music");
            sfxOn = intent.getExtras().getBoolean("sfx");

            //Toggle music/sound based on settings
            if(!musicOn)
                music.setVolume(0,0);
            else
                music.setVolume(1,1);
            if(!sfxOn)
                buttonSound.setVolume(0,0);
            else
                buttonSound.setVolume(1,1);
        }
    }

}