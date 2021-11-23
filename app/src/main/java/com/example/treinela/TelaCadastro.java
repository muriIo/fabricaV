package com.example.treinela;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import models.User;

import android.widget.Toast;

import java.util.ArrayList;

import services.ServiceApi;

public class TelaCadastro extends AppCompatActivity {

    int id = 0;
    User user;
    TextView textCel, textCPF,textSenha, textConfSenha, textEmail, textNome; //,textView3;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        getSupportActionBar().setHomeButtonEnabled(true);

        textCel = findViewById(R.id.textCel);
        textCPF = findViewById(R.id.textCPF);
        textSenha = findViewById(R.id.textSenha);
        textConfSenha = findViewById(R.id.textConfSenha);
        textNome = findViewById(R.id.textNome);
        textEmail = findViewById(R.id.textEmail);
        //textView3 = findViewById(R.id.textView3);

        if(getIntent().hasExtra("id")){
            //Editando um usuario
            id = getIntent().getIntExtra("id", 0);
            new UsuarioAPI("GET").execute("Curso/" + id, "");
        }

       // if(getIntent().hasExtra("id")){
       //     //Editando um usuario
       //     id = getIntent().getIntExtra("id", 0);
        //    new TelaCadastro().TreinellaAPI("GET").execute("user/" + id, "");
       // }
    }

    public void carregarCampos(){
        textCel.setText(user.getCel());
        textCPF.setText(user.getCpf());
        textSenha.setText(user.getPassword());
        textNome.setText(user.getName());
        textEmail.setText(user.getEmail());
      //  textView3.setText(user.getDuracao());
    }

    public void btnFinalizarCadastro(View v){
        if(id >0){ //editar
            user.setName(textNome.getText().toString());
            user.setCel(textNome.getText().toString());
            user.setPassword(textNome.getText().toString());
            user.setEmail(textNome.getText().toString());
            user.setCpf(textNome.getText().toString());


            new UsuarioAPI("PUT").execute("user/" + id, User.parseJson(user));
        }
        else{
            //inserir

            user = new User();
            user.setName(textNome.getText().toString());
            user.setCel(textCel.getText().toString());
            user.setPassword(textSenha.getText().toString());
            user.setEmail(textEmail.getText().toString());
            user.setCpf(textCPF.getText().toString());
            //user.setCourses(textView3.getText().toString());
            //user.setId(0);
            new UsuarioAPI("POST").execute("user", User.parseJson(user));

        }
        dialog.dismiss();
        Intent intent = new Intent(this, Login02.class);
        startActivity(intent);
    }

    public  class UsuarioAPI extends AsyncTask<String, String, String> {
        private String metodo;
        public UsuarioAPI (String metodo){
            this.metodo = metodo;
        }

        @Override protected void onPreExecute(){
            super.onPreExecute();
            dialog = ProgressDialog.show(TelaCadastro.this, "Aguarde","Por favor aguarde...");
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = ServiceApi.getService(strings[0], metodo, strings[1], "");
            return data;
        }

        //@Override
        //protected void onPostExecute (String s){
         //   super.onPostExecute(s);
          //  if(metodo == "GET"){
          //      curso = Curso.parseOneObject(s);
                //carregarCampos();
           //     dialog.dismiss();
          //  }
          //  else if (s == "OK"){
          //      dialog.dismiss();
          //      Toast.makeText(TelaCadastro.this, "Executado com sucesso!", Toast.LENGTH_SHORT).show();
           //     finish();
           // }

       // }
    }


}