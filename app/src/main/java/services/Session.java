package services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

public class Session {

        private SharedPreferences prefs;

        public Session(Context cntx) {
            // TODO Auto-generated constructor stub
            prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        }


        public void setUsername(String username) {
            prefs.edit().putString("username", username).apply();
        }
        public String getUsername() {
            String username = prefs.getString("username","");
            return username;
        }


        public void setId(String id) {
            prefs.edit().putString("id", id).apply();
        }
        public String getId() {
            String id = prefs.getString("id","");
            return id;
        }


        public void setType(String type) {
            prefs.edit().putString("type", type).apply();
        }
        public String getType() {
            String type = prefs.getString("type","");
            return type;
        }

        public void setExpiresAt(Date expiresAt) {
            prefs.edit().putLong("expiresAt", expiresAt.getTime()).apply();
        }
        public Date getExpiresAt() {
            Date expiresAt =new Date(prefs.getLong("expiresAt",0));
            return expiresAt;
        }

        public void setToken(String token) {
            prefs.edit().putString("token", token).apply();
        }
        public String getToken() {
            String token = prefs.getString("token","");
            return token;
        }
}
