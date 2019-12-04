package com.example.jbconsbiosystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jbconsbiosystem.RecyclerClasses.AttendanceDetailRecycler;
import com.example.jbconsbiosystem.RecyclerClasses.EmployeeModel;
import com.example.jbconsbiosystem.Volley.Urls;
import com.example.jbconsbiosystem.Volley.VolleyPostCallBack;
import com.example.jbconsbiosystem.Volley.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AttendanceDetail extends Activity {
    private RecyclerView recyclerView;
    private String TAG = "AttendanceDetail";
    private AttendanceDetailRecycler adaptor;
    private List<EmployeeModel> modelClassList;
    private String id,name;
    private TextView date_from, date_to,name_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_detail);

        date_from = findViewById(R.id.date_from);
        date_to = findViewById(R.id.date_to);
        name_title=findViewById(R.id.name_title);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        date_to.setText(formattedDate);
        date_from.setText(formattedDate);

        date_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(v);
            }
        });

        date_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(v);
            }
        });

        Intent i=getIntent();
        id=i.getStringExtra("id");
        name=i.getStringExtra("name");
        Log.e(TAG, "name: "+ name);
        name_title.setText(name);
        Toast.makeText(this, "yes loading "+id, Toast.LENGTH_SHORT).show();

        modelClassList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_user_attendance);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        AddDataToRecyclerView(date_to.getText().toString(), date_from.getText().toString());
    }
    private void AddDataToRecyclerView(String to, String from) {
        String url= Urls.attendanceDetail;
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("screen","GetAttendanceSheetById");
            jsonObject.put("id",id);
            jsonObject.put("from",from);
            jsonObject.put("to",to);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyRequest.PostRequest(AttendanceDetail.this, url,jsonObject, new VolleyPostCallBack() {
            @Override
            public void OnSuccess(JSONObject jsonObject) {
                JSONArray jsonArray;
                try {
                    jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject data=jsonArray.getJSONObject(i);

                        Log.e(TAG, "JSON object " + data.toString());

                        String date=data.getString("date");
                        String hrs=data.getString("time_hours");

                        String checkin=data.getString("session_in");
                        String checkout=data.getString("session_out");

                        Log.e("TAG", "OnSuccess: " + date);
                        Log.e("TAG", "OnSuccess: " + hrs);
                        Log.e("TAG", "OnSuccess: " + name);

                        modelClassList.add(new EmployeeModel(date,hrs,name, checkin, checkout,id));


                    }
                    Log.e("TAG", modelClassList.toString());
                    adaptor=new AttendanceDetailRecycler(AttendanceDetail.this,modelClassList);


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

    public void openDatePickerDialog(final View v) {
        // Get Current Date
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        switch (v.getId()) {
                            case R.id.date_from:
                                ((TextView) v).setText(selectedDate);
                                break;
                            case R.id.date_to:
                                ((TextView) v).setText(selectedDate);
                                break;
                        }
                        AddDataToRecyclerView(date_to.getText().toString(), date_from.getText().toString());
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    public void backPage(View view) {
        finish();
    }
}
