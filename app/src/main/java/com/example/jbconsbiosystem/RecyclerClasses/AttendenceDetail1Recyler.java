package com.example.jbconsbiosystem.RecyclerClasses;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jbconsbiosystem.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        viewHolder.hours.setText(modelClass.getHrs());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//        Date startDate = null;
//        try {
//            startDate = simpleDateFormat.parse(modelClass.getCheckin());
//            Date endDate = simpleDateFormat.parse(modelClass.getCheckout());
//            long difference = endDate.getTime() - startDate.getTime();
//            if(difference<0)
//            {
//                Date dateMax = simpleDateFormat.parse("24:00");
//                Date dateMin = simpleDateFormat.parse("00:00");
//                difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
//            }
//            int days = (int) (difference / (1000*60*60*24));
//            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
//            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//            Log.i("log_tag","Hours: "+hours+", Mins: "+min);
//            viewHolder.hours.setText(hours+"H "+min+"M");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }





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
