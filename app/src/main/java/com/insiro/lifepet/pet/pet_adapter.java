package com.insiro.lifepet.pet;

import static java.lang.String.format;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.insiro.lifepet.R;

import java.util.ArrayList;

public class pet_adapter extends BaseAdapter {
    Context pContext;
    LayoutInflater pLayoutInflater;
    ArrayList<pet_data> sample;

    public pet_adapter(Context context, ArrayList<pet_data> data){
        pContext=context;
        sample=data;
        pLayoutInflater=LayoutInflater.from(pContext);
    }
    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public Object getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=pLayoutInflater.inflate(R.layout.card_item,null);

        ImageView petImage =view.findViewById(R.id.item_image);
        TextView petCategory=view.findViewById(R.id.item_sub);
        TextView petLv=view.findViewById(R.id.item_desc);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        petImage.setImageResource(sample.get(position).getId());
        petCategory.setText(sample.get(position).getPetCategory());
        petLv.setText(format("%d", sample.get(position).getLevel()));
        return view;
    }
}
