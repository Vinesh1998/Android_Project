package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.collection_skills;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.SkillsAdminAdapter;
import com.app.skilledlabour.helpers.BaseActivity;
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

public class SkillsActivity extends BaseActivity {
    SkillsAdminAdapter adapter;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    List<Skill> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();

        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        findViewById(R.id.addSkill).setOnClickListener(view->{
           showAddSkill();
        });

        setUpRecyclerView();

    }

    public void setUpRecyclerView(){
        RecyclerView recyclerView;

        recyclerView = findViewById(R.id.ListSkills);
        adapter = new SkillsAdminAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSkills();
    }

   void getSkills(){
         if(list.size() != 0) list.clear();
        showProgressDialog();
        databaseReference = firebaseDatabase.getReference(collection_skills);
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.getChildrenCount() != 0 ){
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    try {
                        Skill skill = ds.getValue(Skill.class);
                            String id = skill.getId();
                            String itemName = skill.getName();
                            list.add(new Skill(id,itemName,""));
                            adapter.notifyDataSetChanged();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                }else{
                    Toast.makeText(SkillsActivity.this, "empty results!", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
    }

    public void showAddSkill(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.activity_add_skill);
        bottomSheetDialog.show();
        EditText skillName= bottomSheetDialog.findViewById(R.id.skillName);
        Button btnAddSkill= bottomSheetDialog.findViewById(R.id.btnAddSkill);
        btnAddSkill.setOnClickListener(view->{
            String stSkill = skillName.getText().toString();
            if(!stSkill.isEmpty()) {
                String id = UUID.randomUUID().toString();
                Skill skill = new Skill(id,stSkill,"");
                mDatabase.child(collection_skills).child(String.valueOf(id)).setValue(skill);
                Toast.makeText(this, "added!", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                getSkills();
            }
            else Toast.makeText(this, "invalid skill name!", Toast.LENGTH_SHORT).show();
        });
    }
}
