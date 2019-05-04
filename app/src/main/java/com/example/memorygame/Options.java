package com.example.memorygame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class Options extends AppCompatActivity {

    //Need to add in texture buttons
    //Will probably be ImageViews; change to darker version of image (or highlight?) when selected

    //Update music setting feature when separate music class is added

    //Use gridlayout for texture buttons; use image buttons, not imageview

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;

    //Variables for the on/off image buttons
    ImageView musicBtn;
    ImageView sfxBtn;

    //Variables for sound players
    MediaPlayer buttonSound;
    MediaPlayer switchSound;

    //Variables for GridView of Textures
    GridView gridView;

    //Variables for textures
    String texture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //Get the sound options passed from main menu
        Bundle bundle = getIntent().getExtras();
        musicOn = bundle.getBoolean("music");
        sfxOn = bundle.getBoolean("sfx");
        texture = bundle.getString("texture");

        //Find the on/off image buttons
        musicBtn = (ImageView) findViewById(R.id.button_music);
        sfxBtn = (ImageView) findViewById(R.id.button_sfx);

        //Assign specific sounds to each media player
        buttonSound = MediaPlayer.create(this, R.raw.button);
        switchSound = MediaPlayer.create(this, R.raw.toggle);

        //Set sound/music based on value
        setSound(sfxOn);
        setMusic(musicOn);

        //Create gridview of textures
        gridView = (GridView) findViewById(R.id.gridView_cardTextures);
        Category categories = new Category(R.drawable.card_blank, R.drawable.card_blank);
        final ArrayList<Card> cards = categories.getCardTextures();
        gridView.setColumnWidth(150);
        final TextureLayoutAdapter textureAdapter = new TextureLayoutAdapter(this, cards);
        gridView.setAdapter(textureAdapter);
    }

    //Activates when "Confirm" button is pressed, takes user to 'Main Menu' screen
    public void endActivity(View view){
        //Play sound
        buttonSound.start();

        //Calls method in this class, will pass data to main activity and end this activity
        finish();
    }

    //Activates when music button is pressed
    public void toggleMusic(View view) {
        //Play sound
        switchSound.start();

        //Change music setting value
        musicOn = !musicOn;

        //Set image and volume based on value
        setMusic(musicOn);
    }

    //Activates when sfx button is pressed
    public void toggleSFX(View view) {
        //Play sound
        switchSound.start();

        //Change sfx setting value
        sfxOn = !sfxOn;

        //Set image and volume based on value
        setSound(sfxOn);
    }

    //Sets the sfx image button and sound volume based on value
    public void setSound(boolean on){
        if(on){
            sfxBtn.setImageResource(R.drawable.button_on);
            buttonSound.setVolume(1,1);
            switchSound.setVolume(1,1);
        }

        else{
            sfxBtn.setImageResource(R.drawable.button_off);
            buttonSound.setVolume(0,0);
            switchSound.setVolume(0,0);
        }
    }

    //Sets the music image button and music volume based on value
    public void setMusic(boolean on){
        if(on){
            musicBtn.setImageResource(R.drawable.button_on);
            //Add code here to turn music on
        }

        else{
            musicBtn.setImageResource(R.drawable.button_off);
            //Add code here to turn music off
        }
    }

    //Used to finish the activity and return information to Main Activity
    @Override
    public void finish(){
        //Pass information back to Main Activity
        Intent intent = new Intent();
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);
        intent.putExtra("texture", texture);

        // Activity finished ok, return the data
        setResult(RESULT_OK, intent);
        super.finish();
    }

}