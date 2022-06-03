package com.insiro.lifepet.achievement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.insiro.lifepet.R;

public class DetailAchievement extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_achievement);

        imageView = findViewById(R.id.detail_achievement_image);
        textView = findViewById(R.id.detail_achievement_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String text="";
        String name = bundle.getString("name");
        String rate = bundle.getString("rate");

        int achievement_rate =Integer.parseInt(rate);

        if(achievement_rate > 0 && achievement_rate <50)
            imageView.setImageResource(R.drawable.cooper);
        else if(achievement_rate > 50 && achievement_rate < 100)
            imageView.setImageResource(R.drawable.silver);
        else if(achievement_rate == 100)
            imageView.setImageResource(R.drawable.gold);
        else{

        }

        text = "업적: " + name + "\n달성률: " + rate +"%";

        textView.setText(text);

    }
}
