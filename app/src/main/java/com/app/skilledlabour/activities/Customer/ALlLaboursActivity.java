package com.app.skilledlabour.activities.Customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.QueryAdapter;

public class ALlLaboursActivity extends AppCompatActivity {

    QueryAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_specialist);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });

        findViewById(R.id.labourItemCard).setOnClickListener(view->{
            startActivity(new Intent(this, LabourDetails.class));
        });
    }

}
