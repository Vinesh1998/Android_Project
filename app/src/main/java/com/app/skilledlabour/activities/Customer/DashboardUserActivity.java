package com.app.skilledlabour.activities.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Admin.AllBookingsActivity;
import com.app.skilledlabour.activities.Admin.CustomersListActivity;
import com.app.skilledlabour.activities.Admin.LaboursListActivity;
import com.app.skilledlabour.activities.Admin.QueriesActivity;
import com.app.skilledlabour.activities.Admin.SkillsActivity;
import com.app.skilledlabour.activities.LoginActivity;
import com.app.skilledlabour.adapter.SkillsAdminAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardUserActivity extends AppCompatActivity {
    SkillsAdminAdapter adapter;
    Button searchBtn,myBookings,labours,btnComplaints,btnProfile,signOutBtn;
    RecyclerView recyclerView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        searchBtn = findViewById(R.id.searchBtn);
        myBookings = findViewById(R.id.myBookings);
        labours = findViewById(R.id.labours);
        btnComplaints = findViewById(R.id.btnComplaints);
        btnProfile = findViewById(R.id.btnProfile);
        signOutBtn = findViewById(R.id.signOutBtn);
        mAuth = FirebaseAuth.getInstance();
        searchBtn.setOnClickListener(view -> {
            startActivity( new Intent(this, FindSpecialistActivity.class));
        });
        myBookings.setOnClickListener(view -> {
            startActivity( new Intent(this, MybookingsActivity.class));
        });
        labours.setOnClickListener(view -> {
            startActivity(new Intent(this, ALlLaboursActivity.class));
        });
        btnComplaints.setOnClickListener(v -> {
            startActivity( new Intent(this, QueriesActivity.class));
        });
        btnProfile.setOnClickListener(v -> {
            Toast.makeText(this, "profile details!",Toast.LENGTH_SHORT).show();
        });
        btnProfile=findViewById(R.id.signOutBtn);
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        signOutBtn.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(this, "Good bye!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
