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
import android.widget.TextView;

import java.util.ArrayList;

public class CardLayoutAdapterWords extends BaseAdapter {
    private final Context mContext;
    private ArrayList<Card> cards;

    static Word word1;
    static Word word2;
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

    Runnable s = new Runnable() {
        public void run() {
            Gameplay.addPair();
            Gameplay.compareMatchedToNumPairs();
            firstFlip.setVisibility(View.INVISIBLE);
            secondFlip.setVisibility(View.INVISIBLE);
            secondFlip.resetFlipCount();
        }
    };



    public CardLayoutAdapterWords(Context context, ArrayList<Card> cards) {
        this.mContext = context;
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
        final TextView textViewFront = (TextView) convertView.findViewById(R.id.cardText);
        Word word = card.getWord();
        if(card.getEnglishOrExpression()){
            textViewFront.setText((word.getWord("eng")));
        } else {
            textViewFront.setText((word.getWord(Gameplay.getLanguage())));
        }
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
                    word1 = choice.getWord();
                    firstFlip = flipCard;
                    position1 = position;
                } else if (flipCard.getFlipCount() == 1){
                    if(position1 == position)
                        return;
                    flipCard.flipTheCard();
                    word2 = choice.getWord();
                    secondFlip = flipCard;
                    if (word1.getWord().equals(word2.getWord())){
                        System.out.println("IT'S A MATCH!");
                        v.postDelayed(s, 500);
                        flipCard.resetFlipCount();
                    } else {
                        System.out.println("Not a match!");
                        v.postDelayed(r, 1000);
                    }
                } else {
                    return;
                }
            }
        });
            return convertView;
        }

    public void updateItems (ArrayList<Card> cardList){
        cards.clear();
        if(cards.isEmpty())
            System.out.println("EMPTIED LIST");
        cards.addAll(cardList);
        System.out.println(cards.size());
        notifyDataSetChanged();
    }
}
