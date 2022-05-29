package com.insiro.lifepet.pet;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.insiro.lifepet.DashBoard;
import com.insiro.lifepet.NavigationBar;
import com.insiro.lifepet.R;
import com.insiro.lifepet.ScheduleActivity;
import com.insiro.lifepet.achievement.Achievement;
import com.insiro.lifepet.dataManager.Action;
import com.insiro.lifepet.dataManager.Data;
import com.insiro.lifepet.dataManager.DataManager;
import com.insiro.lifepet.dataManager.Field;
import com.insiro.lifepet.dataManager.Query;
import com.insiro.lifepet.dataManager.QueryBundleBuilder;
import com.insiro.lifepet.dataManager.QueryBundleReader;
import com.insiro.lifepet.dataManager.QueryData;
import com.insiro.lifepet.dataManager.ResponseBundleReader;
import com.insiro.lifepet.entity.Pet;
import com.insiro.lifepet.pet.pet_adapter;
import com.insiro.lifepet.pet.pet_data;
import com.insiro.lifepet.pet.pet_func;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class pet_info extends AppCompatActivity {

    ArrayList<pet_data> petInfoList;
    ArrayList<Pet> pet;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        this.addData();
        this.getQueryData();
        GridView petList=findViewById(R.id.pet_info_petlist);
        pet_adapter pAdapter= new pet_adapter();
        pAdapter.addItem(new pet_data(0,"길고양이","길고양이",
                0,0,10));
        Pet petinfo;
        if(pet!=null) {
            for (int i = 0; i < pet.size(); i++) {
                petinfo = pet.get(i);
                petInfoList.add(new pet_data(0, petinfo.getName(),
                        petinfo.getCategory(), petinfo.getIntimacy(),
                        petinfo.getExp(), petinfo.getLevel()));
            }
        }
        petList.setAdapter(pAdapter);
        petList.setOnItemClickListener((parent, view, position, id) -> {
            pet_data pet_dat=(pet_data)pAdapter.getItem(position);
            Bundle bundle=new Bundle();
            bundle.putString("name",pet_dat.getPetName());
            bundle.putString("category",pet_dat.getPetCategory());
            bundle.putInt("id",pet_dat.getId());
            bundle.putInt("level",pet_dat.getLevel());
            bundle.putInt("intimacy",pet_dat.getIntimacy());
            bundle.putDouble("exp",pet_dat.getExp());
            Intent intent= new Intent(getApplicationContext(),PetDetail.class);
            intent.putExtra("pet",bundle);
            startActivity(intent);
        });
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                it -> new NavigationBar(this).onNavigationItemSelected(it));
    }
    public void getQueryData(){
        petInfoList=new ArrayList();
        QueryBundleBuilder Builder= new QueryBundleBuilder();
        Query load_request= new Query(Field.Pets, Action.Activate,0);
        Query requestDataQuery= new Query(Field.Pets, Action.Get,-1);
        Builder.addQuery(load_request,null);
        Builder.addQuery(requestDataQuery,null);
        Bundle requestBundle=Builder.build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtras(requestBundle);
        ActivityResultLauncher<Intent> intentLauncher =  registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), it->handleResponse(it)
        );
        intentLauncher.launch(intent);
    }
    public void addData(){
        QueryBundleBuilder Builder = new QueryBundleBuilder();
        Query load_request = new Query(Field.Pets, Action.Activate, 0);
        Query requestDataQuery = new Query(Field.Pets, Action.Add, 0);
        Pet pet = new Pet("0", "나비", "코리안숏헤어", 0, 0, 1);
        QueryData newData = new QueryData(pet, Field.Pets, false);
        Builder.addQuery(load_request, null);
        Builder.addQuery(requestDataQuery, newData);
        Builder.addQuery(new Query(Field.Pets, Action.Commit,0),null);
        Bundle requestBundle = Builder.build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtra("requestBundle", requestBundle);
        startActivity(intent);
    }
    private void handleResponse(ActivityResult result){
        Intent data = result.getData();
        if(data ==null)
            return;
        Bundle bundle = result.getData().getExtras();
        ResponseBundleReader queryBundleReader=new ResponseBundleReader(bundle);
        QueryData resData= queryBundleReader.getData(true);
        pet = (ArrayList<Pet>) resData.getData();
    }
}