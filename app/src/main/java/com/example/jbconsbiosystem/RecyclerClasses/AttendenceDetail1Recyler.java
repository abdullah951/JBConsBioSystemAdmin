package com.example.jbconsbiosystem.RecyclerClasses;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jbconsbiosystem.R;

import java.util.List;

public class AttendenceDetail1Recyler extends RecyclerView.Adapter<AttendenceDetail1Recyler.ViewHolder> {

    private Context context;
    private List<EmployeeModel> modelClassList;


    public AttendenceDetail1Recyler(Context context, List<EmployeeModel> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_attendence_detail1,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final EmployeeModel modelClass = modelClassList.get(i);
        viewHolder.checkin.setText(modelClass.getCheckin());
        viewHolder.checkout.setText(modelClass.getCheckout());
        viewHolder.hours.setText(modelClass.getTotalhours());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, com.luxand.facerecognition.AttendanceDetailRecycler.class);
//                intent.putExtra("id", modelClass.getEmp_code());
//                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView checkin, checkout, hours;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkin=itemView.findViewById(R.id.checkin);
            checkout = itemView.findViewById(R.id.checkout);
            hours=itemView.findViewById(R.id.total_hours);


        }
    }
}
