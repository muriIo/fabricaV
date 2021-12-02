package com.example.treinela;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import org.json.JSONObject;

import java.util.Date;
import java.util.Map;

import services.ServiceApi;
import services.*;

public class Login02 extends AppCompatActivity {

    TextView txtEmail;
    TextView txtSenha;
    ProgressDialog dialog;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login02);
        getSupportActionBar().hide();

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtSenha = (TextView) findViewById(R.id.txtSenha);

    }

    public void btnLogar(View view){

        JSONObject json = new JSONObject();
        try {
            json.put("email", txtEmail.getText().toString());
            json.put("password", txtSenha.getText().toString());
            new LoginAPI("POST",Login02.this).execute("auth/login", json.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  class LoginAPI extends AsyncTask<String, String, String> {
        private String metodo;

        private Context context;

        public LoginAPI (String metodo, Context context){
            this.metodo = metodo;
            this.context=context;
        }

        @Override protected void onPreExecute(){
            super.onPreExecute();
            dialog = ProgressDialog.show(Login02.this, "Aguarde","Por favor aguarde...");
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = ServiceApi.getService(strings[0], metodo, strings[1],"");
            return data;
        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);

            if(s!="Erro" && s!="401"){
                try {
                    JSONObject jwtJson = new JSONObject(s);
                    JWT jwt = new JWT(jwtJson.getString("token"));
                    Date expiresAt = jwt.getExpiresAt();
                    String token = jwtJson.getString("token");
                    Map<String, Claim> allClaims = jwt.getClaims();


                    session = new Session(Login02.this);

                    session.setExpiresAt(expiresAt);
                    session.setToken(token);
                    session.setId(allClaims.get("sub").asString());
                    session.setUsername(allClaims.get("email").asString());
                    session.setType(allClaims.get("type").asString());


                    //Intent intent = new Intent(this, Home01.class);
                    //startActivity(intent);

                    Intent intent = new Intent(context, Home01.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                if(s=="401"){
                    Toast.makeText(Login02.this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login02.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            }

            dialog.dismiss();
        }
    }


}