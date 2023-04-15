package com.app.skilledlabour.activities.Customer;

import static com.app.skilledlabour.helpers.common_helper.getQueryDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Query;

public class QueryDetails extends BaseActivity {
    TextView tvlabourName,tvQuery,tvBack,tvQueryTime,tvReplyMsg;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_cust_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this, QueriesActivity.class));
        });
        tvlabourName = findViewById(R.id.tvlabourName);
        tvQueryTime = findViewById(R.id.tvSlotTime);
        tvQuery = findViewById(R.id.tvQuery);
        btnClose = findViewById(R.id.btnClose);
        tvReplyMsg = findViewById(R.id.tvReplyMsg);
        tvBack = findViewById(R.id.tvBack);

        tvBack.setOnClickListener(view -> {
          startActivity(new Intent(this, QueriesActivity.class));
        });

        btnClose.setOnClickListener(view -> {
            Toast.makeText(this, "Closed!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, QueriesActivity.class));
        });

        Query query = getQueryDetails(1);
        tvQuery.setText("Query: "+query.getMsg());
        tvReplyMsg.setText("Reply: "+query.getReplyMsg());
        tvlabourName.setText(query.getEmp_name());
        tvQueryTime.setText(query.getDatetime());
    }
}
