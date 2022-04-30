package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    public float exp=0;
    public int friendly=0;
    public int charLv=0;
    public float totalExp=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar progressBar =findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        Button btn1=findViewById(R.id.easy);
        Button btn2=findViewById(R.id.normal);
        Button btn3=findViewById(R.id.hard);
        Button btn4=findViewById(R.id.dilemma);
        TextView level=findViewById(R.id.Lv);
        TextView xp=findViewById(R.id.exp);
        TextView friends=findViewById(R.id.friend);
        level.setText(charLv+"");
        friends.setText(friendly+"");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendly=gain_friendly(0,true,friendly);
                exp+=gain_exp(0,exp,friendly);
                progressBar.setProgress((int)((exp/totalExp)*100));
                xp.setText((int)exp+"/"+(int)totalExp);
                friends.setText(friendly+"");
                if(progressBar.getProgress()==100){
                    exp-=totalExp;
                    totalExp*=1.2;
                    charLv++;
                    level.setText(charLv+"");
                    progressBar.setProgress((int)((exp/totalExp)*100));
                    xp.setText((int)exp+"/"+(int)totalExp);

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendly=gain_friendly(1,true,friendly);
                exp+=gain_exp(1,exp,friendly);
                progressBar.setProgress((int)((exp/totalExp)*100));
                xp.setText((int)exp+"/"+(int)totalExp);
                if(progressBar.getProgress()==100){
                    exp-=totalExp;
                    totalExp*=1.2;
                    charLv++;
                    level.setText(charLv+"");
                    progressBar.setProgress((int)((exp/totalExp)*100));
                    xp.setText((int)exp+"/"+(int)totalExp);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendly=gain_friendly(2,true,friendly);
                exp+=gain_exp(2,exp,friendly);
                progressBar.setProgress((int)((exp/totalExp)*100));
                xp.setText((int)exp+"/"+(int)totalExp);
                if(progressBar.getProgress()==100){
                    exp-=totalExp;
                    totalExp*=1.2;
                    charLv++;
                    level.setText(charLv+"");
                    progressBar.setProgress((int)((exp/totalExp)*100));
                    xp.setText((int)exp+"/"+(int)totalExp);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendly=gain_friendly(3,true,friendly);
                exp+=gain_exp(3,exp,friendly);
                progressBar.setProgress((int)((exp/totalExp)*100));
                xp.setText((int)exp+"/"+(int)totalExp);
                if(progressBar.getProgress()==100){
                    exp-=totalExp;
                    totalExp*=1.2;
                    charLv++;
                    level.setText(charLv+"");
                    progressBar.setProgress((int)((exp/totalExp)*100));
                    xp.setText((int)exp+"/"+(int)totalExp);
                }
            }
        });
    }
    public float gain_exp(int achieve,float exp, float friendly) {
        if(achieve==0)
            exp=50*((100+friendly)/100);
        else if(achieve==1)
            exp=75*((100+friendly)/100);
        else if(achieve==2)
            exp=100*((100+friendly)/100);
        else if (achieve==3)
            exp=150*((100+friendly)/100);
        else
            exp=0;
        return exp;
    }
    public int gain_friendly(int achieve,boolean success,int friendly) {
        if(success==true) {
            switch (achieve) {
                case 0: friendly+=4;
                    break;
                case 1: friendly+=6;
                    break;
                case 2: friendly+=10;
                    break;
                case 3: friendly+=16;
                    break;
            }
        }else {
            switch (achieve) {
                case 0: friendly-=8;
                    break;
                case 1: friendly-=6;
                    break;
                case 2: friendly-=4;
                    break;
                case 3: friendly-=2;
                    break;
            }

        }
        if(friendly>100){
            friendly=100;
        }
        return friendly;
    }

}