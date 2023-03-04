package com.app.skilledlabour.activities.Admin;

import static com.app.skilledlabour.helpers.common_helper.getSkills;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.skilledlabour.R;
import com.app.skilledlabour.adapter.SkillsAdminAdapter;
import com.app.skilledlabour.models.Skill;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.List;

public class SkillsActivity extends AppCompatActivity {
    SkillsAdminAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        setUpRecyclerView();
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        findViewById(R.id.addSkill).setOnClickListener(view->{
           showAddSkill();
        });
    }

    public void setUpRecyclerView(){
        List<Skill> list = getSkills();
        recyclerView = findViewById(R.id.ListSkills);
        adapter = new SkillsAdminAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showAddSkill(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.activity_add_skill);
        bottomSheetDialog.show();
    }
}
