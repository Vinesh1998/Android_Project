package com.app.skilledlabour.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Labour.QueryDetails;
import com.app.skilledlabour.models.Query;
import java.util.Collections;
import java.util.List;

public class QueryLabourAdapter extends RecyclerView.Adapter<QueryLabourAdapter.MyBook> {
    List<Query> list = Collections.emptyList();
    Context mContext;
    public QueryLabourAdapter(List<Query> list, Context context){
        this.list = list;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MyBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_item, parent, false);
        return new QueryLabourAdapter.MyBook(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyBook holder, int position) {
        Query query = list.get(position);
        holder.tvCust.setText(query.getCust_name());

        holder.tvMsg.setText(query.getMsg());
        holder.tvDatetime.setText(query.getDatetime());
        if(query.isStatus()){
            holder.tvStatus.setText("Active");
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.green));
        }else{
            holder.tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.tvStatus.setText("Closed");
        }
        holder.queryCard.setOnClickListener(view->{
            Intent intent = new Intent(view.getContext(), QueryDetails.class);
            Bundle extras = new Bundle();
            extras.putString("query_id",query.getId());
            intent.putExtras(extras);
            view.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyBook extends RecyclerView.ViewHolder{
        TextView tvMsg,tvCust,tvDatetime,tvStatus;
        CardView queryCard;
        public MyBook(@NonNull View itemView) {
            super(itemView);
            queryCard = itemView.findViewById(R.id.queryCard);
            tvMsg = itemView.findViewById(R.id.tvQuery);
            tvCust = itemView.findViewById(R.id.tvCust);
            tvDatetime = itemView.findViewById(R.id.dateTime);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
