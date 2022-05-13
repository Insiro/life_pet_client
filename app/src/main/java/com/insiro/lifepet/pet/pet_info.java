package com.insiro.lifepet.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.insiro.lifepet.R;
import com.insiro.lifepet.dataManager.Action;
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
        this.getQueryData();
        ListView petList=findViewById(R.id.pet_info_petlist);
        final pet_adapter pAdapter= new pet_adapter(this, petInfoList);

        petList.setAdapter(pAdapter);

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
        Intent intent = new Intent();
        intent.putExtra("requestBundle",requestBundle);
        startActivityForResult(intent,1);
        Pet petinfo;
        for(int i=0;i<pet.size();i++){
            petinfo=pet.get(i);
            petInfoList.add(new pet_data(R.drawable.littledeep_cat_file_style1,
                    petinfo.getCategory(),petinfo.getIntimacy(),
                    (float) petinfo.getExp(),petinfo.getLevel()));
        }

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