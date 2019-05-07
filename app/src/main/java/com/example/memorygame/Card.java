package com.example.memorygame;

public class Card {
    private int backImage;
    private int frontImage;
    private String text;
    private Word word;
    private Equation equation;
    boolean englishOrExpression;


    public Card (int backImage, int frontImage) {
        this.backImage = backImage;
        this.frontImage = frontImage;
    }

    public Card (int backImage, int frontImage, String text) {
        this.backImage = backImage;
        this.frontImage = frontImage;
        this.text = text;
    }

    public Card (int backImage, int frontImage, Word word, boolean englishOrExpression) {
        this.backImage = backImage;
        this.frontImage = frontImage;
        this.word = word;
        this.englishOrExpression = englishOrExpression;
    }

    public Card (int backImage, int frontImage, Equation equation, boolean englishOrExpression) {
        this.backImage = backImage;
        this.frontImage = frontImage;
        this.equation = equation;
        this.englishOrExpression = englishOrExpression;
    }

    public Card (int backImage, int frontImage, Word word, Equation equation, boolean englishOrExpression) {
        this.backImage = backImage;
        this.frontImage = frontImage;
        this.equation = equation;
        this.englishOrExpression = englishOrExpression;
        this.word = word;
    }

    public int getBackImage() {
        return backImage;
    }

    public int getFrontImage() {
        return frontImage;
    }

    public String getText(){ return text;}
    public boolean getEnglishOrExpression(){ return englishOrExpression;}

    public void setEnglishOrExpression(boolean isEnglishOrExpression){
        englishOrExpression = isEnglishOrExpression;
    }
    public Word getWord(){ return word;}
    public Equation getEquation(){ return equation;}

    @Override
    public String toString() {
        String frontImageID = "" + frontImage;
        return frontImageID;
    }
}
