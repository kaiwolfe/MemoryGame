package com.example.memorygame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Options extends AppCompatActivity {
    private Context context = this;

    //Need to add in texture buttons
    //Will probably be ImageViews; change to darker version of image (or highlight?) when selected

    //Update music setting feature when separate music class is added

    //Use gridlayout for texture buttons; use image buttons, not imageview


    //Variables for sound settings
    static boolean musicOn;
    static boolean sfxOn;
    static int cardBack;

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

        //Find the on/off image buttons
        musicBtn = (ImageView) findViewById(R.id.button_music);
        sfxBtn = (ImageView) findViewById(R.id.button_sfx);

        //Get the sound options passed from settings preferences.
        getSettings();

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
        AudioPlay.playButtonSFX(sfxOn);
        //Calls method in this class, will pass data to main activity and end this activity
        finish();
    }

    //Activates when music button is pressed
    public void toggleMusic(View view) {
        //Play sound
        AudioPlay.playToggleSFX(sfxOn);
        //Change music setting value
        musicOn = !musicOn;

        //Set image and volume based on value
        setMusic(musicOn, true);
    }

    //Activates when sfx button is pressed
    public void toggleSFX(View view) {
        //Change sfx setting value
        sfxOn = !sfxOn;
        //Play sound
        AudioPlay.playToggleSFX(sfxOn);



        //Set image and volume based on value
        setSound(sfxOn, true);
    }

    //Sets the sfx image button and sound volume based on value
    //playSound boolean determines if a sound is played when this method is called; it is called within getSettings at initialization and the sound should not play at this time.
    public void setSound(boolean on, boolean playSound){
        if(on){
            sfxBtn.setImageResource(R.drawable.button_on);
            FlipCard.setflipCardSFX(true);
            if(playSound)
                AudioPlay.playToggleSFX(sfxOn);
        } else{
            sfxBtn.setImageResource(R.drawable.button_off);
            FlipCard.setflipCardSFX(false);
        }
    }

    //Sets the music image button and music volume based on value
    public void setMusic(boolean on, boolean reset){
        if(on){
            musicBtn.setImageResource(R.drawable.button_on);
            //Add code here to turn music on
            if(reset)
                AudioPlay.resetGamePlayAudio(this, R.raw.music_gameplay);
            AudioPlay.startGamePlayAudio(true);

        } else{
            musicBtn.setImageResource(R.drawable.button_off);
            //Add code here to turn music off
            AudioPlay.stopGamePlayAudio();
        }
    }

    public static void setCardBack(int textureID){
        cardBack = textureID;
    }

    //Used to finish the activity and return information to Main Activity
    @Override
    public void finish(){
        //Pass information back to Main Activity
        Intent intent = new Intent();
//        intent.putExtra("music", musicOn);
//        intent.Extra("sfx", sfxOn);
////      intent.putExtra("texture", texture);

        SharedPreferences settings = getSharedPreferences("Settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("musicOn", musicOn);
        editor.putBoolean("sfxOn", sfxOn);
        editor.putInt("cardTexture", cardBack);
        editor.apply();

        // Activity finished ok, return the data
        setResult(RESULT_OK, intent);
        super.finish();
    }

    private void getSettings (){
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        musicOn = settings.getBoolean("musicOn", true);
        sfxOn = settings.getBoolean("sfxOn", true);

       setMusic(musicOn, false);
       setSound(sfxOn, false);
    }

    public static boolean getSFX(){
        return sfxOn;
    }
}