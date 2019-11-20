package com.example.jbconsbiosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.jbconsbiosystem.RecyclerClasses.AttendanceDetailRecycler;
import com.example.jbconsbiosystem.RecyclerClasses.EmployeeModel;

import java.util.List;

public class AttendenceDetail1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttendanceDetailRecycler adaptor;
    private List<EmployeeModel> modelClassList;

    private String id;
    private TextView name, totalhours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_detail1);
    }
}
