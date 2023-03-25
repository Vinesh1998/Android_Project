package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.getCustomersList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.CustomersListAdminAdapter;
import com.app.skilledlabour.models.Customer;

import java.util.List;

public class CustomersListActivity extends AppCompatActivity {
    CustomersListAdminAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers);
        setUpRecyclerView();
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();


        });
    }
    public void setUpRecyclerView(){
        List<Customer> list = getCustomersList();
        recyclerView = findViewById(R.id.ListLabour);
        adapter = new CustomersListAdminAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
