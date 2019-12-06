package com.example.jbconsbiosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jbconsbiosystem.RecyclerClasses.AttendanceDetailRecycler;
import com.example.jbconsbiosystem.RecyclerClasses.AttendenceDetail1Recyler;
import com.example.jbconsbiosystem.RecyclerClasses.EmployeeModel;
import com.example.jbconsbiosystem.Volley.Urls;
import com.example.jbconsbiosystem.Volley.VolleyPostCallBack;
import com.example.jbconsbiosystem.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AttendenceDetail1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttendenceDetail1Recyler adaptor;
    private List<EmployeeModel> modelClassList;
    private String TAG = "AttendenceDetail1";

    private String id, date,names,hrs;
    private TextView name, totalhours,dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_detail1);
        Intent i=getIntent();

        id=i.getStringExtra("id");
        date=i.getStringExtra("date");
        names=i.getStringExtra("name");
        hrs=i.getStringExtra("time");
        Log.e(TAG, "name: "+ names);
        name=findViewById(R.id.name_title2);
        dates=findViewById(R.id.date);
        dates.setText(date);
        name.setText(names);
        totalhours=findViewById(R.id.totalhours);
        totalhours.setText(hrs);
        modelClassList=new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_user_attendance);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        AddDataToRecyclerView(date);


    }
    private void AddDataToRecyclerView(String from) {
        String url= Urls.attendanceDetail;
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","GetAttendanceSheetByDate");
            jsonObject.put("id",id);

            jsonObject.put("from",from);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyRequest.PostRequest(AttendenceDetail1.this, url,jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                JSONArray jsonArray;
                try {
                    jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);





                        String checkin=data.getString("session_in");
                        String checkout=data.getString("session_out");
                        String hrs=data.getString("time_hours");
                        Log.e(TAG, "OnSuccess: hours  "+hrs );



                        modelClassList.add(new EmployeeModel( checkin, checkout,hrs));


                    }
                    Log.e("TAG", modelClassList.toString());
                    adaptor=new AttendenceDetail1Recyler(AttendenceDetail1.this,modelClassList);


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
