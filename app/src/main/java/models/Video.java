package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Video {
    private String id;
    private String descricao;
    private String link;

    public Video(String id, String descricao, String link) {
        this.id = id;
        this.descricao = descricao;
        this.link = link;
    }

    public Video(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static String parseJson(Video video){
        JSONObject obj = new JSONObject();
        try{
            obj.put("description",video.getDescricao());
            obj.put("link",video.getLink());

            return obj.toString();
        }catch(Exception e){
            return "";
        }
    }

    public static ArrayList<Video> parseObject(String json){
        ArrayList<models.Video> videos = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);

            for (int i = 0; i < array.length(); i++) {
                Video video = new Video();

                JSONObject obj = array.getJSONObject(i);

                video.setDescricao(obj.getString("description"));
                video.setLink(obj.getString("link"));
                video.setId(obj.getString("_id"));

                videos.add(video);
            }

            return videos;
        }catch(Exception e){
            return videos;
        }
    }
}
