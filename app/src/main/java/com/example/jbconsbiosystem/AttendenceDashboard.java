package com.example.jbconsbiosystem;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


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


        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_total_users);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);



        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.searchclass);

        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adaptor.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adaptor.getFilter().filter(query);
                Log.e("yunnjy", "onQueryTextChange: "+query );
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
                        String sname=data.getString("prenom_personnel");
                        String n=name+" " +sname;

                        modelClassList.add(new EmployeeModel(n," ","Employee",id));


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


    public void backPage(View view) {
        finish();
    }
}
