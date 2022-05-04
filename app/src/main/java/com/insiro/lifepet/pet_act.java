package com.insiro.lifepet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class pet_act extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        ProgressBar progressBar =findViewById(R.id.act_pet_progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        Button btn1=findViewById(R.id.act_pet_easy);
        Button btn2=findViewById(R.id.act_pet_normal);
        Button btn3=findViewById(R.id.act_pet_hard);
        Button btn4=findViewById(R.id.act_pet_dilemma);
        Button btn1F=findViewById(R.id.act_pet_easyF);
        Button btn2F=findViewById(R.id.act_pet_normalF);
        Button btn3F=findViewById(R.id.act_pet_hardF);
        Button btn4F=findViewById(R.id.act_pet_dilemmaF);
        TextView level=findViewById(R.id.act_pet_Lv);
        TextView xp=findViewById(R.id.act_pet_exp);
        ImageButton cat_img1=findViewById(R.id.act_pet_catimg1);
        ImageButton cat_img2=findViewById(R.id.act_pet_catimg2);
        TextView friends=findViewById(R.id.act_pet_friend);
        level.setText(pet_func.charLv+"");
        friends.setText(pet_func.friendly+"");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,0,true);
                if(pet_func.charLv==10){
                    pet_func.change_img(cat_img1,cat_img2);
                    Toast.makeText(getApplicationContext(),"Level up",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,1,true);
                if(pet_func.charLv==10){
                    pet_func.change_img(cat_img1,cat_img2);
                    Toast.makeText(getApplicationContext(),"Level up",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,2,true);
                if(pet_func.charLv==10){
                    pet_func.change_img(cat_img1,cat_img2);
                    Toast.makeText(getApplicationContext(),"Level up",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,3,true);
                if(pet_func.charLv==10){
                    pet_func.change_img(cat_img1,cat_img2);
                    Toast.makeText(getApplicationContext(),"Level up",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn1F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,0,false);            }
        });
        btn2F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,1,false);            }
        });
        btn3F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,2,false);            }
        });
        btn4F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pet_func.react_exp(progressBar,xp,friends,level,3,false);            }
        });
    }

}