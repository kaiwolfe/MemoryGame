package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NormalCategory extends AppCompatActivity implements GestureDetector.OnGestureListener {

    //Variables for game settings
    String category;
    String difficulty;

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;

    GestureDetector gestureDetector;

    //Variable for sound player
    MediaPlayer buttonSound;

    //Variables for radio buttons
    RadioButton easy;
    RadioButton medium;
    RadioButton hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_category);


        //Assign specific sounds to each media player
        buttonSound = MediaPlayer.create(this, R.raw.button);

        //Set radio button references
        easy = findViewById(R.id.radio_easy);
        medium = findViewById(R.id.radio_medium);
        hard = findViewById(R.id.radio_hard);

        //Set sound/music based on value
        getSettings();

        //Set initial checked button based on selected difficulty
        if (difficulty.equals("medium")) {
            easy.setChecked(false);
            medium.setChecked(true);
            hard.setChecked(false);
        } else if (difficulty.equals("easy")) {
            easy.setChecked(true);
            medium.setChecked(false);
            hard.setChecked(false);
        } else {
            easy.setChecked(false);
            medium.setChecked(false);
            hard.setChecked(true);
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=(RadioButton)findViewById(checkedId);
                difficulty = rb.getText().toString().toLowerCase();
                SharedPreferences settings = getSharedPreferences("Settings", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("difficulty", difficulty);
                editor.apply();
            }
        });

        gestureDetector = new GestureDetector(this);
    }

    //To Language
    public void swipeLeft() {
        //Pass data to activity
        Intent intent = new Intent(this, LanguageCategory.class);

        //Start new activity and finish this one
        startActivity(intent);
        finish();
    }

    //To Math
    public void swipeRight() {
        //Pass data to activity
        Intent intent = new Intent(this, MathCategory.class);

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
        Button btn = (Button) view;
        category = btn.getText().toString().toLowerCase();

        //Pass data to activity
        Intent intent = new Intent(this, Gameplay.class);
        intent.putExtra("category", category);
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("score", 0);
        intent.putExtra("round", 1);

        //Start activity
        startActivity(intent);
        this.finish();
    }

    //Sets the sfx image button and sound volume based on value
    public void setSound(boolean on) {
        if (on)
            buttonSound.setVolume(1, 1);
        else
            buttonSound.setVolume(0, 0);
    }

    //Sets the music image button and music volume based on value
    public void setMusic(boolean on) {
        if (on) {
            //Add code here to turn music on
        } else {
            //Add code here to turn music off
        }
    }

    private void getSettings() {
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        musicOn = settings.getBoolean("musicOn", true);
        sfxOn = settings.getBoolean("sfxOn", true);
        difficulty = settings.getString("difficulty","medium");

        //Toggle music/sound based on settings
        setMusic(musicOn);
        setSound(sfxOn);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            //Detect left or right swipe
            if (Math.abs(diffX) > 100 && Math.abs(velocityX) > 100) {
                if (diffX > 0) {
                    //Swipe right detected
                    swipeRight();
                } else {
                    //Swipe left detected
                    swipeLeft();
                }
                result = true;

            }

        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}