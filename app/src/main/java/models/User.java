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

    public User(int id, String name, String cel, String password, String cpf, String email ) {
        Id = id;
        Name = name;
        Cel = cel;
        Password = password;
        Cpf = cpf;
        Email = email;
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

    public static String parseJson(User user){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Nome", user.getName());
            jsonObject.put("Cel", user.getCel());
            jsonObject.put("Password", user.getPassword());
            jsonObject.put("CPF", user.getCpf());
            jsonObject.put("Email", user.getEmail());
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
            user.setName(obj.getString("Nome"));
            user.setCel(obj.getString("Cel"));
            user.setPassword(obj.getString("Password"));
            user.setCpf(obj.getString("CPF"));
            user.setEmail(obj.getString("Email"));
            user.setId(obj.getInt("id"));

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
                user.setName(obj.getString("Nome"));
                user.setCel(obj.getString("Cel"));
                user.setPassword(obj.getString("Password"));
                user.setCpf(obj.getString("CPF"));
                user.setEmail(obj.getString("Email"));
                user.setId(obj.getInt("id"));
                users.add(user);
            }
            return users;
        }
        catch (Exception ex){
            return users;
        }

    }

}
