package com.app.skilledlabour.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Customer.LabourDetails;
import com.app.skilledlabour.models.Labour;

import java.util.Collections;
import java.util.List;

public class LaboursCustomerAdapter extends RecyclerView.Adapter<LaboursCustomerAdapter.MyAuthor> {
    List<Labour> list = Collections.emptyList();
    Context mContext;
    public LaboursCustomerAdapter(List<Labour> list, Context context){
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyAuthor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.labour_item, parent, false);
        return new MyAuthor(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAuthor holder, int position) {
        Labour labour = list.get(position);
        holder.view_labour_name.setText(labour.getName());
        holder.view_labour_skills.setVisibility(View.VISIBLE);
        holder.view_labour_skills.setText(labour.getSkill_set());
        holder.item_labour_age.setText("Years : " + labour.getAge() + " old");
        holder.view_labour_skills.setMovementMethod(new ScrollingMovementMethod());

        holder.labourItemCard.setOnClickListener(view->{
            Intent intent = new Intent(view.getContext(), LabourDetails.class);
            Bundle extras = new Bundle();
            extras.putString("labour_id",labour.getId());
            intent.putExtras(extras);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyAuthor extends RecyclerView.ViewHolder{
        // Here we hold the MyDoctorItems
        TextView view_labour_name, item_labour_age,view_labour_skills;
        ImageView labour_item_image;
        CardView labourItemCard;
        RatingBar labour_view_rating;
        public MyAuthor(@NonNull View itemView) {
            super(itemView);
            view_labour_name = itemView.findViewById(R.id.view_labour_name);
            item_labour_age = itemView.findViewById(R.id.item_labour_age);
            labour_view_rating = itemView.findViewById(R.id.labour_view_rating);
            view_labour_skills = itemView.findViewById(R.id.view_labour_skills);
            labour_item_image = itemView.findViewById(R.id.labour_item_image);
            labourItemCard = itemView.findViewById(R.id.labourItemCard);
        }
    }
}
