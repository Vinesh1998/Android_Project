package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.collection_complaints;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Customer.FindSpecialistActivity;
import com.app.skilledlabour.activities.Customer.LabourDetails;
import com.app.skilledlabour.activities.LoginActivity;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Booking;
import com.app.skilledlabour.models.Query;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class QueryDetails extends BaseActivity {
    Booking booking = new Booking();
    TextView tvCustName,tvQuery,tvClose,tvQueryTime,tvReplyMsg;
    Button btnReply,tvBack;
    EditText etReplyMsg;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this, QueriesActivity.class));
        });
        findViewById(R.id.tvBack).setOnClickListener(view->{
            startActivity(new Intent(this, QueriesActivity.class));
        });
        tvCustName = findViewById(R.id.tvCustName);
        tvQueryTime = findViewById(R.id.tvSlotTime);
        tvQuery = findViewById(R.id.tvQuery);
        tvClose = findViewById(R.id.tvClose);
        etReplyMsg = findViewById(R.id.etReplyMsg);
        tvBack = findViewById(R.id.tvBack);
        tvReplyMsg = findViewById(R.id.tvReplyMsg);
        btnReply = findViewById(R.id.btnReply);

        Intent intent = getIntent();

        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("query_id");
            if(!strId.isEmpty())  getQueryDetails(strId);
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LabourDetails.class));
            }
        }
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    boolean check =false;
    private void getQueryDetails(String strId){
        showProgressDialog();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(collection_complaints).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        try {
                            String id = ds.child("id").getValue(String.class);

                            if (id.equals(strId)) {
                                check = true;
                                String emp_name = ds.child("emp_name").getValue(String.class);
                                String emp_id = ds.child("emp_id").getValue(String.class);
                                String cust_id = ds.child("cust_id").getValue(String.class);
                                String cust_name = ds.child("cust_name").getValue(String.class);
                                String datetime = ds.child("datetime").getValue(String.class);
                                String message = ds.child("msg").getValue(String.class);
                                String subject = ds.child("sub").getValue(String.class);
                                String replyMsg = ds.child("replyMsg").getValue(String.class);
                                boolean status = Boolean.TRUE.equals(ds.child("status").getValue(boolean.class));
                                Query query1 = new Query(id, cust_id, cust_name, emp_id, emp_name, subject, message,
                                        replyMsg, datetime, status);
                                showDetails(query1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(!check){
                        Toast.makeText(getApplicationContext(), "invalid details!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), FindSpecialistActivity.class));
                    }
                }
                hideProgressDialog();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showDetails(Query query){
        tvQuery.setText("Query: "+query.getMsg());
        etReplyMsg.setText(query.getReplyMsg());
        tvCustName.setText(query.getCust_name());
        tvQueryTime.setText(query.getDatetime());

        if(!query.isStatus()){
            btnReply.setVisibility(View.GONE);
            tvClose.setVisibility(View.GONE);
            tvReplyMsg.setText("Reply: "+query.getReplyMsg());
            tvReplyMsg.setVisibility(View.VISIBLE);
            etReplyMsg.setVisibility(View.GONE);
            tvBack.setVisibility(View.VISIBLE);
        }

        btnReply.setOnClickListener(view -> {
            if(!etReplyMsg.getText().toString().isEmpty()) {
                query.setReplyMsg(etReplyMsg.getText().toString());
                query.setStatus(false);
                sendReply(query);
            }else    etReplyMsg.setError("Required!");
        });

        tvClose.setOnClickListener(view -> {
            query.setReplyMsg("can't resolve!");
            query.setStatus(false);
            sendReply(query);
        });

    }

    private void sendReply(Query query){
        FirebaseDatabase.getInstance().getReference().child(collection_complaints).child(query.getId()).setValue(query)
                .addOnSuccessListener(command -> {
                    Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), QueriesActivity.class));
                })
                .addOnFailureListener(command ->{
                    Toast.makeText(this, "failed!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), QueriesActivity.class));
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
