package com.app.skilledlabour.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Admin.LabourDetails;
import com.app.skilledlabour.activities.Labour.JobDetails;
import com.app.skilledlabour.models.Booking;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Collections;
import java.util.List;

public class MyJobsAdapter extends RecyclerView.Adapter<MyJobsAdapter.MyBooking> {
    List<Booking> list = Collections.emptyList();
    Context mContext;

    public MyJobsAdapter(List<Booking> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyBooking onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new MyBooking(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBooking holder, int position) {
        Booking booking = list.get(position);
        holder.item_booking_date.setText(booking.getDatetime());
        holder.view_labout_name.setVisibility(View.GONE);
        holder.view_customer_name.setText(booking.getCust_name());
        holder.labourItemCard.setOnClickListener(view->{
            Intent intent = new Intent(view.getContext(), JobDetails.class);
            Bundle extras = new Bundle();
            extras.putInt("job_id",booking.getId());
            intent.putExtras(extras);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyBooking extends RecyclerView.ViewHolder {
        // Here we hold the MyDoctorItems
        TextView item_booking_date, view_customer_name, view_labout_name, item_service_type;
        ImageView cust_item_image;
        CardView labourItemCard;

        public MyBooking(@NonNull View itemView) {
            super(itemView);
            cust_item_image = itemView.findViewById(R.id.cust_item_image);
            item_booking_date = itemView.findViewById(R.id.item_booking_date);
            view_customer_name = itemView.findViewById(R.id.view_customer_name);
            view_labout_name = itemView.findViewById(R.id.view_labout_name);
            item_service_type = itemView.findViewById(R.id.item_service_type);
            labourItemCard = itemView.findViewById(R.id.labourItemCard);
        }
    }
}
