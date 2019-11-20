package com.example.jbconsbiosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.jbconsbiosystem.RecyclerClasses.classRecycler;
import com.example.jbconsbiosystem.RecyclerClasses.model;
import com.example.jbconsbiosystem.Volley.Urls;
import com.example.jbconsbiosystem.Volley.VolleyPostCallBack;
import com.example.jbconsbiosystem.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "TimeSheet";

    private RecyclerView recyclerView;
    private classRecycler adaptor;
    private List<model> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_time_sheet);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);


        AddDataToRecyclerView();
    }

    private void AddDataToRecyclerView() {
        VolleyRequest.GetRequest(MainActivity.this, Urls.attendance, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                Log.e("yes", "OnSuccess: " );
                JSONArray jsonArray=new JSONArray();
                try {
                    jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);
                        String id=data.getString("matricule_personnel");
                        String name=data.getString("nom_personnel");
                        String parent=data.getString("prenom_personnel");
                        String date=data.getString("date");
                        String hrs=data.getString("time_hours");
                        modelClassList.add(new model(name,date,
                                hrs));


                    }
                    adaptor = new classRecycler(MainActivity.this, modelClassList);


                    recyclerView.setAdapter(adaptor);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnFailure(String err) {

            }
        });


    }


}
