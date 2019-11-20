package com.example.jbconsbiosystem.RecyclerClasses;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.jbconsbiosystem.AttendanceDetail;
import com.example.jbconsbiosystem.R;

import java.util.List;

public class EmployeeRecycler extends RecyclerView.Adapter<EmployeeRecycler.ViewHolder> {

    private Context context;
    private List<EmployeeModel> modelClassList;


    public EmployeeRecycler(Context context, List<EmployeeModel> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_empoyee_row,null);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final EmployeeModel modelClass = modelClassList.get(i);
        viewHolder.name.setText(modelClass.getName());
        viewHolder.post.setText(modelClass.getPost());
        viewHolder.image.setImageResource(R.drawable.image);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AttendanceDetail.class);
                intent.putExtra("id", modelClass.getEmp_code());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


         TextView name,post;
         ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            post=itemView.findViewById(R.id.post);
            image=itemView.findViewById(R.id.image);

        }
    }
}
