package com.app.skilledlabour.activities.Customer;

import static com.app.skilledlabour.helpers.common_helper.getCustomerQueries;
import static com.app.skilledlabour.helpers.common_helper.getLabourQueries;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.QueryAdapter;
import com.app.skilledlabour.adapter.QueryCustAdapter;
import com.app.skilledlabour.models.Query;

import java.util.List;

public class QueriesActivity extends AppCompatActivity {
    QueryCustAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);
        setUpRecyclerView();
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this,DashboardUserActivity.class));
        });
    }

    public void setUpRecyclerView(){
        int cust_id = 1;
        List<Query> list = getCustomerQueries(cust_id);
        recyclerView = findViewById(R.id.ListSkills);
        adapter = new QueryCustAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
