package com.insiro.lifepet.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.insiro.lifepet.R;


public class Popup extends Activity {

    EditText petnameText;
    EditText petcategoryText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        petnameText=findViewById(R.id.popup_name_insert);
        petcategoryText=findViewById(R.id.popup_category_insert);
        button=findViewById(R.id.popup_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                if(petnameText.getText()==null){
                    Toast.makeText(getApplicationContext(),"이름을 입력하십시오",Toast.LENGTH_SHORT).show();
                }else {
                intent.putExtra("name",petnameText.getText());
                intent.putExtra("category",petcategoryText.getText());
                setResult(RESULT_OK,intent);
                finish();
                }
            }
        });
    }
}