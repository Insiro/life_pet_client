package com.insiro.lifepet.pet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.insiro.lifepet.R;
import com.insiro.lifepet.ScheduleActivity;
import com.insiro.lifepet.achievement.Achievement;
import com.insiro.lifepet.dataManager.Action;
import com.insiro.lifepet.dataManager.DataManager;
import com.insiro.lifepet.dataManager.Field;
import com.insiro.lifepet.dataManager.Query;
import com.insiro.lifepet.dataManager.QueryBundleBuilder;
import com.insiro.lifepet.dataManager.QueryData;
import com.insiro.lifepet.dataManager.ResponseBundleReader;
import com.insiro.lifepet.entity.Pet;

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
    Pet pet;
    BottomNavigationView bottomNavigationView;
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
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("pet");
        String name=bundle.getString("name");
        String category=bundle.getString("category");
        int lv=bundle.getInt("level");
        double totalExp=500*Math.pow(1.2,lv-1);
        int intimacy=bundle.getInt("intimacy");
        double exp=bundle.getDouble("exp");
        petName.setText(name);
        petCategory.setText(category);
        petLv.setText("LV: "+lv);
        petExp.setText("Exp: "+exp+"/"+String.format("%.2f",totalExp));
        expProg.setProgress((int) (exp/totalExp)*100);
        petIntimacy.setText("Friendship: "+intimacy+"/100");
        intimacyProg.setProgress(intimacy);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu1:
                        Intent intent1 = new Intent(PetDetail.this, Achievement.class);
                        startActivity(intent1);
                        break;
                    case R.id.bottom_menu2:
                        Intent intent2 = new Intent(PetDetail.this, ScheduleActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottom_menu3:
                        Intent intent3 = new Intent(PetDetail.this, ScheduleActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.bottom_menu4:
                        Intent intent4 = new Intent(PetDetail.this, pet_info.class);
                        startActivity(intent4);
                }
                return true;
            }
        });

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent();
        Bundle bundle = intent.getExtras();
        ResponseBundleReader queryBundleReader = new ResponseBundleReader(bundle);
        QueryData resData = queryBundleReader.getData(true);
        pet = (Pet) resData.getData();
    }
}