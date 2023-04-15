package com.app.skilledlabour.activities.Labour;

import static android.content.ContentValues.TAG;
import static com.app.skilledlabour.helpers.common_helper.collection_complaints;
import static com.app.skilledlabour.helpers.common_helper.collection_labours;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.LoginActivity;
import com.app.skilledlabour.adapter.QueryLabourAdapter;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Labour;
import com.app.skilledlabour.models.Query;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QueriesActivity extends BaseActivity {
    QueryLabourAdapter adapter;
    RecyclerView recyclerView;
    List<Query> complaintList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String uName = "";
    TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);

        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), DashboardLabourActivity.class));
        });
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(collection_labours).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Labour labour =  task.getResult().getValue(Labour.class);
                try {
                    assert labour != null;
                    uName = labour.getName();
                } catch (Exception ex) {
                    ex.getMessage();
                    signout();
                }
            }
        });
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        recyclerView = findViewById(R.id.ListSkills);
        tvMsg = findViewById(R.id.tvMsg);
        adapter = new QueryLabourAdapter(complaintList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getComplaintsList();
    }

    private void getComplaintsList(){
        if(complaintList.size() != 0) complaintList.clear();
        showProgressDialog();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(collection_complaints);
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.getChildrenCount() != 0){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        try {
                            String id = ds.child("id").getValue(String.class);
                            String emp_name = ds.child("emp_name").getValue(String.class);
                            String emp_id = ds.child("emp_id").getValue(String.class);
                            String cust_id = ds.child("cust_id").getValue(String.class);
                            String cust_name = ds.child("cust_name").getValue(String.class);
                            String datetime = ds.child("datetime").getValue(String.class);
                            String message = ds.child("msg").getValue(String.class);
                            String subject = ds.child("sub").getValue(String.class);
                            String replyMsg = ds.child("replyMsg").getValue(String.class);
                            boolean status = Boolean.TRUE.equals(ds.child("status").getValue(boolean.class));
                            if(emp_id.equals(mAuth.getUid())) {
                                complaintList.add(new Query(id, cust_id, cust_name, emp_id, emp_name, subject, message,
                                        replyMsg, datetime, status));
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                            Log.e(TAG, "onDataChange: "+e.getMessage() );
                        }
                    }
                    if(complaintList.size() == 0){
                        Toast.makeText(QueriesActivity.this, "no complaints found!", Toast.LENGTH_SHORT).show();
                        tvMsg.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }else{
                        adapter.notifyDataSetChanged();
                        tvMsg.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "List is empty!", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
    });
    }

    private void signout(){
        mAuth.signOut();
        Toast.makeText(this, "Good bye!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
