package com.example.jbconsbiosystem.Shared;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;


public class SharedPreferenceEdit {
    private Context context;



    public SharedPreferenceEdit(Context context) {
        this.context=context;

    }

   public void AddUserId(String id,String type,String name ){
       SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = prefs.edit();
       editor.putString("LOGIN_STATUS", "login");
       editor.putString("USER_ID",id);
       editor.putString("LOGIN_TYPE",type);
       editor.putString("USER_NAME",name);



       editor.apply();
    }


    public JSONObject GetUserId() throws JSONException {
        SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
        String id = prefs.getString("USER_ID", "");
        String type= prefs.getString("USER_TYPE", "");
        String name= prefs.getString("USER_NAME", "");
        JSONObject data=new JSONObject();
        data.put("name",name);
        data.put("id",id);
        data.put("type",type);

        return data;
    }
    public String GetUserLogin()  {
        SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
        String status = prefs.getString("LOGIN_STATUS", "");


        return status;
    }
    public void ClearUserId( ){
        SharedPreferences prefs = context.getSharedPreferences("LOGIN_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LOGIN_STATUS", "");
        editor.putString("USER_ID","");
        editor.putString("USER_NAME","");
        editor.putString("LOGIN_TYPE","");


        editor.apply();
    }

}
