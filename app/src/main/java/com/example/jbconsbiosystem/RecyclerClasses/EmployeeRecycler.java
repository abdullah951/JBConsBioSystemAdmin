package com.example.jbconsbiosystem.RecyclerClasses;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.jbconsbiosystem.AttendanceDetail;
import com.example.jbconsbiosystem.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRecycler extends RecyclerView.Adapter<EmployeeRecycler.ViewHolder> implements Filterable {

    private Context context;
    private List<EmployeeModel> modelClassList;
    private List<EmployeeModel> modelClassListFiltered;

    public EmployeeRecycler(Context context, List<EmployeeModel> modelClassList) {
        this.context = context;
        this.modelClassList = modelClassList;
        this.modelClassListFiltered = modelClassList;
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

        final EmployeeModel modelClass = modelClassListFiltered.get(i);
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
        return modelClassListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.e("vcghfthggh", "performFiltering: "+charSequence );
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    modelClassListFiltered = modelClassList;
                } else {
                    List<EmployeeModel> filteredList = new ArrayList<>();
                    for (EmployeeModel row : modelClassList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                            Log.e("yesder", "performFiltering: row is "+row.getName() );
                        }
                    }

                    modelClassListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = modelClassListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                modelClassListFiltered = (ArrayList<EmployeeModel>) filterResults.values;
                Log.e("vcghfthggh", "performFiltering: "+modelClassListFiltered.size() );
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
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
