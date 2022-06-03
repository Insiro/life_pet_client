package com.insiro.lifepet.achievement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.insiro.lifepet.NavigationBar;
import com.insiro.lifepet.R;

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

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner = (Spinner) findViewById(R.id.achieve_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arrayList.get(i) + "이 선택되었습니다.", Toast.LENGTH_SHORT).show();

                if(i==0){
                    ImageView imageView = findViewById(R.id.achieve_image1);
                    TextView textView = findViewById(R.id.achieve_text1);
                    imageView.setImageResource(R.drawable.gold);
                    textView.setText("습관 5일 연속 달성하기\n달성시간: 2022. 04. 16 14:34");

                }
                else{
                    ImageView imageView = findViewById(R.id.achieve_image1);
                    TextView textView = findViewById(R.id.achieve_text1);
                    imageView.setImageResource(R.drawable.silver);
                    textView.setText("습관 10일 연속 달성하기\n진행도: 80%");
                }
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

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                it -> new NavigationBar(this).onNavigationItemSelected(it));
    }
}




