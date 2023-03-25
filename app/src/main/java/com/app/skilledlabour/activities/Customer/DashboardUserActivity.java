package com.app.skilledlabour.activities.Customer;

import static com.app.skilledlabour.helpers.common_helper.collection_customers;
import static com.app.skilledlabour.helpers.common_helper.collection_labours;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
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
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DashboardUserActivity extends AppCompatActivity {
    Button searchBtn,myBookings,labours,btnComplaints,btnProfile,signOutBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    TextView tvUser;
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
        tvUser = findViewById(R.id.tvUser);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(collection_customers).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Customer customer =  task.getResult().getValue(Customer.class);
                try {
                    assert customer != null;
                    String uName = customer.getName();
                    tvUser.setText(uName);
                } catch (Exception ex) {
                    ex.getMessage();
                    mAuth.signOut();
                }
            }
        });

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
