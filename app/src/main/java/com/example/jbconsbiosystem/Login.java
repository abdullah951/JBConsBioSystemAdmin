package com.example.jbconsbiosystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jbconsbiosystem.Shared.SharedPreferenceEdit;
import com.example.jbconsbiosystem.Volley.Urls;
import com.example.jbconsbiosystem.Volley.VolleyPostCallBack;
import com.example.jbconsbiosystem.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private SharedPreferenceEdit sharedPreferenceEdit;
    private EditText user,pin;
    // private CheckBox type;

    private String type = "SuperAdmin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferenceEdit=new SharedPreferenceEdit(Login.this);
        user=findViewById(R.id.enter_id);
        pin=findViewById(R.id.pin);
        // type=findViewById(R.id.type);
    }

    public void Submit(View view)  {

        String name=user.getText().toString();
        String pass=pin.getText().toString();
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("screen","GetLogin");
            jsonObject.put("name",name);
            jsonObject.put("pass",pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        VolleyRequest.PostRequest(Login.this, Urls.login, jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                JSONArray jsonArray=new JSONArray();
                try {
                    jsonArray= jsonObject.getJSONArray("result");
                    String status=jsonArray.getJSONObject(0).getString("status");
                    if(status.equals("yes")){
                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        String id=jsonArray.getJSONObject(0).getString("iduser");
                        String name=jsonArray.getJSONObject(0).getString("name");
                        sharedPreferenceEdit.AddUserId(id,"onSite",name);
                        Intent i = new Intent(Login.this, Dashboard.class);
                        startActivity(i);

                    }else{
                        Toast.makeText(Login.this, "User name or Password Incorrect", Toast.LENGTH_SHORT).show();
                        user.setText("");
                        pin.setText("");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Tag", "OnSuccess: "+jsonObject );
            }

            @Override
            public void OnFailure(String err) {

            }
        });






    }
}
