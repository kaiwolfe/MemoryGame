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
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.cardImage);
        imageView.setImageResource(card.getBackImage());
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(),bitmap.getHeight(), true);
        imageView.setImageBitmap(scaledBitmap);
        imageView.setAdjustViewBounds(true);

        return convertView;
    }

}
