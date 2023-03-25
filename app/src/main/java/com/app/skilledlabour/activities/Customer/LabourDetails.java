package com.app.skilledlabour.activities.Customer;

import static com.app.skilledlabour.helpers.common_helper.collection_labours;
import static com.app.skilledlabour.helpers.common_helper.getAdminLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.QueryAdapter;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Labour;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LabourDetails extends BaseActivity {
    private FirebaseDatabase firebaseDatabase;
    Labour labour = new Labour();
    private DatabaseReference databaseReference;
    TextView tvLabName,tvLabourSkills,tvLabourEmail,tvLabourMobile,tvDelete;
    RatingBar tvLabourRating;
    Button btnBookNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(collection_labours);

        tvLabName = findViewById(R.id.tvLabName);
        tvLabourSkills = findViewById(R.id.tvLabourSkills);
        tvLabourEmail = findViewById(R.id.tvLabourEmail);
        tvLabourMobile = findViewById(R.id.tvLabourMobile);
        btnBookNow = findViewById(R.id.btnBookNow);
        btnBookNow.setVisibility(View.GONE);
        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("labour_id");
            if(!strId.isEmpty()) getLaboursList(strId);
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), com.app.skilledlabour.activities.Admin.LabourDetails.class));
            }
        }
        btnBookNow.setOnClickListener(this::book);
    }

    private void book(View view){
        Toast.makeText(this, "your booking has been placed!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MybookingsActivity.class));
    }

    boolean check =false;
    private Labour getLaboursList(String id) {
        showProgressDialog();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        try {
                            String labourId = ds.child("id").getValue(String.class);
                            if (id.equals(labourId)) {
                                check = true;
                                String name = ds.child("name").getValue(String.class);
                                String email = ds.child("email").getValue(String.class);
                                String mobile = ds.child("mobile").getValue(String.class);
                                String rating = ds.child("rating").getValue(String.class);
                                String skills = ds.child("skill_set").getValue(String.class);
                                String availability = ds.child("availability").getValue(String.class);
                                int age = ds.child("age").getValue(Integer.class);
                                boolean status = Boolean.TRUE.equals(ds.child("status").getValue(boolean.class));
                                labour =new Labour(id, name, email, mobile, age, skills, availability, rating, status);
                                showDetails(labour);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(!check){
                        Toast.makeText(getApplicationContext(), "invalid details!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),FindSpecialistActivity.class));
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return labour;
    }

    private void showDetails(Labour labour){
        btnBookNow.setVisibility(View.VISIBLE);
        tvLabourEmail.setText(labour.getEmail());
        tvLabourMobile.setText(labour.getMobile());
        tvLabName.setText(labour.getName());
        tvLabourSkills.setText(labour.getSkill_set());
        tvLabourRating.setVisibility(View.GONE);
        tvLabourRating.setRating(Integer.parseInt(labour.getName()));
    }

}
