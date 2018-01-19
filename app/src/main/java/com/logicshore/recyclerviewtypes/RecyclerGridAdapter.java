package com.logicshore.recyclerviewtypes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 05-12-2017.
 */

class RecyclerGridAdapter extends RecyclerView.Adapter{
    Context context;
    ArrayList<Integer> arrayImage;
    ArrayList<String> arrayNames;

    public RecyclerGridAdapter(Context recyclerGridView, ArrayList personImages, ArrayList personNames) {
        context=recyclerGridView;
        arrayImage=personImages;
        arrayNames=personNames;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_grid_item,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MyViewHolder v =  (MyViewHolder) holder;
      //  ((MyViewHolder) holder).image.setImageResource(arrayImage.get(position));

        v.image.setImageResource(arrayImage.get(position));
        v.name.setText(arrayNames.get(position));



    }

    @Override
    public int getItemCount() {
        return arrayNames.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.image);
            name=(TextView)itemView.findViewById(R.id.name);
        }
    }
}
