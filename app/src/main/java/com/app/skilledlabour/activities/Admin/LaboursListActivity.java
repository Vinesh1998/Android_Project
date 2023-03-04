package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.getLaboursList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.LaboursAdminAdapter;
import com.app.skilledlabour.models.Labour;

import java.util.List;

public class LaboursListActivity extends AppCompatActivity {
    LaboursAdminAdapter adapter;
    RecyclerView recyclerView;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_labours);
        setUpRecyclerView();
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
    public void setUpRecyclerView(){
        List<Labour> list = getLaboursList();
        recyclerView = findViewById(R.id.ListLabour);
        adapter = new LaboursAdminAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
