package com.insiro.lifepet;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class pet_func {

    public static float exp=0;
    public static int friendly=0;
    public static int charLv=0;
    public static float totalExp=500;
    public static float gain_exp(int achieve,float exp, float friendly) {
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
    public static void change_img(ImageButton old, ImageButton replace){
        old.setVisibility(View.INVISIBLE);
        replace.setVisibility(View.VISIBLE);
    }
    public static void react_exp(ProgressBar progressBar, TextView xp, TextView friends,
                                 TextView level, int achieve, boolean success){
        friendly=gain_friendly(achieve,success,friendly);
        if(success) {
            exp += gain_exp(achieve, exp, friendly);
            progressBar.setProgress((int) ((exp / totalExp) * 100));
            xp.setText((int) exp + "/" + (int) totalExp);
        }
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
    public static int gain_friendly(int achieve,boolean success,int friendly) {
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
        if(friendly>100){//most friendly is 100
            friendly=100;
        }
        if(friendly<-100)//less friendly is -100
            friendly=-100;
        return friendly;
    }

}
