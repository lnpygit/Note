package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    public Dao dao;
    Button cancel, save;
    EditText et;
    public int mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initview();

        try {
            Intent mintent = getIntent();
            String content, isimportant;
            int id;

            content = mintent.getStringExtra("content");
            isimportant = mintent.getStringExtra("isimportant");
            id = mintent.getIntExtra("id", -1);

            if (!content.equals("") || content != null){
                et.setText(content);
            }

            if (id != -1){
                mid = id;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initview(){
        initet();
        initButton();
    }

    private void initet(){
        et = findViewById(R.id.EditText_add);
    }

    private void initButton(){
        cancel = findViewById(R.id.button_cancel);
        save = findViewById(R.id.button_save);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainintent = new Intent();
                setResult(RESULT_CANCELED, mainintent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.EditText_add);
                Intent mainintent = new Intent();

                String content = editText.getText().toString();

                if(!content.equals("")){
                    mainintent.putExtra("content", content);
                    mainintent.putExtra("isimportant", "true");
                    mainintent.putExtra("id", mid);
                    setResult(RESULT_OK, mainintent);
                }
                else{
                    setResult(RESULT_CANCELED, mainintent);
                }
                finish();
            }
        });
    }

}
