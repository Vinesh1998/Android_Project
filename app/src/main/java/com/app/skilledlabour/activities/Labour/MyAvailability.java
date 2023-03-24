package com.app.skilledlabour.activities.Labour;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.app.skilledlabour.R;

public class MyAvailability extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myavailability);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
    }
}
