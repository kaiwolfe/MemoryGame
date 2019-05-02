package com.example.memorygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CardLayoutAdapter extends BaseAdapter {
    private final Context mContext;
    //private final ArrayList<Drawable> backImage;
    private ArrayList<Card> cards = new ArrayList<>();
    //private final ArrayList<String> text;
    static int choices = 0;
    static int image1;
    static int image2;
    static EasyFlipView flip1;
    static EasyFlipView flip2;

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

        final EasyFlipView easyFlipView = (EasyFlipView) convertView;
        easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {
                Card choice = (Card) getItem(position);

                choices++;
                if (choices == 1){
                    image1 = choice.getBackImage();
                    System.out.println("First Card: " + image1);
                    flip1 = flipView;
                    flipView.setAutoFlipBack(false);
                }
                else if (choices == 2) {
                    image2 = choice.getBackImage();
                    System.out.println("Second Card: " + image2);
                    flip2 = flipView;
                    if(image1 == image2){
                        System.out.println("IT'S A MATCH!");
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);

                            flip1.setOnFlipListener(null);
                            flip1.setAutoFlipBack(false);
                            flip2.setOnFlipListener(null);
                            flip2.setAutoFlipBack(false);
                            flip1.setFlipOnTouch(false);
                            flip2.setFlipOnTouch(false);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        choices = 0;
                    } else {
                        System.out.println("No match, try again!");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            flip1.flipTheView();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        flipView.setAutoFlipBack(true);
                    }
                } else {
                    if (choices == 4)
                        choices = 0;
                }
            }
        });
        return easyFlipView;
    }

    public void flipViewsBack(EasyFlipView flip1, EasyFlipView flip2) {
        EasyFlipView first = flip1;
        EasyFlipView second = flip2;

        first.flipTheView();
        second.flipTheView();
    }
}
