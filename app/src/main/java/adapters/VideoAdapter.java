package adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.treinela.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import holders.VideoHolder;
import models.Video;
import player.PlayerVideo;

public class VideoAdapter extends RecyclerView.Adapter<VideoHolder> {

    private final ArrayList<Video> videos;

    public VideoAdapter(ArrayList<Video> videos){
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_videos, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        int pos = position;

        holder.txtDescricao.setText(videos.get(position).getDescricao());
        String link = videos.get(position).getLink();

        String[] linkSeparated = link.split("=");

        String videoId=linkSeparated[1];
        String url = "https://img.youtube.com/vi/"+videoId+"/0.jpg";

        Picasso.get().load(url).into(holder.imgVideo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = videos.get(pos).getLink();
                String descricao = videos.get(pos).getDescricao();
                Intent intent;
                 intent = new Intent(v.getContext(), PlayerVideo.class);
                intent.putExtra("link",link);
                intent.putExtra("descricao", descricao);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos != null ? videos.size() : 0;
    }
}