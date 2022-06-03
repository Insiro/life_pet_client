package com.insiro.lifepet.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.insiro.lifepet.R;
import com.insiro.lifepet.entity.Pet;

import java.util.ArrayList;

public class pet_adapter extends BaseAdapter {
    ArrayList<Pet> sample = new ArrayList<>();

    public void addItem(Pet item) {
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
        final Context context = parent.getContext();
        final Pet pet_data = sample.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card_item, parent, false);
        } else {
            View view = new View(context);
            view = (View) convertView;
        }
        ImageView petImage = convertView.findViewById(R.id.item_image);
        petImage.setImageResource(R.drawable.cat_ilust);
        TextView petName = convertView.findViewById(R.id.item_name);
        TextView petCategory = convertView.findViewById(R.id.item_sub);
        TextView petLv = convertView.findViewById(R.id.item_desc);
        petImage.setImageResource(R.drawable.cat_ilust);
        petName.setText(sample.get(position).getName());
        petCategory.setText(sample.get(position).getCategory());
        petLv.setText("Lv: " + sample.get(position).getLevel());
        return convertView;
    }
}
