package com.example.memorygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Card card = cards.get(position);
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.card_layout, null);
        }
        final ImageView imageViewBack = (ImageView)convertView.findViewById(R.id.cardImageBack);
        imageViewBack.setImageResource(card.getBackImage());
        BitmapDrawable drawable = (BitmapDrawable) imageViewBack.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(),bitmap.getHeight(), true);
        imageViewBack.setImageBitmap(scaledBitmap);
        imageViewBack.setAdjustViewBounds(true);
        final ImageView imageViewFront = (ImageView)convertView.findViewById(R.id.cardImageFront);
        imageViewFront.setImageResource(card.getFrontImage());
        BitmapDrawable drawable2 = (BitmapDrawable) imageViewFront.getDrawable();
        Bitmap bitmap2 = drawable2.getBitmap();
        Bitmap scaledBitmap2 = Bitmap.createScaledBitmap(bitmap2, bitmap2.getWidth(),bitmap2.getHeight(), true);
        imageViewFront.setImageBitmap(scaledBitmap2);
        imageViewFront.setAdjustViewBounds(true);

        return convertView;
    }

}
