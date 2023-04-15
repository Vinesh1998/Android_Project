package com.app.skilledlabour.activities.Customer;

import static com.app.skilledlabour.helpers.common_helper.getCustomerById;
import static com.app.skilledlabour.helpers.common_helper.getLabourById;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Labour.DashboardLabourActivity;
import com.app.skilledlabour.activities.Labour.MyJobsActivity;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;

public class CustomerProfile extends BaseActivity {
    TextView tvName,tvCustAddress,tvEmail,tvMobile;
    Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this, DashboardUserActivity.class));
        });
        tvName = findViewById(R.id.tvName);
        tvCustAddress = findViewById(R.id.tvCustAddress);
        tvEmail = findViewById(R.id.tvEmail);
        tvMobile = findViewById(R.id.tvMobile);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();
        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("cust_id");
            if(!strId.isEmpty()){
                Customer customer= getCustomerById(strId);
                if(!customer.getId().equals(""))
                    showDetails(customer);
                else{
                    Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CustomerProfile.class));
                }
            }
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), CustomerProfile.class));
            }
        }

        btnHome.setOnClickListener(view->{
            startActivity(new Intent(this, DashboardUserActivity.class));
        });
    }

    private void showDetails(Customer customer){
        tvName.setText(customer.getName());
        tvCustAddress.setText(customer.getAddress());
        tvEmail.setText(customer.getEmail());
        tvMobile.setText(customer.getContact());
    }
}
