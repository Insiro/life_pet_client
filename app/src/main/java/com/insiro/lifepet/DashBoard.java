package com.insiro.lifepet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.insiro.lifepet.achievement.Achievement;
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
import com.insiro.lifepet.pet.pet_info;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    ArrayList<Habit> habits;
    ImageView bubble;
    ImageView catImage;
    TextView bubbleText;
    ProgressBar dailyprog;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        dailyprog=findViewById(R.id.progress);
        bubbleText=findViewById(R.id.dashboard_bubbleText);
        bottomNavigationView=findViewById(R.id.dashboard_bottomNavigation);
        if(habits!=null) {
            int total=habits.size();
            int achieve=0;
            for (Habit habit : habits) {
                if (habit.getTarget() == habit.getAcheive())
                    achieve++;
            }
            dailyprog.setProgress((int) achieve / total * 100);
        }else{
            dailyprog.setProgress(0);
            bubbleText.setText("등록된 습관이 없어요!");
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu1:
                        Intent intent1 = new Intent(DashBoard.this, DashBoard.class);
                        startActivity(intent1);
                        break;
                    case R.id.bottom_menu2:
                        Intent intent2 = new Intent(DashBoard.this, ScheduleActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottom_menu3:
                        Intent intent3 = new Intent(DashBoard.this, pet_info.class);
                        startActivity(intent3);
                        break;
                    case R.id.bottom_menu4:
                        Intent intent4 = new Intent(DashBoard.this, Achievement.class);
                        startActivity(intent4);
                }
                return true;
            }
        });
    }
    public void getData() {
        QueryBundleBuilder Builder = new QueryBundleBuilder();
        Query load_request = new Query(Field.Habits, Action.Activate, 0);
        Query requestDataQuery = new Query(Field.Habits, Action.Get, -1);
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