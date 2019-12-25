package com.example.sliitlawresearchproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sliitlawresearchproject.R;
import com.example.sliitlawresearchproject.SummerizeActivity;
import com.example.sliitlawresearchproject.models.ModelData;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.MyHolder> {

    Context context;
    List<ModelData> dataList;

    //constructors
    public AdapterData(Context context, List<ModelData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate layout(row_user.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.row_data, viewGroup, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        //get data
        final String data_item_UID = dataList.get(i).getUid();
        String dataTitle = dataList.get(i).getTitle();

        //set data
        myHolder.prob_title.setText(dataTitle);

        //handle item click
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SummerizeActivity.class);
                intent.putExtra("data_item_Uid", data_item_UID);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView prob_title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            prob_title = itemView.findViewById(R.id.prob_title);

        }
    }
}
