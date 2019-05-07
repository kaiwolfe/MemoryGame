package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameMode extends AppCompatActivity {

    //Variable for game settings
    String gameMode;
    String difficulty;

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        getSettings();
    }

    //Activates when "Back" button is pressed, takes user to 'Main Menu' screen
    public void endActivity(View view){
        AudioPlay.playButtonSFX(sfxOn);
        //Clears activity stack, ends activity, starts main menu
        Intent intent = new Intent(this, MainActivity.class);
        //Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    //Activates when "Normal" "Math" or "Language" buttons are pressed, takes user to appropriate category activity
    public void launchCategory(View view) {
        AudioPlay.playButtonSFX(sfxOn);
        //Get text of the button pressed
        Button btn = (Button)view;
        gameMode = btn.getText().toString().toLowerCase();

        //Determine next activity based on button pressed
        Intent intent;
        if(gameMode.equals("math"))
            intent = new Intent(this, MathCategory.class);
        else if(gameMode.equals("language"))
            intent = new Intent(this, LanguageCategory.class);
        else
            intent = new Intent(this, NormalCategory.class);

        //Pass data to activity
        intent.putExtra("mode", gameMode);

        //Start activity
        startActivity(intent);
    }


    private void getSettings (){
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("round", 1);

        musicOn = settings.getBoolean("musicOn", true);
        sfxOn = settings.getBoolean("sfxOn", true);
        difficulty = settings.getString("difficulty", "medium");
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

        finish();
    }
}
