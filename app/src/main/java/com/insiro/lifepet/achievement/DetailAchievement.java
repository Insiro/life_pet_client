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
        String category = bundle.getString("category");

        if(category.equals("attend"))
            imageView.setImageResource(R.drawable.logo);
        else if(category.equals("achieve"))
            imageView.setImageResource(R.drawable.achieve_ilust);
        else if(category.equals("grow_up"))
            imageView.setImageResource(R.drawable.cat_ilust);
        else{
            //안드로이드 기본 이미지 삽입
        }

        text = name + "\n달성률: " + rate +"%";

        textView.setText(text);

    }
}
