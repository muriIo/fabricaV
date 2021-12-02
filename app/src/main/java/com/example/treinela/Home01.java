package com.example.treinela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import lista_videos.ListaVideos;

public class Home01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home01);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().hide();
    }

    public void btnAulaClick(View view){
        Intent intent = new Intent(this, ListaVideos.class);
        startActivity(intent);
    }
}