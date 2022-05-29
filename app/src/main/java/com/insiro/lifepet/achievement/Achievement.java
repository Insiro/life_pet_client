package com.insiro.lifepet.achievement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.insiro.lifepet.DashBoard;
import com.insiro.lifepet.R;
import com.insiro.lifepet.ScheduleActivity;
import com.insiro.lifepet.pet.pet_info;

import java.util.ArrayList;


public class Achievement extends AppCompatActivity {
    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Button plus;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);

        arrayList = new ArrayList<>();
        arrayList.add("달성 업적");
        arrayList.add("미달성 업적");
        arrayList.add("시작 가능한 업적");
        arrayList.add("진행중인 업적");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner = (Spinner)findViewById(R.id.achieve_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"이 선택되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        plus = findViewById(R.id.achieve_detail);
        plus.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AdditionalAchievement.class);
            Bundle bundle = new Bundle();
            bundle.putInt("option", spinner.getSelectedItemPosition());
            intent.putExtras(bundle);

            startActivity(intent);
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.achievement_bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu1:
                        Intent intent1 = new Intent(Achievement.this, DashBoard.class);
                        startActivity(intent1);
                        break;
                    case R.id.bottom_menu2:
                        Intent intent2 = new Intent(Achievement.this, ScheduleActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottom_menu3:
                        Intent intent3 = new Intent(Achievement.this, pet_info.class);
                        startActivity(intent3);
                        break;
                    case R.id.bottom_menu4:
                        Intent intent4 = new Intent(Achievement.this, Achievement.class);
                        startActivity(intent4);
                }
                return true;
            }
        });
    }

}




