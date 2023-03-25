package com.app.skilledlabour.activities.Labour;

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

public class DashboardLabourActivity extends AppCompatActivity {
    SkillsAdminAdapter adapter;
    Button btnSkills,myAvailability,btnMyJobs,btnComplaints,btnProfile,signOutBtn;
    RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_dashboard);
        btnSkills = findViewById(R.id.btnSkills);
        tvUser = findViewById(R.id.tvUser);
        myAvailability = findViewById(R.id.myAvailability);
        btnMyJobs = findViewById(R.id.btnMyJobs);
        btnComplaints = findViewById(R.id.btnComplaints);
        btnProfile = findViewById(R.id.btnProfile);
        signOutBtn = findViewById(R.id.signOutBtn);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(collection_labours).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Labour Labour =  task.getResult().getValue(com.app.skilledlabour.models.Labour.class);
                try {
                    assert Labour != null;
                    String uName = Labour.getName();
                    tvUser.setText(uName);
                } catch (Exception ex) {
                    ex.getMessage();
                    mAuth.signOut();
                }
            }
        });
        myAvailability.setOnClickListener(v -> {
            startActivity( new Intent(this, MyAvailability.class));
        });
        btnSkills.setOnClickListener(v -> {
            startActivity( new Intent(this, SkillsActivity.class));
        });
        btnComplaints.setOnClickListener(v -> {
            startActivity( new Intent(this, QueriesActivity.class));
        });
        btnMyJobs.setOnClickListener(v -> {
            startActivity( new Intent(this, MyJobsActivity.class));
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
