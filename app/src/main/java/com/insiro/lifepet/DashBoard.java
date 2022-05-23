package com.insiro.lifepet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.insiro.lifepet.dataManager.Action;
import com.insiro.lifepet.dataManager.DataManager;
import com.insiro.lifepet.dataManager.Field;
import com.insiro.lifepet.dataManager.Query;
import com.insiro.lifepet.dataManager.QueryBundleBuilder;
import com.insiro.lifepet.dataManager.QueryData;
import com.insiro.lifepet.dataManager.ResponseBundleReader;
import com.insiro.lifepet.entity.Habit;
import com.insiro.lifepet.entity.Pet;
import com.insiro.lifepet.pet.pet_func;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    ArrayList<Habit> habits;
    ImageView bubble;
    ImageView catImage;
    TextView bubbleText;
    ProgressBar dailyprog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        int total=habits.size();
        int achieve=0;
        for(Habit habit:habits){
            if(habit.getTarget()==habit.getAcheive())
                achieve++;
        }
        dailyprog.setProgress((int)achieve/total*100);

    }
    public void getData() {
        QueryBundleBuilder Builder = new QueryBundleBuilder();
        Query load_request = new Query(Field.Habits, Action.Activate, 0);
        Query requestDataQuery = new Query(Field.Habits, Action.Get, 0);
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
        habits = (ArrayList<Habit>) resData.getData();
    }
}