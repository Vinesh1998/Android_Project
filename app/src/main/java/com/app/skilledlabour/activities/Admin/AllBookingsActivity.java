package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.getAllBookingsData;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.AllBookingsAdapter;
import com.app.skilledlabour.models.Booking;

import java.util.List;

public class AllBookingsActivity extends AppCompatActivity {
    AllBookingsAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bookings);
        setUpRecyclerView();
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
    public void setUpRecyclerView(){
        List<Booking> list = getAllBookingsData();
        recyclerView = findViewById(R.id.ListBookings);
        adapter = new AllBookingsAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
