package com.insiro.lifepet.pet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.insiro.lifepet.R;
import com.insiro.lifepet.dataManager.Action;
import com.insiro.lifepet.dataManager.DataManager;
import com.insiro.lifepet.dataManager.Field;
import com.insiro.lifepet.dataManager.Query;
import com.insiro.lifepet.dataManager.QueryBundleBuilder;
import com.insiro.lifepet.dataManager.QueryData;
import com.insiro.lifepet.dataManager.ResponseBundleReader;
import com.insiro.lifepet.entity.Pet;
import com.insiro.lifepet.pet.pet_func;

import java.util.ArrayList;
import java.util.BitSet;

public class pet_act extends AppCompatActivity {
    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        ProgressBar progressBar = findViewById(R.id.act_pet_progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        Button btn1 = findViewById(R.id.act_pet_easy);
        Button btn2 = findViewById(R.id.act_pet_normal);
        Button btn3 = findViewById(R.id.act_pet_hard);
        Button btn4 = findViewById(R.id.act_pet_dilemma);
        Button btn1F = findViewById(R.id.act_pet_easyF);
        Button btn2F = findViewById(R.id.act_pet_normalF);
        Button btn3F = findViewById(R.id.act_pet_hardF);
        Button btn4F = findViewById(R.id.act_pet_dilemmaF);
        TextView level = findViewById(R.id.act_pet_Lv);
        TextView xp = findViewById(R.id.act_pet_exp);
        ImageButton cat_img1 = findViewById(R.id.act_pet_catimg1);
        ImageButton cat_img2 = findViewById(R.id.act_pet_catimg2);
        TextView friends = findViewById(R.id.act_pet_friend);
        TextView category = findViewById(R.id.act_pet_category);
        level.setText(pet_func.charLv + "");
        friends.setText(pet_func.friendly + "");
        getData();
        category.setText(pet.getCategory());
        btn1.setOnClickListener(view -> {
            pet_func.react_exp(progressBar, xp, friends, level, 0, true);
            if (pet_func.charLv == 10) {
                pet_func.change_img(cat_img1, cat_img2);
                Toast.makeText(getApplicationContext(), "Level up", Toast.LENGTH_SHORT).show();
            }
            update();
        });
        btn2.setOnClickListener(view -> {
            pet_func.react_exp(progressBar, xp, friends, level, 1, true);
            if (pet_func.charLv == 10) {
                pet_func.change_img(cat_img1, cat_img2);
                Toast.makeText(getApplicationContext(), "Level up", Toast.LENGTH_SHORT).show();
            }
            update();
        });
        btn3.setOnClickListener(view -> {
            pet_func.react_exp(progressBar, xp, friends, level, 2, true);
            if (pet_func.charLv == 10) {
                pet_func.change_img(cat_img1, cat_img2);
                Toast.makeText(getApplicationContext(), "Level up", Toast.LENGTH_SHORT).show();
            }
            update();
        });
        btn4.setOnClickListener(view -> {
            pet_func.react_exp(progressBar, xp, friends, level, 3, true);
            if (pet_func.charLv == 10) {
                pet_func.change_img(cat_img1, cat_img2);
                Toast.makeText(getApplicationContext(), "Level up", Toast.LENGTH_SHORT).show();
            }
            update();
        });
        btn1F.setOnClickListener(view -> pet_func.react_exp(progressBar, xp, friends, level, 0, false));
        btn2F.setOnClickListener(view -> pet_func.react_exp(progressBar, xp, friends, level, 1, false));
        btn3F.setOnClickListener(view -> pet_func.react_exp(progressBar, xp, friends, level, 2, false));
        btn4F.setOnClickListener(view -> pet_func.react_exp(progressBar, xp, friends, level, 3, false));
    }

    public void getData() {
        QueryBundleBuilder Builder = new QueryBundleBuilder();
        Query load_request = new Query(Field.Pets, Action.Activate, 0);
        Query requestDataQuery = new Query(Field.Pets, Action.Get, 0);
        Builder.addQuery(load_request, null);
        Builder.addQuery(requestDataQuery, null);
        Bundle requestBundle = new Bundle();
        requestBundle = Builder.build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtra("requestBundle", requestBundle);
        startActivityForResult(intent, 1);

    }

    public void update() {
        QueryBundleBuilder Builder = new QueryBundleBuilder();
        Query load_request = new Query(Field.Pets, Action.Activate, 0);
        Query requestDataQuery = new Query(Field.Pets, Action.Update, -1);
        Pet pet = new Pet("0",pet_func.name, pet_func.category, pet_func.friendly, pet_func.exp, pet_func.charLv);
        QueryData newData = new QueryData(pet, Field.Pets, false);
        Builder.addQuery(load_request, null);
        Builder.addQuery(requestDataQuery, newData);
        Bundle requestBundle = Builder.build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtra("requestBundl   e", requestBundle);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent();
        Bundle bundle = intent.getExtras();
        ResponseBundleReader queryBundleReader = new ResponseBundleReader(bundle);
        QueryData resData = queryBundleReader.getData(true);
        pet = (Pet) resData.getData();
        pet_func.setCharLv(pet.getLevel());
        pet_func.setExp(pet.getExp());
        pet_func.setTotalExp(pet.getLevel());
        pet_func.setFriendly(pet.getIntimacy());
        pet_func.setCategory(pet.getCategory());
        pet_func.setName(pet.getName());
    }
}