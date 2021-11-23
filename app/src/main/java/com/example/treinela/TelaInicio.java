package com.example.treinela;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TelaInicio extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicio);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    public void btnCadastrarClick(View view){
        Intent intent = new Intent(TelaInicio.this, TelaCadastro.class);
        startActivity(intent);
    }

    public void btnFazerLoginHomeClick(View view){
        Intent intent = new Intent(this, Login02.class);
        startActivity(intent);
    }



}