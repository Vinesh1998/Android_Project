package com.app.skilledlabour.activities.Labour;

import static com.app.skilledlabour.helpers.common_helper.getLabourById;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Labour;

public class LabourProfile extends BaseActivity {
    TextView tvName,tvJob,tvEmail,tvMobile;
    Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_profile);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(this, MyJobsActivity.class));
        });
        tvName = findViewById(R.id.tvName);
        tvJob = findViewById(R.id.tvSkills);
        tvEmail = findViewById(R.id.tvEmail);
        tvMobile = findViewById(R.id.tvMobile);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();
        if (null != intent) {
            //Null Checking
            String strId= intent.getStringExtra("lab_id");
            if(!strId.isEmpty()){
                Labour labour= getLabourById(strId);
                if(!labour.getId().equals(""))
                    showDetails(labour);
                else{
                    Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LabourProfile.class));
                }
            }
            else {
                Toast.makeText(this, "invalid!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LabourProfile.class));
            }
        }

        btnHome.setOnClickListener(view->{
            startActivity(new Intent(this, DashboardLabourActivity.class));
        });
    }

    private void showDetails(Labour labour){
        tvName.setText(labour.getName());
        tvJob.setText(labour.getSkill_set());
        tvEmail.setText(labour.getEmail());
        tvMobile.setText(labour.getMobile());
    }
}
