package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    private int Id;
    private String Name;
    private String Cel;
    private String Password;
    private String Cpf;
    private String Email;
    private String Type;

    public User(int id, String name, String cel, String password, String cpf, String email , String type) {
        Id = id;
        Name = name;
        Cel = cel;
        Password = password;
        Cpf = cpf;
        Email = email;
        Type = type;
    }

    public User(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCel() {
        return Cel;
    }

    public void setCel(String cel) {
        Cel = cel;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getType() { return Type; }

    public void setType(String type) { Type = type; }

    public static String parseJson(User user){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", user.getName());
            jsonObject.put("cel", user.getCel());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("cpf", user.getCpf());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("type", "NORMAL");
            return jsonObject.toString();
        }
        catch (Exception ex){
            return "";
        }
    }

    public static User parseOneObject(String json){
        try {
            User user = new User();
            JSONObject obj = new JSONObject(json);
            user.setName(obj.getString("name"));
            user.setCel(obj.getString("cel"));
            user.setPassword(obj.getString("password"));
            user.setCpf(obj.getString("cpf"));
            user.setEmail(obj.getString("email"));
            user.setId(obj.getInt("id"));
            user.setType(obj.getString("type"));

            return user;
        }
        catch (Exception ex){
            return null;
        }
    }

    public static ArrayList<User> parseObject (String json){
        ArrayList<User> users = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                User user = new User();
                JSONObject obj = array.getJSONObject(i);
                user.setName(obj.getString("name"));
                user.setCel(obj.getString("cel"));
                user.setPassword(obj.getString("password"));
                user.setCpf(obj.getString("cpf"));
                user.setEmail(obj.getString("email"));
                user.setId(obj.getInt("id"));
                user.setType(obj.getString("type"));
                users.add(user);
            }
            return users;
        }
        catch (Exception ex){
            return users;
        }

    }

}
