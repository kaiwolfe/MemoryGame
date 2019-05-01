package com.example.memorygame;

public class Card {
    private int backImage;
    private int frontImage;


    public Card (int backImage, int frontImage) {
        this.backImage = backImage;
        this.frontImage = frontImage;
    }

    public int getBackImage() {
        return backImage;
    }

    public int getFrontImage() {
        return frontImage;
    }
}
