package com.app.skilledlabour.activities.Labour;

import static com.app.skilledlabour.helpers.common_helper.getBookingData;
import static com.app.skilledlabour.helpers.common_helper.getCustomer;
import static com.app.skilledlabour.helpers.common_helper.getQueryDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Admin.QueriesActivity;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Booking;
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Query;

public class QueryDetails extends BaseActivity {
    Booking booking = new Booking();
    TextView tvCustName,tvQuery,tvClose,tvQueryTime;
    Button btnReply;
    EditText etReplyMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this, MyJobsActivity.class));
        });
        tvCustName = findViewById(R.id.tvCustName);
        tvQueryTime = findViewById(R.id.tvSlotTime);
        tvQuery = findViewById(R.id.tvQuery);
        tvClose = findViewById(R.id.tvClose);
        etReplyMsg = findViewById(R.id.etReplyMsg);
        btnReply = findViewById(R.id.btnReply);

        tvClose.setOnClickListener(view -> {
            Toast.makeText(this, "Closed!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, QueriesActivity.class));
        });

        btnReply.setOnClickListener(view -> {
            if(!etReplyMsg.getText().toString().isEmpty()) {
                Toast.makeText(this, "Sent!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, QueriesActivity.class));
            }else{
                etReplyMsg.setError("Required!");
            }
        });

        Query query = getQueryDetails(1);
        tvQuery.setText("Query: "+query.getMsg());
        tvCustName.setText(query.getCust_name());
        tvQueryTime.setText(query.getDatetime());
    }
}
