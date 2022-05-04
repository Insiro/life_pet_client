package com.insiro.lifepet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class pet_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        ImageView petImage =findViewById(R.id.pet_info_petimg);
        TextView petName=findViewById(R.id.pet_info_name);
        TextView petLv=findViewById(R.id.pet_info_Lv);
        TextView petExp=findViewById(R.id.pet_info_exp);
        TextView petFriendly=findViewById(R.id.pet_info_friendly);
        ListView petList=findViewById(R.id.pet_info_petlist);

        petName.setText("길고양이");
        petLv.setText(pet_func.charLv+"");
        petExp.setText(pet_func.exp/pet_func.totalExp+"");
        petFriendly.setText(pet_func.friendly+"/100");


    }
}