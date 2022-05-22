package com.insiro.lifepet.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.insiro.lifepet.R;

public class PetDetail extends AppCompatActivity {
    Button nextPetBtn;
    Button listButton;
    Button prePetBtn;
    TextView petName;
    ImageView petImage;
    TextView petCategory;
    TextView petLv;
    TextView petExp;
    ProgressBar expProg;
    TextView petIntimacy;
    ProgressBar intimacyProg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        petName=findViewById(R.id.pet_detail_name);
        petImage=findViewById(R.id.pet_detail_catimg);
        petCategory=findViewById(R.id.pet_detail_category);
        petLv= findViewById(R.id.pet_detail_lv);
        petExp=findViewById(R.id.pet_detail_exp);
        expProg=findViewById(R.id.exp_progress);
        petIntimacy=findViewById(R.id.pet_detail_intimacy);
        intimacyProg=findViewById(R.id.friendship_progress);
        prePetBtn=findViewById(R.id.pet_detail_previous_button);
        listButton=findViewById(R.id.pet_detail_list_button);
        nextPetBtn=findViewById(R.id.pet_detail_next_button);
        Intent intent=new Intent();
        Bundle bundle=intent.getBundleExtra("pet");
        double exp=bundle.getDouble("exp");
        int lv=bundle.getInt("lv");
        double totalExp=500*Math.pow(1.2,lv-1);
        int intimacy=bundle.getInt("intimacy");
        String name=bundle.getString("name");
        String category=bundle.getString("category");
        petName.setText(name);
        petCategory.setText(category);
        petLv.setText("LV: "+lv);
        petExp.setText("Exp: "+exp+"/"+totalExp);
        expProg.setProgress((int) (exp/totalExp)*100);
        petIntimacy.setText("Friendship: "+intimacy+"/100");
        intimacyProg.setProgress(intimacy);


    }
}