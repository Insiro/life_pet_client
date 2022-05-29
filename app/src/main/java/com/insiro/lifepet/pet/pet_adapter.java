package com.insiro.lifepet.pet;

import static java.lang.String.format;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;

import com.insiro.lifepet.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class pet_adapter extends BaseAdapter {
    ArrayList<pet_data> sample=new ArrayList<>();

    public void addItem(pet_data item){
        sample.add(item);
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
        final Context context=parent.getContext();
        final pet_data pet_data=sample.get(position);
        if(convertView==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.card_item,parent,false);
        }else{
            View view = new View(context);
            view=(View) convertView;
        }
        ImageView petImage =convertView.findViewById(R.id.item_image);
        petImage.setImageResource(R.drawable.cat_ilust);
        TextView petName=convertView.findViewById(R.id.item_name);
        TextView petCategory=convertView.findViewById(R.id.item_sub);
        TextView petLv=convertView.findViewById(R.id.item_desc);
        petImage.setImageResource(R.drawable.cat_ilust);
        petName.setText(sample.get(position).getPetName());
        petCategory.setText(sample.get(position).getPetCategory());
        petLv.setText("Lv: "+sample.get(position).getLevel());
        return convertView;
    }
}
