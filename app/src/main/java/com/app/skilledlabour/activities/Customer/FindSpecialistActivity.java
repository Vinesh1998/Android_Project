package com.app.skilledlabour.activities.Customer;

import static com.app.skilledlabour.helpers.common_helper.collection_labours;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.LaboursAdminAdapter;
import com.app.skilledlabour.adapter.LaboursCustomerAdapter;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Labour;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindSpecialistActivity extends BaseActivity {
    LaboursCustomerAdapter adapter;
    RecyclerView recyclerView;
    List<Labour> list = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_specialist);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(collection_labours);
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        recyclerView = findViewById(R.id.ListLabour);
        adapter = new LaboursCustomerAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getLaboursList();
    }

    private List<Labour> getLaboursList(){
        if(list.size() != 0) list.clear();
        showProgressDialog();
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.getChildrenCount() != 0){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        try {
                            String id = ds.child("id").getValue(String.class);
                            String name = ds.child("name").getValue(String.class);
                            String email = ds.child("email").getValue(String.class);
                            String mobile = ds.child("mobile").getValue(String.class);
                            String rating = ds.child("rating").getValue(String.class);
                            String skills = ds.child("skill_set").getValue(String.class);
                            String availability = ds.child("availability").getValue(String.class);
                            int age = ds.child("age").getValue(Integer.class);
                            boolean status = Boolean.TRUE.equals(ds.child("status").getValue(boolean.class));
                            list.add( new Labour(id, name, email, mobile, age, skills,availability, rating,status));
                            adapter.notifyDataSetChanged();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
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

        return list;
    }
}
