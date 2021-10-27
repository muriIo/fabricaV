package player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.treinela.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayerVideo extends AppCompatActivity {

    TextView textView;
    YouTubePlayerView youTubePlayerView;
    EditText inputComentario;
    Button btnEnviarComentario;

    public void PlayerVideo(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_video);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        textView = (TextView) findViewById(R.id.textView);
        getLifecycle().addObserver(youTubePlayerView);
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String link = extras.getString("link");
            String descricao = extras.getString("descricao");

            String arrayLink[] = link.split("=");
            String videoId = arrayLink[1];

            textView.setText(descricao.toString());

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        }


    }
}