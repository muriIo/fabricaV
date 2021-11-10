package com.example.treinela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login02);
        getSupportActionBar().hide();
    }

    public void btnLogar(View view){
        Intent intent = new Intent(this, Home01.class);
        startActivity(intent);
    }


}