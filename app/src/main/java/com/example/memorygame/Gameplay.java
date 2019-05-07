package com.example.memorygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class Gameplay extends AppCompatActivity {

    private static CountDownTimer countDownTimer;
    private static long timeLeftInMilliseconds;
    static TextView countDownText;

    //Variables for game settings
    static String category;
    static String langCode;
    static String difficulty;
    static int round;
    static int matchedPairs;
    static int numOfPairs;
    boolean gameWon = false;
    private static boolean allMatched = false;
    Category categories;

    //Variables for determining score
    static int difficultyMultiplier;
    static int cardPoints;
    int roundBonus = 25;
    static int score = 0;

    //Variables for sound settings
    static boolean musicOn;
    static boolean sfxOn;

    //Variable for texture setting
    int texture = R.drawable.cardback_bluestripes;

    //GridLayout variables
    static GridView gridView;
    static ArrayList<Card> cards;
    CardLayoutAdapter cardAdapter;
    CardLayoutAdapterWords wordCardAdapter;
    CardLayoutAdapterMath mathCardAdapter;
    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        matchedPairs = 0;
        //Get the sound options passed from main menu
        Bundle bundle = getIntent().getExtras();
        category = bundle.getString("category");
        gameWon = false;

        int numCols = 3;

        //Set sound, music, and texture based on settings.
        getSettings();
        AudioPlay.stopGamePlayAudio();
        AudioPlay.resetGamePlayAudio(this, R.raw.music_gameplay);
        AudioPlay.startGamePlayAudio(musicOn);

        //COUNTDOWN TIMER SECTION
        countDownText = (TextView) findViewById(R.id.text_timer);
        gridView = (GridView) findViewById(R.id.gridView);
        setup();
        categories = new Category(texture, R.drawable.card_blank);
        try {
            generateRound(category, difficulty, numCols, numOfPairs, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRoundPairs(){

        if (round == 1){
            numOfPairs = 3;
            timeLeftInMilliseconds = 30000;
            System.out.println("Round:" + round);
        } else if (round == 2) {
             numOfPairs = 4;
            timeLeftInMilliseconds = 45000;
            System.out.println("Round:" + round);
        } else {
            numOfPairs = 6;
            timeLeftInMilliseconds = 60000;
            System.out.println("Round:" + round);
        }
    }

    public static void compareMatchedToNumPairs(){
        //if round completed in time.
        if (matchedPairs == numOfPairs){
            //increment the round
            setAllMatched(true);
            }
        }

    public static void setAllMatched(boolean matched){
        allMatched = matched;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
                if (allMatched){
                    try {
                        nextRound();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.cancel();
                    countDownTimer = null;
                }
            }

            @Override
            public void onFinish() {
                countDownText.setText("TIME'S UP!");
                //Start End Activity
                //Bundle the score, num matches, etc. to pass.
                endGame();
            }
        }.start();
    }

    public static void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText = "";
        if (minutes < 10) timeLeftText = "0";
        timeLeftText += "" + minutes;
        timeLeftText += " : ";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countDownText.setText(timeLeftText);
    }


    public void setup() {
        setRoundPairs();
        setScoreValues();
        startTimer();
    }

    //Set values used to calculate score
    public static void setScoreValues() {
        //Set points per match based on round
        cardPoints = (round * 5) + 5;

        //Set difficulty multiplier
        if (difficulty.equals("easy"))
            difficultyMultiplier = 1;
        else if (difficulty.equals("medium"))
            difficultyMultiplier = 2;
        else
            difficultyMultiplier = 3;
    }


    //USE THIS METHOD TO ITERATE THROUGH THE CARDS AND TURN OFF THEIR ONCLICKLISTERNERS
    @SuppressLint("ClickableViewAccessibility")
    public static void disableGridView() {
        for (int i = 0; i < gridView.getChildCount(); i++) {

        }
    }


    public void generateRound(String category, String difficulty, int numColumns, int numCardPairs, boolean firstRound) throws IOException {

        gridView.setNumColumns(numColumns);

        switch (category) {
            case "animals":
                cards = categories.getAnimalCards(numCardPairs, difficulty);
                type = "image";
                break;
            case "food":
                cards = categories.getFoodCards(numCardPairs, difficulty);
                type = "image";
                break;
            case "nature":
                cards = categories.getNatureCards(numCardPairs, difficulty);
                type = "image";
                break;
            case "addition":
                //grab respective set of cards
                cards = categories.getAdditionCards(numCardPairs, difficulty);
                type = "math";
                break;
            case "subtraction":
                //grab respective set of cards
                cards = categories.getSubtracitonCards(numCardPairs, difficulty);
                type = "math";
                break;
            case "random":
                //grab respective set of cards
                cards = categories.getRandomCards(numCardPairs, difficulty);
                type = "math";
                break;
            case "japanese":
                //grab respective set of cards
                cards = categories.getWordCards(this, numCardPairs, difficulty);
                langCode = "jap";
                type = "word";
                break;
            case "german":
                //grab respective set of cards
                cards = categories.getWordCards(this, numCardPairs, difficulty);
                langCode = "ger";
                type = "word";
                break;
            case "spanish":
                //grab respective set of cards
                cards = categories.getWordCards(this, numCardPairs, difficulty);
                langCode = "spa";
                type = "word";
                break;
        }
        if(firstRound) {
            switch(type){
                case "image":
                    cardAdapter = new CardLayoutAdapter(this, cards);
                    gridView.setAdapter(cardAdapter);
                    break;
                case "word":
                    wordCardAdapter = new CardLayoutAdapterWords(this, cards);
                    gridView.setAdapter(wordCardAdapter);
                    break;
                case "math":
                    mathCardAdapter = new CardLayoutAdapterMath(this, cards);
                    gridView.setAdapter(mathCardAdapter);
                    break;
                }
        }else {
            switch(type){
                case "image":
                    cardAdapter.updateItems(cards);
                    gridView.invalidateViews();
                    gridView.invalidate();
                    gridView.setAdapter(cardAdapter);
                    break;
                case "word":
                    wordCardAdapter.updateItems(cards);
                    gridView.invalidateViews();
                    gridView.invalidate();
                    gridView.setAdapter(wordCardAdapter);
                    break;
                case "math":
                    mathCardAdapter.updateItems(cards);
                    gridView.invalidateViews();
                    gridView.invalidate();
                    gridView.setAdapter(mathCardAdapter);
                    break;
            }
        }
    }

    public static String getLanguage(){
        return langCode;
    }

    public void selectCard(View view) {
        //Play sound
        //Flip up card

        //If first card, set value (will be used to compare against second card -- use ID # of card)
        //If second card, check against value

        //If not match
        //Flip 2nd card, play sound
        //Flip 1st card, play sound

        //If match
        //Play sound? (needs added)
        //Add to score
        //Remove cards
        //Check if last match
        //If so, do multiplier, then add round bonus to score
        //Check round
        //If last, gameWon = true, go to End screen
        //Else, set up next round, round++, setup()
    }

    public static void addPair() {
        matchedPairs++;
        addToScore();
        AudioPlay.playMatchSFX(sfxOn);
    }


    public static void addToScore() {
        score += cardPoints;
    }

    public void calculateScore() {
        score = score * difficultyMultiplier;

        //Only if round is won; adding AFTER multiplier is not a mistake
        score += roundBonus;
    }


    //Pass data
    public void endGame() {
        //Pass data to activity
        Intent intent = new Intent(this, End.class);

        calculateScore();
        intent.putExtra("gameWon", gameWon);
        intent.putExtra("score", score);
        round = 1;
        matchedPairs = 0;
        setAllMatched(false);
        //Start activity
        startActivity(intent);
        this.finish();
    }

    public void nextRound() throws IOException {
        if(round == 3){
            gameWon = true;
            score += round * (int)timeLeftInMilliseconds/1000;
            endGame();
        }else{
            round++;
            matchedPairs = 0;
            score += round * (int)timeLeftInMilliseconds /1000;
            setup();
            setAllMatched(false);
            generateRound(category, difficulty, 4, numOfPairs, false);
        }
    }


    private void getSettings() {
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        round = settings.getInt("round", 1);
        musicOn = settings.getBoolean("musicOn", true);
        sfxOn = settings.getBoolean("sfxOn", true);
        int temp = R.drawable.cardback_bluestripes;
        texture = settings.getInt("cardTexture", temp);
        difficulty = settings.getString("difficulty", "medium");

    }

    @Override
    public void onBackPressed() {
        try {
            countDownTimer.cancel();
            countDownTimer = null;

        } catch (Exception e) {
            Log.d("TIMER", e.getMessage());
        }
            matchedPairs = 0;
            setAllMatched(false);
            round = 1;
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
