package com.example.jbconsbiosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jbconsbiosystem.RecyclerClasses.AttendanceDetailRecycler;
import com.example.jbconsbiosystem.RecyclerClasses.EmployeeModel;

import java.util.List;

public class AttendenceDetail1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttendanceDetailRecycler adaptor;
    private List<EmployeeModel> modelClassList;
    private String TAG = "AttendenceDetail1";

    private String id, date,names;
    private TextView name, totalhours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_detail1);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        date=i.getStringExtra("date");
        names=i.getStringExtra("name");
        Log.e(TAG, "name: "+ names);
        name=findViewById(R.id.name_title2);

    }

    public void backPage(View view) {
        finish();
    }
}
