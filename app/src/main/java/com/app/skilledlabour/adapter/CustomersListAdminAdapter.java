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
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;

import java.util.Collections;
import java.util.List;

public class CustomersListAdminAdapter extends RecyclerView.Adapter<CustomersListAdminAdapter.MyCust> {
    List<Customer> list = Collections.emptyList();
    Context mContext;
    public CustomersListAdminAdapter(List<Customer> list, Context context){
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyCust onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.labour_item, parent, false);
        return new MyCust(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCust holder, int position) {
        Customer author = list.get(position);
        holder.view_labour_name.setText(author.getName());
        //   holder.tvRating.setRating(Float.parseFloat(author.getRating()));
        holder.labourItemCard.setOnClickListener(view->{
        //   view.getContext().startActivity(new Intent(view.getContext(), AuthorDetailsActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyCust extends RecyclerView.ViewHolder{
        // Here we hold the MyDoctorItems
        TextView view_labour_name, item_labour_age;
        ImageView labour_item_image;
        CardView labourItemCard;
        RatingBar labour_view_rating;
        public MyCust(@NonNull View itemView) {
            super(itemView);
            view_labour_name = itemView.findViewById(R.id.view_labour_name);
            item_labour_age = itemView.findViewById(R.id.item_labour_age);
            labour_view_rating = itemView.findViewById(R.id.labour_view_rating);
            labour_item_image = itemView.findViewById(R.id.labour_item_image);
            labourItemCard = itemView.findViewById(R.id.labourItemCard);
        }
    }
}
