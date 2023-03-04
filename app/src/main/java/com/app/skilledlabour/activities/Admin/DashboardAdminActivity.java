package com.app.skilledlabour.activities.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.LoginActivity;

public class DashboardAdminActivity extends AppCompatActivity {
    Button SignOutBtn, btnLabours, btnCustomers, btnQueries, btnBookings, btnSkills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btnLabours = findViewById(R.id.btnLabours);
        btnCustomers = findViewById(R.id.btnCustomers);
        btnQueries = findViewById(R.id.btnQueries);
        btnBookings = findViewById(R.id.btnBookings);
        btnSkills = findViewById(R.id.btnSkills);
        btnSkills.setOnClickListener(view -> {
              startActivity(new Intent(this, SkillsActivity.class));
        });
        btnLabours.setOnClickListener(view -> {
             startActivity( new Intent(this, LaboursListActivity.class));
        });
        btnCustomers.setOnClickListener(view -> {
             startActivity(new Intent(this, CustomersListActivity.class));
        });
//        btnRequests.setOnClickListener(v -> {
//             startActivity( new Intent(this, RequestedBooksActivity.class));
//        });
//        btnDues.setOnClickListener(v -> {
//              startActivity( new Intent(this, OverDueBooksActivity.class));
//        });
        SignOutBtn=findViewById(R.id.signOutBtn);
        SignOutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
