package com.insiro.lifepet.pet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.insiro.lifepet.dataManager.Action;
import com.insiro.lifepet.dataManager.DataManager;
import com.insiro.lifepet.dataManager.Field;
import com.insiro.lifepet.dataManager.Query;
import com.insiro.lifepet.dataManager.QueryBundleBuilder;
import com.insiro.lifepet.dataManager.QueryData;
import com.insiro.lifepet.dataManager.ResponseBundleReader;
import com.insiro.lifepet.databinding.ActivityPetBinding;
import com.insiro.lifepet.entity.Pet;

public class pet_act extends AppCompatActivity {
    Pet pet;
    ActivityPetBinding act_pet_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act_pet_binding = ActivityPetBinding.inflate(getLayoutInflater());
        setContentView(act_pet_binding.getRoot());
    }

    public void getData() {
        QueryBundleBuilder Builder = new QueryBundleBuilder();
        Query load_request = new Query(Field.Pets, Action.Activate, 0);
        Query requestDataQuery = new Query(Field.Pets, Action.Get, 0);
        Builder.addQuery(load_request, null);
        Builder.addQuery(requestDataQuery, null);
        Bundle requestBundle = new Bundle();
        requestBundle = Builder.build();
        Intent intent = new Intent(this, DataManager.class);
        intent.putExtra("requestBundle", requestBundle);
        startActivityForResult(intent, 1);

    }

    public void update() {
        QueryBundleBuilder Builder = new QueryBundleBuilder();
        Query load_request = new Query(Field.Pets, Action.Activate, 0);
        Query requestDataQuery = new Query(Field.Pets, Action.Update, -1);
        Pet pet = new Pet("0", pet_func.name, pet_func.category, pet_func.friendly, pet_func.exp, pet_func.charLv);
        QueryData newData = new QueryData(pet, Field.Pets, false);
        Builder.addQuery(load_request, null);
        Builder.addQuery(requestDataQuery, newData);
        Bundle requestBundle = Builder.build();
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
        pet = (Pet) resData.getData();
        pet_func.setCharLv(pet.getLevel());
        pet_func.setExp(pet.getExp());
        pet_func.setTotalExp(pet.getLevel());
        pet_func.setFriendly(pet.getIntimacy());
        pet_func.setCategory(pet.getCategory());
        pet_func.setName(pet.getName());
    }
}