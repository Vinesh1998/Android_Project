package com.app.skilledlabour.activities.Labour;

import static com.app.skilledlabour.helpers.common_helper.collection_labours;
import static com.app.skilledlabour.helpers.common_helper.getBookingData;
import static com.app.skilledlabour.helpers.common_helper.getCustomer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Booking;
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobDetails extends BaseActivity {
    Booking booking = new Booking();
    TextView tvCustName,tvJob,tvCustEmail,tvCustMobile,tvReject,tvSlotTime;
    LinearLayout parent_layout;
    Button btnAccept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_job_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this, MyJobsActivity.class));
        });
        parent_layout = findViewById(R.id.parent_layout);
        tvCustName = findViewById(R.id.tvCustName);
        tvJob = findViewById(R.id.tvJob);
        tvCustEmail = findViewById(R.id.tvCustEmail);
        tvCustMobile = findViewById(R.id.tvCustMobile);
        btnAccept = findViewById(R.id.btnAccept);
        tvReject = findViewById(R.id.tvReject);
        tvSlotTime = findViewById(R.id.tvSlotTime);

        tvReject.setOnClickListener(view -> {
            Toast.makeText(this, "Rejected!", Toast.LENGTH_SHORT).show();
        });

        Intent intent = getIntent();
        if (null != intent) {
            //Null Checking
            int strId= intent.getIntExtra("job_id",0);
            if(strId != 0){
                Booking booking= getBookingData(strId);
                if(booking.getId() != 0)
                showDetails(booking);
                else{
                    Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), JobDetails.class));
                }
            }
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), JobDetails.class));
            }
        }
    }

    private void showDetails(Booking booking){
        Customer customer = getCustomer(String.valueOf(booking.getCust_id()));
        tvCustMobile.setText(customer.getContact());
        tvCustName.setText(booking.getCust_name());
        tvJob.setText(booking.getDatetime());
        tvJob.setText(booking.getDescription());
        btnAccept.setOnClickListener(view->{
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MyJobsActivity.class));
        });
        tvReject.setOnClickListener(view->{
            Toast.makeText(this, "rejected!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MyJobsActivity.class));
        });
    }
}
