package com.insiro.lifepet;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.insiro.lifepet.dataManager.Action;
import com.insiro.lifepet.dataManager.DataManager;
import com.insiro.lifepet.dataManager.Field;
import com.insiro.lifepet.dataManager.Query;
import com.insiro.lifepet.dataManager.QueryBundleBuilder;
import com.insiro.lifepet.dataManager.QueryData;
import com.insiro.lifepet.dataManager.ResponseBundleReader;
import com.insiro.lifepet.databinding.ActivityDashBoardBinding;
import com.insiro.lifepet.entity.Habit;
import com.insiro.lifepet.entity.Pet;

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
        dailyprog = findViewById(R.id.progress);
        bubbleText = findViewById(R.id.dashboard_bubbleText);
        bottomNavigationView = findViewById(R.id.navigation);
        this.getData();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                it -> new NavigationBar(this).onNavigationItemSelected(it)
        );
    }

    public void getData() {
        Bundle requestBundle = new QueryBundleBuilder()
                .addQuery(new Query(Field.Habits, Action.Activate, 0), null)
                .addQuery(new Query(Field.Habits, Action.Get, -1), null)
                .build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtras(requestBundle);
        ActivityResultLauncher<Intent> intentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), this::handleResponse
        );
        intentLauncher.launch(intent);
    }

    private void handleResponse(ActivityResult result){
        Intent data = result.getData();
        if (data == null)
            return;
        Bundle bundle = result.getData().getExtras();
        ResponseBundleReader queryBundleReader = new ResponseBundleReader(bundle);
        QueryData resData = queryBundleReader.getData(true);
        if (resData == null) return;
        habits = (ArrayList<Habit>) resData.getData();

        if (habits!=null) {
            double total = habits.size();
            double achieve = 0;
            for (Habit habit : habits) {
                if (habit.getTarget() == habit.getAcheive())
                    achieve++;
                Log.d("db",total+"");
            }
            dailyprog.setProgress((int) (achieve / total* 100));
        } else {
            dailyprog.setProgress(0);
            bubbleText.setText("등록된 습관이 없어요!");
        }
    }
}