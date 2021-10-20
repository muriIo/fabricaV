package holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.treinela.R;

public class VideoHolder extends RecyclerView.ViewHolder {
    public ImageView imgVideo;
    public TextView txtDescricao;

    public VideoHolder(View view){
        super(view);
        imgVideo = (ImageView) view.findViewById(R.id.imgVideo);
        txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
    }
}
