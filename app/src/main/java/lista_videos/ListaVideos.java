package lista_videos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.treinela.Login02;
import com.example.treinela.R;
import models.*;
import adapters.*;
import services.*;

import org.json.JSONObject;
import java.util.ArrayList;


public class ListaVideos extends AppCompatActivity {

    ProgressDialog dialog;
    ArrayList<models.Video> listaVideos;
    RecyclerView recyclerVideos;
    VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_videos);
        getSupportActionBar().hide();
        recyclerVideos = (RecyclerView) findViewById(R.id.recyclerVideos);
        listaVideos = new ArrayList<>();

    }

    @Override
    protected void onResume(){
        super.onResume();
        buscarVideos();
    }

    private void buscarVideos(){
        JSONObject json = new JSONObject();
        new VideoApi("GET").execute("video",json.toString());
    }

    public void setupRecyclerVideo(){
        //Configurando o layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerVideos.setLayoutManager(layoutManager);

        // add adapter
        videoAdapter = new VideoAdapter(listaVideos);
        recyclerVideos.setAdapter(videoAdapter);

        //divisor entre linhas
        recyclerVideos.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    public class VideoApi extends AsyncTask<String,String,String>{
        private String metodo;
        private Session session;

        public VideoApi(String metodo){
            this.metodo = metodo;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ListaVideos.this,"Aguarde...", "Por favor, aguarde a busca de vídeos...");
        }

        @Override
        protected String doInBackground(String... strings) {
            session = new Session(ListaVideos.this);
            String token = session.getToken();
            String data = ServiceApi.getService(strings[0], metodo, strings[1],token);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s!="Erro" && s!="401"){
                listaVideos = Video.parseObject(s);

                setupRecyclerVideo();
            }else{
                if(s=="401"){
                    Toast.makeText(ListaVideos.this, "Não autorizado, logue novamente.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ListaVideos.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            }

            dialog.dismiss();
        }
    }
}