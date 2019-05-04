package com.example.memorygame;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FlipCard extends RelativeLayout {
    private Context context;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private ImageView cardFront;
    private ImageView cardBack;
    private static int flipCount = 0;
    FlipCard firstSelection;

    public FlipCard(Context context){
        super(context);
        this.context = context;
    }

    public FlipCard(Context context, AttributeSet attributeSet){
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
    }

    private void findViews() {
        this.cardBack = (ImageView)findViewById(R.id.cardImageBack);
        this.cardFront = (ImageView)findViewById(R.id.cardImageFront);
    }

    public void flipTheCard() {
        flipCount++;
        if (getFlipCount() > 2) return;
        //if(mSetRightOut.isRunning() || mSetLeftIn.isRunning()) return;
        if (getChildCount() < 2) return;
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(cardFront);
            mSetLeftIn.setTarget(cardBack);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(cardBack);
            mSetLeftIn.setTarget(cardFront);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }

    public void flipTheCardBack() {
        resetFlipCount();
        //if(mSetRightOut.isRunning() || mSetLeftIn.isRunning()) return;
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(cardFront);
            mSetLeftIn.setTarget(cardBack);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(cardBack);
            mSetLeftIn.setTarget(cardFront);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }

    public void resetFlipCount() {
        flipCount = 0;
    }
    public int getFlipCount(){
        return flipCount;
    }
}
