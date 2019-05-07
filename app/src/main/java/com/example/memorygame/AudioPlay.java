package com.example.memorygame;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static MediaPlayer buttonPlayer;
    public static MediaPlayer togglePlayer;
    public static MediaPlayer flipCardUp;
    public static MediaPlayer flipCardDown;
    public static MediaPlayer cardMatch;




    public void onPrepared(MediaPlayer player) {
        player.start();
    }

    public static boolean isGameplayPlayingAudio = false;

    public static void createGamePlayAudio(Context c, int id){
        if (!isGameplayPlayingAudio) {
            mediaPlayer = MediaPlayer.create(c,id);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } else{
            mediaPlayer.release();
            isGameplayPlayingAudio = false;
            createGamePlayAudio(c, id);
        }
    }

    public static void startGamePlayAudio(boolean on) {
        if(on){
            if(!mediaPlayer.isPlaying())
            {
                mediaPlayer.start();
            }
        } else {
            stopGamePlayAudio();
        }
    }

    public static void stopGamePlayAudio() {
        isGameplayPlayingAudio = false;
        mediaPlayer.pause();
    }

    public static void resetGamePlayAudio(Context c, int id){
        mediaPlayer.release();
        createGamePlayAudio(c, id);
    }

    public static void createButtonSFX (Context c, int id){
        buttonPlayer = MediaPlayer.create(c,id);
    }

    public static void playButtonSFX(boolean on){
        if(on){
            buttonPlayer.start();
        }
    }

    public static void createToggleSFX (Context c, int id){
        togglePlayer = new MediaPlayer();
        togglePlayer = MediaPlayer.create(c,id);
    }

    public static void playToggleSFX(boolean on){
        if(on)
            togglePlayer.start();
    }
    public static void createFlipUpSFX (Context c, int id){
        flipCardUp = new MediaPlayer();
        flipCardUp = MediaPlayer.create(c,id);
    }

    public static void playFlipUpSFX(boolean on){
        if(on)
            flipCardUp.start();
    }
    public static void createFlipDownSFX (Context c, int id){
        flipCardDown = new MediaPlayer();
        flipCardDown = MediaPlayer.create(c,id);
    }

    public static void playFlipDownSFX(boolean on){
        if(on)
            flipCardDown.start();
    }

    public static void createMatchSFX (Context c, int id){
        cardMatch = new MediaPlayer();
        cardMatch = MediaPlayer.create(c,id);
    }

    public static void playMatchSFX(boolean on){
        if(on)
            cardMatch.start();
    }
}
