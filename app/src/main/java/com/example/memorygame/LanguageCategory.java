package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LanguageCategory extends AppCompatActivity implements GestureDetector.OnGestureListener{

    //Variables for game settings
    String category;
    String difficulty = "medium";

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

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_category);

        //Set radio button references
        easy = findViewById(R.id.radio_easy);
        medium = findViewById(R.id.radio_medium);
        hard = findViewById(R.id.radio_hard);

        getSettings();

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

    //To Math
    public void swipeLeft(){
        //Pass data to activity
        Intent intent = new Intent(this, MathCategory.class);

        //Start new activity and finish this one
        startActivity(intent);
        finish();
    }

    //To Normal
    public void swipeRight(){
        //Pass data to activity
        Intent intent = new Intent(this, NormalCategory.class);

        //Start new activity and finish this one
        startActivity(intent);
        finish();
    }

    //Activates when "Back" button is pressed, takes user to 'Game Mode' screen
    public void endActivity(View view) {
        //Play sound
        AudioPlay.playButtonSFX(sfxOn);

        //Ends activity
        finish();
    }

    //Activates when a category button is pressed, takes user to 'Gameplay' screen
    public void launchGameplay(View view) {
        //Play sound
        AudioPlay.playButtonSFX(sfxOn);

        //Get text of the button pressed
        Button btn = (Button)view;
        category = btn.getText().toString().toLowerCase();

        //Pass data to activity
        Intent intent = new Intent(this, Gameplay.class);
        intent.putExtra("category", category);

        //Start activity
        startActivity(intent);
        this.finish();
    }


    private void getSettings() {
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        musicOn = settings.getBoolean("musicOn", true);
        sfxOn = settings.getBoolean("sfxOn", true);
        difficulty = settings.getString("difficulty","medium");
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