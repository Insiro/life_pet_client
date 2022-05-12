package com.insiro.lifepet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class Achievement extends AppCompatActivity {
    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Button plus;
    String Id;//유저 아이디

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);

        //Activity간의 ID 교환으로 인해 bundle에서 가져오기
        Intent intent= getIntent();
        Bundle bundle=intent.getExtras();
        Id = bundle.getString("ID");

        arrayList = new ArrayList<>();
        arrayList.add("달성 업적");
        arrayList.add("미달성 업적");
        arrayList.add("시작 가능한 업적");
        arrayList.add("진행중인 업적");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner = (Spinner)findViewById(R.id.achieve_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"이 선택되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        plus = findViewById(R.id.achieve_detail);
        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Additional_Achievement.class);
                Bundle bundle = new Bundle();
                bundle.putInt("option", spinner.getSelectedItemPosition());
                bundle.putString("id", Id);//Activity간의 ID 교환
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

}
