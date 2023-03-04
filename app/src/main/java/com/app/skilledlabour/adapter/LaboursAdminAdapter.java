package com.app.skilledlabour.adapter;

import android.content.Context;
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
import com.app.skilledlabour.models.Labour;

import java.util.Collections;
import java.util.List;

public class LaboursAdminAdapter extends RecyclerView.Adapter<LaboursAdminAdapter.MyAuthor> {
    List<Labour> list = Collections.emptyList();
    Context mContext;
    public LaboursAdminAdapter(List<Labour> list, Context context){
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
        Labour author = list.get(position);
        holder.view_labour_name.setText(author.getName());
        holder.item_labour_age.setText("Years : " + author.getAge() + " old");
        //   holder.tvRating.setRating(Float.parseFloat(author.getRating()));
        holder.labourItemCard.setOnClickListener(view->{
        //   view.getContext().startActivity(new Intent(view.getContext(), AuthorDetailsActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyAuthor extends RecyclerView.ViewHolder{
        // Here we hold the MyDoctorItems
        TextView view_labour_name, item_labour_age;
        ImageView labour_item_image;
        CardView labourItemCard;
        RatingBar labour_view_rating;
        public MyAuthor(@NonNull View itemView) {
            super(itemView);
            view_labour_name = itemView.findViewById(R.id.view_labour_name);
            item_labour_age = itemView.findViewById(R.id.item_labour_age);
            labour_view_rating = itemView.findViewById(R.id.labour_view_rating);
            labour_item_image = itemView.findViewById(R.id.labour_item_image);
            labourItemCard = itemView.findViewById(R.id.labourItemCard);
        }
    }
}
