package com.example.memorygame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameMode extends AppCompatActivity {

    //Variable for game settings
    String gameMode = "";

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;

    //Variable for sound player
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        //Get the sound options passed from main menu
        Bundle bundle = getIntent().getExtras();
        musicOn = bundle.getBoolean("music");
        sfxOn = bundle.getBoolean("sfx");

        //Assign specific sounds to each media player
        buttonSound = MediaPlayer.create(this, R.raw.button);

        //Set sound/music based on value
        setSound(sfxOn);
        setMusic(musicOn);
    }

    //Activates when "Back" button is pressed, takes user to 'Main Menu' screen
    public void endActivity(View view){
        //Play sound
        buttonSound.start();

        //Ends activity
        finish();
    }

    //Activates when "Normal" "Math" or "Language" buttons are pressed, takes user to appropriate category activity
    public void launchCategory(View view) {
        //Play sound
        buttonSound.start();

        //Get text of the button pressed
        Button btn = (Button)view;
        gameMode = btn.getText().toString().toLowerCase();

        //Determine next activity based on button pressed
        Intent intent;
        if(gameMode.equals("Math"))
            intent = new Intent(this, MathCategory.class);
        else if(gameMode.equals("Language"))
            intent = new Intent(this, LanguageCategory.class);
        else
            intent = new Intent(this, NormalCategory.class);

        //Pass data to activity
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);
        intent.putExtra("mode", gameMode);

        //Start activity
        startActivity(intent);
    }

    //Sets the sfx image button and sound volume based on value
    public void setSound(boolean on){
        if(on)
            buttonSound.setVolume(1,1);
        else
            buttonSound.setVolume(0,0);
    }

    //Sets the music image button and music volume based on value
    public void setMusic(boolean on){
        if(on){
            //Add code here to turn music on
        }

        else{
            //Add code here to turn music off
        }
    }

}