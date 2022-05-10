package com.insiro.lifepet.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.insiro.lifepet.R;
import com.insiro.lifepet.pet.pet_data;
import com.insiro.lifepet.pet.pet_func;

import java.util.ArrayList;

public class pet_adapter extends BaseAdapter {
    Context pContext=null;
    LayoutInflater pLayoutInflater=null;
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
        View view=pLayoutInflater.inflate(R.layout.petlistview,null);

        ImageView petImage =view.findViewById(R.id.pet_info_petimg);
        TextView petId=view.findViewById(R.id.pet_info_id);
        TextView petCategory=view.findViewById(R.id.pet_info_category);
        TextView petLv=view.findViewById(R.id.pet_info_Lv);
        TextView petExp=view.findViewById(R.id.pet_info_exp);
        TextView petFriendly=view.findViewById(R.id.pet_info_friendly);

        petImage.setImageResource(sample.get(position).getId());
        petId.setText(sample.get(position).getId()+"");
        petCategory.setText(sample.get(position).getPetCategory());
        petLv.setText(sample.get(position).getLevel()+"");
        petExp.setText(sample.get(position).getExp()+"/"+ pet_func.totalExp);
        petFriendly.setText(sample.get(position).getIntimacy()+"/100");
        return view;
    }
}
