package com.insiro.lifepet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class pet_info extends AppCompatActivity {

    ArrayList<pet_data> petInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        this.InitializePetData();
        ListView petList=findViewById(R.id.pet_info_petlist);
        final pet_adapter pAdapter= new pet_adapter(this, petInfoList);

        petList.setAdapter(pAdapter);

    }
    public void InitializePetData(){
        petInfoList=new ArrayList<pet_data>();

        petInfoList.add(new pet_data(R.drawable.littledeep_cat_file_style2,
                "길고양이",pet_func.friendly,pet_func.exp,pet_func.charLv));
        petInfoList.add(new pet_data(R.drawable.littledeep_cat_file_style1,
                "길고양이",pet_func.friendly,pet_func.exp,pet_func.charLv));
        petInfoList.add(new pet_data(R.drawable.littledeep_cat_file_style2,
                "길고양이",pet_func.friendly,pet_func.exp,pet_func.charLv));
    }
}