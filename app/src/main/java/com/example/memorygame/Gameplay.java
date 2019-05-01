package com.example.memorygame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

public class Gameplay extends AppCompatActivity {

    //Variables for game settings
    String gameMode;
    String category;
    String difficulty;
    int round = 1;
    int numOfCards;
    boolean gameWon = false;

    //Variables for determining score
    int difficultyMultiplier;
    int cardPoints;
    int roundBonus = 25;
    int score = 0;

    //Variables for sound settings
    boolean musicOn;
    boolean sfxOn;

    //Variable for sound player
    MediaPlayer music;
    MediaPlayer cardUpSound;
    MediaPlayer cardDownSound;

    //GridLayout variables
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        //Get the sound options passed from main menu
        Bundle bundle = getIntent().getExtras();
        musicOn = bundle.getBoolean("music");
        sfxOn = bundle.getBoolean("sfx");
        gameMode = bundle.getString("mode");
        category = bundle.getString("category");
        difficulty = bundle.getString("difficulty");
        //Will need more data: textures

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

        //add the cardviews here, Kai.
        setup();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(R.drawable.calicocat1, R.drawable.cardback_stripes));
        cards.add(new Card(R.drawable.calicocat2, R.drawable.cardback_bluestripes));
        cards.add(new Card(R.drawable.tabbycat1, R.drawable.cardback_brick));
        cards.add(new Card(R.drawable.tabbycat2, R.drawable.cardback_deer));
        cards.add(new Card(R.drawable.blackcat1, R.drawable.cardback_fingerprint));
        cards.add(new Card(R.drawable.blackcat2, R.drawable.cardback_flower));
        cards.add(new Card(R.drawable.graycat1, R.drawable.cardback_green));
        cards.add(new Card(R.drawable.graycat2, R.drawable.cardback_koi));
        cards.add(new Card(R.drawable.tortiecat1, R.drawable.cardback_orange));
        cards.add(new Card(R.drawable.tortiecat2, R.drawable.cardback_pink));
        cards.add(new Card(R.drawable.whitecat1, R.drawable.cardback_purple));
        cards.add(new Card(R.drawable.whitecat2, R.drawable.cardback_sakura));
        cards.add(new Card(R.drawable.whitecat1, R.drawable.cardback_tile));
        cards.add(new Card(R.drawable.whitecat2, R.drawable.cardback_classic));





        gridView = findViewById(R.id.gridView);

        CardLayoutAdapter cardAdapter = new CardLayoutAdapter(this, cards);
        gridView.setAdapter(cardAdapter);
    }

    public void setup(){
        setScoreValues();
        setCardBacks();
        setCardFaces();
        populateCards();
        createCards();
    }

    //Set values used to calculate score
    public void setScoreValues(){
        //Set points per match based on round
        cardPoints = (round * 5) + 5;

        //Set difficulty multiplier
        if(difficulty.equals("easy"))
            difficultyMultiplier = 1;
        else if(difficulty.equals("normal"))
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

    //Set card attributes
    public void setCardFaces(){

        //Set faces of cards
        //Use database for normal/language
        //Use RNG for math
        if(category.equals("animals")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("normal")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("food")){
        }

        else if(category.equals("nature")){
        }

        else if(category.equals("add")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("normal")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("sub")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("normal")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("random")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("normal")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("english")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("normal")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("spanish")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("normal")){
            }

            else if(difficulty.equals("hard")){
            }
        }

        else if(category.equals("japanese")){
            if(difficulty.equals("easy")){
            }

            else if(difficulty.equals("normal")){
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