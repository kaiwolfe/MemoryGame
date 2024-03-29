package com.example.memorygame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class End extends AppCompatActivity {

    //Variables for game settings
    boolean gameWon = false;
    int score = 0;
    int gameWonBonus = 50; //Bonus points for winning game

    //Variables for views that need to be changed
    TextView titleText;
    TextView scoreText;

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;

    //Variable for sound player
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        //Get the sound options passed from main menu
        Bundle bundle = getIntent().getExtras();

        score = bundle.getInt("score");
        gameWon = bundle.getBoolean("gameWon");

        //Assign view variables to appropriate views
        titleText = (TextView) findViewById(R.id.title_end);
        scoreText = (TextView) findViewById(R.id.text_score);

        //Default text is for winning game, this sets text if game is lost
        if(!gameWon) {
            titleText.setText("You Lose!");
            gameWonBonus = 0;
        } else {
            titleText.setText("YOU WIN!");
            gameWonBonus = 100;
        }

        //Set score
        score += gameWonBonus;
        scoreText.setText(score + "");
    }

    //Activates when "Main Menu" button is pressed, takes user to 'Main Menu' screen
    public void endActivity(View view) {
        AudioPlay.playButtonSFX(sfxOn);
        AudioPlay.stopGamePlayAudio();
        AudioPlay.createGamePlayAudio(this, R.raw.music_menu);
        //Clears activity stack, ends activity, starts main menu
        //THIS NEEDS TESTED
        Intent intent = new Intent(this, GameMode.class);
        //Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AudioPlay.stopGamePlayAudio();
        AudioPlay.createGamePlayAudio(this, R.raw.music_menu);
        //Clears activity stack, ends activity, starts main menu
        //THIS NEEDS TESTED
        Intent intent = new Intent(this, GameMode.class);
        //Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
