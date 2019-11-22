package com.example.jbconsbiosystem;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jbconsbiosystem.RecyclerClasses.EmployeeModel;
import com.example.jbconsbiosystem.RecyclerClasses.EmployeeRecycler;
import com.example.jbconsbiosystem.Volley.Urls;
import com.example.jbconsbiosystem.Volley.VolleyPostCallBack;
import com.example.jbconsbiosystem.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AttendenceDashboard extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private EmployeeRecycler adaptor;
    private List<EmployeeModel> modelClassList;
    private SearchView searchView;
    // saerch pe click nai horha keyboard open ho raha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_dashboard);


         searchView=findViewById(R.id.searchclass);
        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_total_users);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.showSoftInput(view, 0);
                    }
                }
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.e("onQueryTextChange", "called");
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                AddDataToRecyclerView(query);

                // Do your task here

                return false;
            }

        });

         AddDataToRecyclerView("");
    }

    private void AddDataToRecyclerView( String name) {
        String url= Urls.attendance+"?screen=GetUsersSearch&name="+name;
        VolleyRequest.GetRequest(AttendenceDashboard.this, url, new VolleyPostCallBack() {
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

                        modelClassList.add(new EmployeeModel(name," ","Employee",id));


                    }
                    adaptor = new EmployeeRecycler(AttendenceDashboard.this, modelClassList);


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
