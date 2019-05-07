package com.example.memorygame;

import android.media.audiofx.DynamicsProcessing;

public class Card {
    private int backImage;
    private int frontImage;
    private String text;
    private Word word;
    private Equation equation;
    boolean english;


    public Card (int backImage, int frontImage) {
        this.backImage = backImage;
        this.frontImage = frontImage;
    }

    public Card (int backImage, int frontImage, String text) {
        this.backImage = backImage;
        this.frontImage = frontImage;
        this.text = text;
    }

    public Card (int backImage, int frontImage, Word word, boolean english) {
        this.backImage = backImage;
        this.frontImage = frontImage;
        this.word = word;
        this.english = english;
    }

    public Card (int backImage, int frontImage, Equation equation, boolean english) {
        this.backImage = backImage;
        this.frontImage = frontImage;
        this.equation = equation;
        this.english = english;
    }

    public int getBackImage() {
        return backImage;
    }

    public int getFrontImage() {
        return frontImage;
    }

    public String getText(){ return text;}
    public boolean getEnglish(){ return english;}
    public void setEnglish(boolean isEnglish){
        english = isEnglish;
    }
    public Word getWord(){ return word;}
    public Equation getEquation(){ return equation;}

    @Override
    public String toString() {
        String frontImageID = "" + frontImage;
        return frontImageID;
    }
}
