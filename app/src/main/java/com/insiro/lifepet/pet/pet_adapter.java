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
                Bundle bundle=new Bundle();
                bundle.putString("name",sample.get(position).getPetName());
                bundle.putString("category",sample.get(position).getPetCategory());
                bundle.putInt("id",sample.get(position).getId());
                bundle.putInt("level",sample.get(position).getLevel());
                bundle.putInt("intimacy",sample.get(position).getIntimacy());
                bundle.putDouble("exp",sample.get(position).getExp());
                Intent intent= new Intent(pContext,PetDetail.class);
                intent.putExtra("pet",bundle);
                pContext.startActivity(intent);
            }
        });
        petImage.setImageResource(sample.get(position).getId());
        petCategory.setText(sample.get(position).getPetCategory());
        petLv.setText(format("%d", sample.get(position).getLevel()));
        return view;
    }
}
