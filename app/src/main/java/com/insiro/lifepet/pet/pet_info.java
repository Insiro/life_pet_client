package com.insiro.lifepet.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.insiro.lifepet.R;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        //this.getQueryData();
        GridView petList=findViewById(R.id.pet_info_petlist);
        pet_adapter pAdapter= new pet_adapter();
        pAdapter.addItem(new pet_data(0,"길고양이","길고양이",
                0,0,1));

        pAdapter.addItem(new pet_data(0,"길고양이","길고양이",
                0,0,1));
        Pet petinfo;
        /*for(int i=0;i<pet.size();i++){
            petinfo=pet.get(i);
            petInfoList.add(new pet_data(0,petinfo.getName(),
                    petinfo.getCategory(),petinfo.getIntimacy(),
                    petinfo.getExp(),petinfo.getLevel()));
        }*/
        petList.setAdapter(pAdapter);
        petList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pet_data pet_dat=(pet_data)pAdapter.getItem(position);
                
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