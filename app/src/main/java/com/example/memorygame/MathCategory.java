package com.example.memorygame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MathCategory extends AppCompatActivity {

    //Variables for game settings
    String gameMode;
    String category;
    String difficulty;

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;

    //Variable for texture
    String texture;

    //Variable for sound player
    MediaPlayer buttonSound;

    //Variables for radio buttons
    RadioButton easy;
    RadioButton medium;
    RadioButton hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_category);

        //Get the sound options passed from main menu
        Bundle bundle = getIntent().getExtras();
        musicOn = bundle.getBoolean("music");
        sfxOn = bundle.getBoolean("sfx");
        texture = bundle.getString("texture");
        gameMode = bundle.getString("mode");
        difficulty = bundle.getString("difficulty");

        //Assign specific sounds to each media player
        buttonSound = MediaPlayer.create(this, R.raw.button);

        //Set sound/music based on value
        setSound(sfxOn);
        setMusic(musicOn);

        //Set radio button references
        easy = findViewById(R.id.radio_easy);
        medium = findViewById(R.id.radio_medium);
        hard = findViewById(R.id.radio_hard);

        //Set initial checked button based on selected difficulty
        if(difficulty.equals("medium")){
            easy.setChecked(false);
            medium.setChecked(true);
            hard.setChecked(false);
        }
        else if(difficulty.equals("easy")){
            easy.setChecked(true);
            medium.setChecked(false);
            hard.setChecked(false);
        }
        else{
            easy.setChecked(false);
            medium.setChecked(false);
            hard.setChecked(true);
        }
    }

    public void onRadioButtonClicked(View view) {
        //Checks if the button pressed is checked
        boolean checked = ((RadioButton) view).isChecked();

        //Checks which one was pressed, sets difficulty variable
        switch(view.getId()) {
            case R.id.radio_easy:
                if (checked)
                    difficulty = "easy";
                break;
            case R.id.radio_hard:
                if (checked)
                    difficulty = "hard";
                break;
            case R.id.radio_medium:
                if(checked)
                    difficulty = "medium";
        }
    }

    //To Normal
    public void swipeLeft(){
        //Pass data to activity
        Intent intent = new Intent(this, NormalCategory.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);
        intent.putExtra("texture", texture);
        intent.putExtra("mode", gameMode);
        intent.putExtra("difficulty", difficulty);

        //Start new activity and finish this one
        startActivity(intent);
        finish();
    }

    //To Language
    public void swipeRight(){
        //Pass data to activity
        Intent intent = new Intent(this, LanguageCategory.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);
        intent.putExtra("texture", texture);
        intent.putExtra("mode", gameMode);
        intent.putExtra("difficulty", difficulty);

        //Start new activity and finish this one
        startActivity(intent);
        finish();
    }

    //Activates when "Back" button is pressed, takes user to 'Game Mode' screen
    public void endActivity(View view) {
        //Play sound
        buttonSound.start();

        //Ends activity
        finish();
    }

    //Activates when a category button is pressed, takes user to 'Gameplay' screen
    public void launchGameplay(View view) {
        //Play sound
        buttonSound.start();

        //Get text of the button pressed
        Button btn = (Button)view;
        category = btn.getText().toString().toLowerCase();

        //Pass data to activity
        Intent intent = new Intent(this, Gameplay.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);
        intent.putExtra("texture", texture);
        intent.putExtra("mode", gameMode);
        intent.putExtra("category", category);
        intent.putExtra("difficulty", difficulty);

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