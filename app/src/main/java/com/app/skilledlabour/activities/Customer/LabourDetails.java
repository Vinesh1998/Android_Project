package com.app.skilledlabour.activities.Customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.QueryAdapter;

public class LabourDetails extends AppCompatActivity {
    QueryAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }

}
