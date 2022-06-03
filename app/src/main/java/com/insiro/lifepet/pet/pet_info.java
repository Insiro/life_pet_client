package com.insiro.lifepet.pet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.insiro.lifepet.NavigationBar;
import com.insiro.lifepet.R;
import com.insiro.lifepet.dataManager.Action;
import com.insiro.lifepet.dataManager.DataManager;
import com.insiro.lifepet.dataManager.Field;
import com.insiro.lifepet.dataManager.Query;
import com.insiro.lifepet.dataManager.QueryBundleBuilder;
import com.insiro.lifepet.dataManager.QueryData;
import com.insiro.lifepet.dataManager.ResponseBundleReader;
import com.insiro.lifepet.entity.Pet;

import java.text.MessageFormat;
import java.util.ArrayList;

public class pet_info extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    pet_adapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        this.getQueryData();
        GridView petList = findViewById(R.id.pet_info_petlist);
        floatingActionButton = findViewById(R.id.pet_info_floating_btn);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Popup.class);
            startActivityForResult(intent, 1);
            pAdapter.notifyDataSetChanged();
        });
        pAdapter = new pet_adapter();
        pAdapter.sample = new ArrayList<>();
        pAdapter.notifyDataSetChanged();

        petList.setAdapter(pAdapter);
        petList.setOnItemClickListener((parent, view, position, id) -> {
            pet_data pet_dat = (pet_data) pAdapter.getItem(position);
            Bundle bundle = new Bundle();
            bundle.putString("name", pet_dat.getPetName());
            bundle.putString("category", pet_dat.getPetCategory());
            bundle.putInt("id", pet_dat.getId());
            bundle.putInt("level", pet_dat.getLevel());
            bundle.putInt("intimacy", pet_dat.getIntimacy());
            bundle.putDouble("exp", pet_dat.getExp());
            Intent intent = new Intent(getApplicationContext(), PetDetail.class);
            intent.putExtra("pet", bundle);
            startActivity(intent);
        });
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                it -> new NavigationBar(this).onNavigationItemSelected(it));
    }

    public void getQueryData() {
        Bundle requestBundle = new QueryBundleBuilder()
                .addQuery(new Query(Field.Pets, Action.Activate, 0), null)
                .addQuery(new Query(Field.Pets, Action.Get, -1), null)
                .build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtras(requestBundle);
        ActivityResultLauncher<Intent> intentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), this::handleResponse
        );
        intentLauncher.launch(intent);
    }

    public void addData(String petName, String category) {
        Pet new_pet = new Pet(MessageFormat.format("{0}", pAdapter.sample.size()), petName, category, 0, 0, 1);
        Bundle requestBundle = new QueryBundleBuilder()
                .addQuery(new Query(Field.Pets, Action.Activate, 0), null)
                .addQuery(new Query(Field.Pets, Action.Add, 0), new QueryData(new_pet, Field.Pets, false))
                .addQuery(new Query(Field.Pets, Action.Commit, 0), null)
                .build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtras(requestBundle);
        startActivity(intent);
    }

    private void handleResponse(ActivityResult result) {
        Intent data = result.getData();
        if (data == null)
            return;
        Bundle bundle = result.getData().getExtras();
        ResponseBundleReader queryBundleReader = new ResponseBundleReader(bundle);
        QueryData resData = queryBundleReader.getData(true);
        if (resData == null) return;
        pAdapter.sample = (ArrayList<Pet>) resData.getData();
        pAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String petName = data.getStringExtra("name");
                String petCategory = data.getStringExtra("category");
                addData(petName, petCategory);
            }
        }
    }
}