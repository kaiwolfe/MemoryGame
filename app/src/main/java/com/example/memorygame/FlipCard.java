package com.example.memorygame;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FlipCard extends RelativeLayout {
    private Context context;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private AnimatorSet mSetRightOutText;
    private AnimatorSet mSetLeftInText;
    private boolean mIsBackVisible = false;
    private ImageView cardFront;
    private ImageView cardBack;
    private TextView cardText;
    private static int flipCount = 0;
    static boolean sfxOn = true;

    public FlipCard(Context context){
        super(context);
        this.context = context;
    }

    public FlipCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public FlipCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        findViews();
        //changeCameraDistance();
        loadAnimations();
        //cardFlipUp = MediaPlayer.create(context, R.raw.card_up);
        //cardFlipDown = MediaPlayer.create(context, R.raw.card_down);
    }

//    private void changeCameraDistance() {
//        int distance = 8000;
//        float scale = getResources().getDisplayMetrics().density * distance;
//        cardFront.setCameraDistance(scale);
//        cardBack.setCameraDistance(scale);
//    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.in_animation);
        mSetRightOutText = (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.out_animation);
        mSetLeftInText = (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.in_animation);
    }

    private void findViews() {
        this.cardBack = (ImageView)findViewById(R.id.cardImageBack);
        this.cardFront = (ImageView)findViewById(R.id.cardImageFront);
        this.cardText = (TextView)findViewById(R.id.cardText);
    }

    public void flipTheCard() {
        flipCount++;
        AudioPlay.playFlipUpSFX(sfxOn);
        if (getFlipCount() > 2) return;
        //if(mSetRightOut.isRunning() || mSetLeftIn.isRunning()) return;
        if (getChildCount() < 2) return;
        //r.run();
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(cardFront);
            mSetLeftInText.setTarget(cardText);
            mSetLeftIn.setTarget(cardBack);
            mSetRightOut.start();
            mSetLeftIn.start();
            mSetLeftInText.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(cardBack);
            mSetLeftIn.setTarget(cardFront);
            mSetRightOutText.setTarget(cardText);
            mSetRightOut.start();
            mSetLeftIn.start();
            mSetRightOutText.start();
            mIsBackVisible = false;
        }
    }

    public void flipTheCardBack() {
        resetFlipCount();
        //if(mSetRightOut.isRunning() || mSetLeftIn.isRunning()) return;
        //s.run();
        AudioPlay.playFlipDownSFX(sfxOn);
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(cardFront);
            mSetLeftInText.setTarget(cardText);
            mSetLeftIn.setTarget(cardBack);
            mSetRightOut.start();
            mSetLeftIn.start();
            mSetLeftInText.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(cardBack);
            mSetLeftIn.setTarget(cardFront);
            mSetRightOutText.setTarget(cardText);
            mSetRightOut.start();
            mSetLeftIn.start();
            mSetRightOutText.start();
            mIsBackVisible = false;
        }
    }

    public void resetFlipCount() {
        flipCount = 0;
    }
    public int getFlipCount(){
        return flipCount;
    }

    public static void setflipCardSFX(boolean on){
        sfxOn = on;
    }
}
