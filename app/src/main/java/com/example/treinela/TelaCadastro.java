package com.example.treinela;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void btnFinalizarCadastro(View view){
        Intent intent = new Intent(this, Login02.class);
        startActivity(intent);
    }
}