package com.insiro.lifepet.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

public class pet_info extends AppCompatActivity {

    ArrayList<pet_data> petInfoList;
    ArrayList<Pet> pet;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        //this.getQueryData();
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
        petList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            }
        });
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.pet_info_bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu1:
                        Intent intent1 = new Intent(pet_info.this, Achievement.class);
                        startActivity(intent1);
                        break;
                    case R.id.bottom_menu2:
                        Intent intent2 = new Intent(pet_info.this, ScheduleActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.bottom_menu3:
                        Intent intent3 = new Intent(pet_info.this, ScheduleActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.bottom_menu4:
                        Intent intent4 = new Intent(pet_info.this, pet_info.class);
                        startActivity(intent4);
                }
                return true;
            }
        });
    }
    public void getQueryData(){
        petInfoList=new ArrayList();
        QueryBundleBuilder Builder= new QueryBundleBuilder();
        Query load_request= new Query(Field.Pets, Action.Activate,0);
        Query requestDataQuery= new Query(Field.Pets, Action.Get,-1);
        Builder.addQuery(load_request,null);
        Builder.addQuery(requestDataQuery,null);
        Bundle requestBundle=new Bundle();
        requestBundle=Builder.build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtra("requestBundle",requestBundle);
        startActivityForResult(intent,1);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent=new Intent();
        Bundle bundle=intent.getExtras();
        ResponseBundleReader queryBundleReader=new ResponseBundleReader(bundle);
        QueryData resData= queryBundleReader.getData(true);
        pet = (ArrayList<Pet>) resData.getData();
    }

}