package com.app.skilledlabour.activities.Labour;

import static com.app.skilledlabour.helpers.common_helper.getAllBookingsData;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.AllBookingsAdapter;
import com.app.skilledlabour.models.Booking;

import java.util.List;

public class MyJobsActivity extends AppCompatActivity {
    AllBookingsAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }

}
