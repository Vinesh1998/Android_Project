package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.getCustomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Customer;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CustomerDetails extends BaseActivity {
    Customer customer = new Customer();
    TextView tvCustName,tvCustAddress,tvCustEmail,tvCustMobile,tvDelete;
    RatingBar tvLabourRating;
    LinearLayout parent_layout;
    Button btnEdit;
    EditText custName, etEmail, etMobile, etAddress;
    Button btnUpdateCust;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        parent_layout = findViewById(R.id.parent_layout);
        tvCustName = findViewById(R.id.tvCustName);
        tvCustEmail = findViewById(R.id.tvCustEmail);
        tvCustAddress = findViewById(R.id.tvCustAddress);
        btnEdit = findViewById(R.id.btnEdit);
        tvDelete = findViewById(R.id.tvDelete);

        tvDelete.setOnClickListener(view -> {
            Toast.makeText(this, "Will be Delete!", Toast.LENGTH_SHORT).show();
        });

        parent_layout.setVisibility(View.GONE);
        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("cust_id");
            if(!strId.isEmpty()) {
                customer = getCustomer(strId);
                if(customer.isStatus()) showDetails(customer);
            }
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), CustomerDetails.class));
            }
        }

        btnEdit.setOnClickListener(v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.activity_edit_cust);
            bottomSheetDialog.show();

            custName= bottomSheetDialog.findViewById(R.id.custName);
            etEmail= bottomSheetDialog.findViewById(R.id.etEmail);
            etMobile= bottomSheetDialog.findViewById(R.id.etMobile);
            etAddress= bottomSheetDialog.findViewById(R.id.etAge);
            btnUpdateCust= bottomSheetDialog.findViewById(R.id.btnUpdateCust);

            custName.setText(customer.getName());
            etEmail.setText(customer.getEmail());
            etMobile.setText(customer.getContact());
//            etAddress.setText(customer.getAddress());

            btnUpdateCust.setOnClickListener(view->{
//                String stLabName = custName.getText().toString();
//                String stEmail = etEmail.getText().toString();
//                String stMobile = etMobile.getText().toString();
                Toast.makeText(this, "Details will be update!", Toast.LENGTH_SHORT).show();
                hideProgressDialog();
                bottomSheetDialog.dismiss();
            });
        });
    }

    private void showDetails(Customer customer){
        parent_layout.setVisibility(View.VISIBLE);
        tvCustEmail.setText(customer.getEmail());
//        tvCustMobile.setText(customer.getContact());
        tvCustName.setText(customer.getName());
        tvCustAddress.setText(customer.getAddress());
    }
}
