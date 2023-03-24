package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.getAllQueries;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.QueryAdapter;
import com.app.skilledlabour.adapter.SkillsAdminAdapter;
import com.app.skilledlabour.models.Query;
import com.app.skilledlabour.models.Skill;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class QueriesActivity extends AppCompatActivity {
    QueryAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries);
        setUpRecyclerView();
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }

    public void setUpRecyclerView(){
        List<Query> list = getAllQueries();
        recyclerView = findViewById(R.id.ListSkills);
        adapter = new QueryAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showAddSkill(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.activity_add_skill);
        bottomSheetDialog.show();
    }
}
