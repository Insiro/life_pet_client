package com.insiro.lifepet.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.insiro.lifepet.R;

public class PetDetail extends AppCompatActivity {
    Button listButton;
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
        petName = findViewById(R.id.pet_detail_name);
        petImage = findViewById(R.id.pet_detail_catimg);
        petCategory = findViewById(R.id.pet_detail_category);
        petLv = findViewById(R.id.pet_detail_lv);
        petExp = findViewById(R.id.pet_detail_exp);
        expProg = findViewById(R.id.pet_detail_friendship_progress);
        petIntimacy = findViewById(R.id.pet_detail_intimacy);
        intimacyProg = findViewById(R.id.pet_detail_friendship_progress);
        listButton = findViewById(R.id.pet_detail_list_button);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("pet");
        String name = bundle.getString("name");
        String category = bundle.getString("category");
        int lv = bundle.getInt("level");
        double totalExp = 500 * Math.pow(1.2, lv - 1);
        int intimacy = bundle.getInt("intimacy");
        double exp = bundle.getDouble("exp");
        petName.setText(name);
        petCategory.setText(category);
        petLv.setText("LV: " + lv);
        petExp.setText("Exp: " + exp + "/" + String.format("%.2f", totalExp));
        expProg.setProgress((int) (exp / totalExp) * 100);
        petIntimacy.setText("Friendship: " + intimacy + "/100");
        intimacyProg.setProgress(intimacy);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}