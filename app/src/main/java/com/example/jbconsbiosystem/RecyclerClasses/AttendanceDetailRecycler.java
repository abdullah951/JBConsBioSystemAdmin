package com.example.jbconsbiosystem.RecyclerClasses;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jbconsbiosystem.AttendenceDetail1;
import com.example.jbconsbiosystem.R;

import java.util.List;

public class AttendanceDetailRecycler extends RecyclerView.Adapter<AttendanceDetailRecycler.ViewHolder> {

    private Context context;
    private List<EmployeeModel> modelClassList;


    public AttendanceDetailRecycler(Context context, List<EmployeeModel> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_attendance_detail,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final EmployeeModel modelClass = modelClassList.get(i);
        viewHolder.date.setText(modelClass.getDate());
        viewHolder.hrs.setText(modelClass.getHrs()+" ");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AttendenceDetail1.class);
                intent.putExtra("id", modelClass.getEmp_code());
                intent.putExtra("date", modelClass.getDate());
                intent.putExtra("name",modelClass.getName());
                intent.putExtra("time",modelClass.getHrs());

                Log.e("Recyclerview TAG", "onClick: "+modelClass.getEmp_code());
                Log.e("Recyclerview TAG", "onClick: "+modelClass.getName());
                Log.e("Recyclerview TAG", "onClick: "+modelClass.getDate());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


         TextView date,hrs;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            hrs=itemView.findViewById(R.id.total_hours);


        }
    }
}
