package com.example.treinela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaInicio extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicio);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().hide();
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