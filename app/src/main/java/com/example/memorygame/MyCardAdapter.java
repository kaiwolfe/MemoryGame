package com.example.memorygame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyCardAdapter extends RecyclerView.Adapter< CardViewHolder > {

private Context mContext;
private List< Card > mCardList;

    MyCardAdapter(Context mContext, List< Card > mCardList) {
    this.mContext = mContext;
    this.mCardList = mCardList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new CardViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        cardViewHolder.mImage.setImageResource(mCardList.get(i).getBackImage());
    }


    @Override
    public int getItemCount()    {
        return mCardList.size();
    }
}

class CardViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;

    CardViewHolder(View itemView) {
        super(itemView);

        ImageView mImage = itemView.findViewById(R.id.cardImage);
    }
}
