package com.example.memorygame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class CardLayoutAdapter extends BaseAdapter {
    private final Context mContext;
    //private final ArrayList<Drawable> backImage;
    private ArrayList<Card> cards = new ArrayList<>();
    //private final ArrayList<String> text;
    static int choices = 0;
    static int image1;
    static int image2;
    static FlipCard firstFlip;
    static FlipCard secondFlip;
    static int position1;
    Runnable r = new Runnable() {
        public void run() {
            firstFlip.flipTheCardBack();
            secondFlip.flipTheCardBack();
            secondFlip.resetFlipCount();
        }
    };

    public CardLayoutAdapter(Context context, ArrayList<Card> cards) {
        this.mContext = context;
        //this.backImage = back;
        this.cards = cards;

    }

    // 2
    @Override
    public int getCount() {
        return cards.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    // 5
    @SuppressLint({"ClickableViewAccessibility", "InflateParams"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Card card = cards.get(position);
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.card_layout, null);
        }

        final ImageView imageViewBack = (ImageView) convertView.findViewById(R.id.cardImageBack);
        imageViewBack.setImageResource(card.getBackImage());
        BitmapDrawable drawable = (BitmapDrawable) imageViewBack.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        imageViewBack.setImageBitmap(scaledBitmap);
        imageViewBack.setAdjustViewBounds(true);

        final ImageView imageViewFront = (ImageView) convertView.findViewById(R.id.cardImageFront);
        imageViewFront.setImageResource(card.getFrontImage());
        BitmapDrawable drawable2 = (BitmapDrawable) imageViewFront.getDrawable();
        Bitmap bitmap2 = drawable2.getBitmap();
        Bitmap scaledBitmap2 = Bitmap.createScaledBitmap(bitmap2, bitmap2.getWidth(), bitmap2.getHeight(), true);
        imageViewFront.setImageBitmap(scaledBitmap2);
        imageViewFront.setAdjustViewBounds(true);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FlipCard flipCard = (FlipCard) v;
                Card choice = (Card) getItem(position);
                System.out.println("Position: " + position);
                if(flipCard.getFlipCount() == 0){
                    flipCard.flipTheCard();
                    image1 = choice.getBackImage();
                    firstFlip = flipCard;
                    position1 = position;
                } else if (flipCard.getFlipCount() == 1){
                    if(position1 == position)
                        return;
                    flipCard.flipTheCard();
                    image2 = choice.getBackImage();
                    secondFlip = flipCard;
                    if (image1 == image2){
                        flipCard.resetFlipCount();
                        System.out.println("IT'S A MATCH!");
                    } else {
                        System.out.println("Not a match!");
                        v.postDelayed(r, 1500);
                    }
                } else {
                    return;
                }
            }
        });

//        final EasyFlipView easyFlipView = (EasyFlipView) convertView;
//        easyFlipView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                ((EasyFlipView) v).flipTheView();
//                Log.d("TOUCH EVENT", "Detected");
//                System.out.println("TOUCH EVENT : Detected Touch");
//
//
//                choices++;
//                System.out.println("Choices: " + choices);
//
//                if(choices == 1){
//                    position1 = position;
//                    image1 = choice.getBackImage();
//                    flip1 = ((EasyFlipView)v);
//                    flip1.setFlipEnabled(false);
//                } else if (choices == 2){
//                    position2 = position;
//                    if (position1 != position2){
//                        //Gameplay.disableGridView();
//                        image2 = choice.getBackImage();
//                        flip2 = ((EasyFlipView)v);
//                        System.out.println("First Card: " + image1);
//                        System.out.println("Second Card: " + image2);
//                        choices = 0;
//                        if (image1 == image2) {
//                            System.out.println("MATCH FOUND!");
//                            Gameplay.enableGridView(this);
//                            flip1.setVisibility(View.INVISIBLE);
//                            flip1.setVisibility(View.INVISIBLE);
//                        }else{
//                            System.out.println("No match found. Try again.");
//                            try {
//                                //TimeUnit.SECONDS.sleep(1);
//                                ((EasyFlipView)v).flipTheView();
//                                flip1.flipTheView();
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            Gameplay.enableGridView(this);
//                        }
//                    }else{
//                        choices--;
//                    }
//                }
//
////
////                    if (image1 == image2) {
////                        System.out.println("IT'S A MATCH!");
////                        try {
////                            TimeUnit.MILLISECONDS.sleep(500);
////                            flip1.setAutoFlipBack(false);
////                            flip2.setAutoFlipBack(false);
////                            flip1.setFlipOnTouch(false);
////                            flip2.setFlipOnTouch(false);
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
////                        choices = 0;
////                        Gameplay.enableGridView();
////                    } else {
////                        System.out.println("No match, try again!");
////                        try {
////                            TimeUnit.SECONDS.sleep(1);
////                            flip1.flipTheView();
////                            flip2.flipTheView();
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
////                        choices = 0;
////                        Gameplay.enableGridView();
////                    }
////                }
//                return false;
//            }
//        });
//
//            easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
//                @Override
//                public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide) {
//                    Card choice = (Card) getItem(position);
//                    choices++;
//                    if (choices == 1) {
//                        image1 = choice.getBackImage();
//                        System.out.println("First Card: " + image1);
//                        flip1 = (EasyFlipView) flipView;
//                        flip1.flipTheView();
//                        flip1.setAutoFlipBack(false);
//                    } else if (choices == 2) {
//                        image2 = choice.getBackImage();
//                        System.out.println("Second Card: " + image2);
//                        flip2 = (EasyFlipView) flipView;
//                        flip2.flipTheView();
//                        Gameplay.disableGridView();
//                        if (image1 == image2) {
//                            System.out.println("IT'S A MATCH!");
//                            try {
//                                TimeUnit.MILLISECONDS.sleep(500);
//                                flip1.setAutoFlipBack(false);
//                                flip2.setAutoFlipBack(false);
//                                flip1.setFlipOnTouch(false);
//                                flip2.setFlipOnTouch(false);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            choices = 0;
//                            Gameplay.enableGridView();
//                        } else {
//                            System.out.println("No match, try again!");
//                            try {
//                                Gameplay.enableGridView();
//                                TimeUnit.SECONDS.sleep(1);
//                                flip1.flipTheView();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            flip2.setAutoFlipBack(true);
//                        }
//                    } else {
//                        if (choices == 4) {
//                            choices = 0;
//                            Gameplay.enableGridView();
//                        }
//                    }
//                }
//            });
            return convertView;
        }
}
