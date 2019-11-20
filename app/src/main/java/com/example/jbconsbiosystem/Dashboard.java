package com.example.jbconsbiosystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jbconsbiosystem.Shared.SharedPreferenceEdit;

import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity {

    private SharedPreferenceEdit sharedPreferenceEdit;
    private TextView name;
    private String mng_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedPreferenceEdit=new SharedPreferenceEdit(Dashboard.this);
        name=findViewById(R.id.name);
        try {
            JSONObject names =sharedPreferenceEdit.GetUserId();
            mng_name=names.getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        name.setText(mng_name);
    }

    public void Logout(View view) {
        sharedPreferenceEdit.ClearUserId();
        Intent intent=new Intent(Dashboard.this,Login.class);
        startActivity(intent);
    }

    public void OpenTimeSheet(View view) {
        Intent i = new Intent(this, AttendenceDashboard.class);
        startActivity(i);
    }
}
