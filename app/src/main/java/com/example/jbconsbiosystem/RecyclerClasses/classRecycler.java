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

public class classRecycler extends RecyclerView.Adapter<classRecycler.ViewHolder> {

    private Context context;
    private List<model> modelClassList;


    public classRecycler(Context context, List<model> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_time_sheet,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        model modelClass = modelClassList.get(i);
        viewHolder.name.setText(modelClass.getName());
        viewHolder.date.setText(modelClass.getDate());
        viewHolder.total_hours.setText(modelClass.getTotal_hours());




    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


         TextView name,date,total_hours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
            total_hours=itemView.findViewById(R.id.total_hours);


        }
    }
}
