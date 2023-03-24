package com.app.skilledlabour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.models.Query;
import com.app.skilledlabour.models.Skill;

import java.util.Collections;
import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.MyBook> {
    List<Query> list = Collections.emptyList();
    Context mContext;
    public QueryAdapter(List<Query> list, Context context){
        this.list = list;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MyBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_item, parent, false);
        return new QueryAdapter.MyBook(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyBook holder, int position) {
        Query Query = list.get(position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyBook extends RecyclerView.ViewHolder{
        // Here we hold the MyDoctorItems
        TextView skillTitle;
        ImageView skillItemImg;
        CardView skillCard;
        public MyBook(@NonNull View itemView) {
            super(itemView);
            skillCard = itemView.findViewById(R.id.skillCard);
            skillItemImg = itemView.findViewById(R.id.skillItemImg);
            skillTitle = itemView.findViewById(R.id.skillTitle);
        }
    }
}
