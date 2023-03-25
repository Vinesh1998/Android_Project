package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.collection_labours;
import static com.app.skilledlabour.helpers.common_helper.collection_skills;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.QueryAdapter;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Labour;
import com.app.skilledlabour.models.Skill;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LabourDetails extends BaseActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Labour labour = new Labour();
    TextView tvLabName,tvLabourSkills,tvLabourEmail,tvLabourMobile,tvDelete;
    RatingBar tvLabourRating;
    LinearLayout parent_layout;
    Button btnEdit;
    EditText labName, etEmail, etMobile, etSkills;
    Button btnUpdateSkill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_labour_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(collection_labours);

        parent_layout = findViewById(R.id.parent_layout);
        tvLabName = findViewById(R.id.tvLabName);
        tvLabourSkills = findViewById(R.id.tvLabourSkills);
        tvLabourEmail = findViewById(R.id.tvLabourEmail);
        tvLabourMobile = findViewById(R.id.tvLabourMobile);
        btnEdit = findViewById(R.id.btnEdit);
        tvDelete = findViewById(R.id.tvDelete);

        tvDelete.setOnClickListener(view -> {
            Toast.makeText(this, "Will be Delete!", Toast.LENGTH_SHORT).show();
        });

        parent_layout.setVisibility(View.GONE);
        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("labour_id");
            if(!strId.isEmpty()) getLaboursList(strId);
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),LabourDetails.class));
            }
        }


        
        btnEdit.setOnClickListener(v -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.activity_edit_labour);
            bottomSheetDialog.show();

            labName= bottomSheetDialog.findViewById(R.id.labName);
            etEmail= bottomSheetDialog.findViewById(R.id.etEmail);
            labName= bottomSheetDialog.findViewById(R.id.labName);
            etMobile= bottomSheetDialog.findViewById(R.id.etMobile);
            etSkills= bottomSheetDialog.findViewById(R.id.etSkills);
            btnUpdateSkill= bottomSheetDialog.findViewById(R.id.btnUpdateSkill);

            labName.setText(labour.getName());
            etEmail.setText(labour.getEmail());
            etMobile.setText(labour.getEmail());
            etSkills.setText(labour.getSkill_set());

            btnUpdateSkill.setOnClickListener(view->{
                String stLabName = labName.getText().toString();
                String stEmail = etEmail.getText().toString();
                String stMobile = etMobile.getText().toString();
                String stSkills = etSkills.getText().toString();
                if (validateForm(stEmail,stMobile,stLabName,stSkills)) {
                    Toast.makeText(this, "Details will be update!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "invalid details!", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
                bottomSheetDialog.dismiss();
            });
        });
    }
    private boolean validateForm(String email, String mobile, String name, String skills){
        boolean result = true;
        showProgressDialog();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required");
            result = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter valid email!");
            result = false;
        } else etEmail.setError(null);

        if (TextUtils.isEmpty(mobile)) {
            etMobile.setError("Required");
            result = false;
        } else etMobile.setError(null);

        if (TextUtils.isEmpty(name)) {
            labName.setError("Required");
            result = false;
        } else labName.setError(null);

        if (TextUtils.isEmpty(skills)) {
            etSkills.setError("Required");
            result = false;
        } else etSkills.setError(null);

        return result;
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
                        Toast.makeText(LabourDetails.this, "invalid details!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),LabourDetails.class));
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
        parent_layout.setVisibility(View.VISIBLE);
        tvLabourEmail.setText(labour.getEmail());
        tvLabourMobile.setText(labour.getMobile());
        tvLabName.setText(labour.getName());
        tvLabourSkills.setText(labour.getSkill_set());
        tvLabourRating.setVisibility(View.GONE);
        tvLabourRating.setRating(Integer.parseInt(labour.getName()));
    }
}
