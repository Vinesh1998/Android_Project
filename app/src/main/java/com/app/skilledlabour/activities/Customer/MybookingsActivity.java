package com.app.skilledlabour.activities.Customer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.app.skilledlabour.R;

public class MybookingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings_user);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
}
