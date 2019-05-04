package com.example.memorygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class Gameplay extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 30000;
    TextView countDownText;

    //Variables for game settings
    String gameMode;
    String category;
    String difficulty;
    int round = 1;
    int numOfCards;
    int numOfPairs;
    boolean gameWon = false;

    //Variables for determining score
    int difficultyMultiplier;
    int cardPoints;
    int roundBonus = 25;
    int score = 0;

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;

    //Variable for texture setting
    String texture;

    //Variable for sound player
    MediaPlayer music;
    MediaPlayer cardUpSound;
    MediaPlayer cardDownSound;

    //GridLayout variables
    static GridView gridView;
    static int choices = 0;
    static int image1;
    static int image2;
    Card choice = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        //Get the sound options passed from main menu
        Bundle bundle = getIntent().getExtras();
        musicOn = bundle.getBoolean("music");
        sfxOn = bundle.getBoolean("sfx");
        texture = bundle.getString("texture");
        gameMode = bundle.getString("mode");
        category = bundle.getString("category");
        difficulty = bundle.getString("difficulty");

        //If round != 1, also bundle.getInt("score)" and .getInt("round")
        //When last match:
            //Create the intent, pass all information + score and round
            //startActivity() -- gameplay and finish()

        //Assign specific sounds to each media player
        music = MediaPlayer.create(this, R.raw.music_gameplay);
        cardUpSound = MediaPlayer.create(this, R.raw.card_up);
        cardDownSound = MediaPlayer.create(this, R.raw.card_down);

        //Set sound/music based on value
        setSound(sfxOn);
        setMusic(musicOn);

        //Set # of cards based on round
        if(round == 1)
            numOfCards = 6;
        else if(round == 2)
            numOfCards = 10;
        else
            numOfCards = 12;

        numOfPairs = numOfCards / 2;

        //COUNTDOWN TIMER SECTION
        countDownText = (TextView) findViewById(R.id.text_timer);
        setup();
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                countDownText.setText("TIME'S UP!");
            }
        }.start();
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText = "";
        if (minutes < 10) timeLeftText = "0";
        timeLeftText += "" + minutes;
        timeLeftText += " : " ;
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countDownText.setText(timeLeftText);
    }


    public void setup(){
        setScoreValues();
        setCardBacks();
        setCardFaces();
        populateCards();
        createCards();
        startTimer();
    }

    //Set values used to calculate score
    public void setScoreValues(){
        //Set points per match based on round
        cardPoints = (round * 5) + 5;

        //Set difficulty multiplier
        if(difficulty.equals("easy"))
            difficultyMultiplier = 1;
        else if(difficulty.equals("medium"))
            difficultyMultiplier = 2;
        else
            difficultyMultiplier = 3;
    }


    public void createCards(){
        //Create array of cards using Card class
        //
    }

    //Set card attributes
    public void setCardBacks(){
        //Set backs of cards based on texture setting
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void disableGridView() {
        for (int i = 0; i < gridView.getChildCount(); i++){
        }
    }
    public static void enableGridView() {
        for (int i = 0; i < gridView.getChildCount(); i++){
        }
    }

    //Set card attributes
    public void setCardFaces(){

        //Rather than a bunch of if statements, pass the variables
        //Instead of if(diff=easy), getCards(3, easy), just getCards(numOfCards, difficulty)


        //Set faces of cards
        if(category.equals("animals")){
            if(difficulty.equals("easy")){
                Category categories = new Category(R.drawable.texture_fingerprint, R.drawable.card_blank);
                final ArrayList<Card> cards = categories.getAnimalCards(3, "Easy");
                gridView = findViewById(R.id.gridView);
                gridView.setColumnWidth(300);
                final CardLayoutAdapter cardAdapter = new CardLayoutAdapter(this, cards);
                gridView.setAdapter(cardAdapter);
            }

            else if(difficulty.equals("medium")){
                Category categories = new Category(R.drawable.texture_fingerprint, R.drawable.card_blank);
                final ArrayList<Card> cards = categories.getAnimalCards(6, "Medium");
                gridView = findViewById(R.id.gridView);
                final CardLayoutAdapter cardAdapter = new CardLayoutAdapter(this, cards);
                gridView.setAdapter(cardAdapter);
            }

            else if(difficulty.equals("hard")){
                Category categories = new Category(R.drawable.texture_fingerprint, R.drawable.card_blank);
                final ArrayList<Card> cards = categories.getAnimalCards(8, "Hard");
                gridView = findViewById(R.id.gridView);
                final CardLayoutAdapter cardAdapter = new CardLayoutAdapter(this, cards);
                gridView.setAdapter(cardAdapter);
            }
        }

        else if(category.equals("food")){
        }

        else if(category.equals("nature")){
        }

        else if(category.equals("addition")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("medium")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("subtraction")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("medium")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("random")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("medium")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("spanish")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("medium")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("japanese")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("medium")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("german")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("medium")){
            }

            else if(difficulty.equals("hard")){
            }
        }

    }

    //Put cards on screens
    public void populateCards(){

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


    public void addToScore(){

        score += cardPoints;

    }

    public void calculateScore(){
        score = score * difficultyMultiplier;

        //Only if round is won; adding AFTER multiplier is not a mistake
        score += roundBonus;
    }



    //Pass data
    public void endGame(){
        //Pass data to activity
        Intent intent = new Intent(this, End.class);
        intent.putExtra("music", musicOn);
        intent.putExtra("sfx", sfxOn);
        intent.putExtra("gameWon", gameWon);
        intent.putExtra("score", score);

        //Start activity
        startActivity(intent);
    }



    //Sets the sfx image button and sound volume based on value
    public void setSound(boolean on){
        if(on) {
            cardUpSound.setVolume(1, 1);
            cardDownSound.setVolume(1, 1);
        }

        else {
            cardUpSound.setVolume(0, 0);
            cardDownSound.setVolume(0, 0);
        }
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