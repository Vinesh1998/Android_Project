package com.app.skilledlabour.activities;

import static android.content.ContentValues.TAG;

import static com.app.skilledlabour.helpers.common_helper.collection_customers;
import static com.app.skilledlabour.helpers.common_helper.collection_labours;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.skilledlabour.R;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends BaseActivity {
    Button btnCreateAccount;
    TextView tvLogin;
    RadioGroup rgUserRole;
    RadioButton rbRole;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    LinearLayout layoutLabour, layoutUser;
    String  role = "Labour";
    EditText etLabName, etLabMobile, etLabEmail, etLabAge, etLabPass, etLabCnfPass,
            etUsrName, etUsrMobile, etUsrEmail, etUsrAddress, etUsrPass, etUsrCnfPass;
    String  stLabName, stLabMobile, stLabEmail, stLabAge, stLabPass,stLabCnfPass,
            stUsrName, stUsrMobile, stUsrEmail, stUsrAddress, stUsrPass, stUsrCnfPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        tvLogin = findViewById(R.id.tvLogin);
        btnCreateAccount = findViewById(R.id.CreateAccount);
        layoutLabour = findViewById(R.id.viewLabour);
        layoutUser = findViewById(R.id.viewUser);

        etLabName = findViewById(R.id.etName);
        etLabEmail = findViewById(R.id.etEmail);
        etLabPass = findViewById(R.id.etPassword);
        etLabCnfPass = findViewById(R.id.etConfPass);
        etLabAge = findViewById(R.id.etAge);
        etLabMobile = findViewById(R.id.etMobile);

        etUsrName = findViewById(R.id.etCustName);
        etUsrEmail = findViewById(R.id.etCustEmail);
        etUsrMobile = findViewById(R.id.etCustMobile);
        etUsrAddress = findViewById(R.id.etCustAddress);
        etUsrPass = findViewById(R.id.etCustPassword);
        etUsrCnfPass = findViewById(R.id.etCustConfPass);

        rgUserRole = findViewById(R.id.rgRole);
        rgUserRole.setOnCheckedChangeListener((group, checkedId) -> {
            rbRole = findViewById(checkedId);
            role = rbRole.getText().toString();
            if(role.equals("User")){
                layoutUser.setVisibility(View.VISIBLE);
                layoutLabour.setVisibility(View.GONE);
            }
            else{
                layoutUser.setVisibility(View.GONE);
                layoutLabour.setVisibility(View.VISIBLE);
            }
        });

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnCreateAccount.setOnClickListener(this::signUp);
        tvLogin.setOnClickListener(this::login);
    }

    private void login(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    private boolean validateForm() {
        boolean result = true;

        stLabEmail = etLabEmail.getText().toString().trim();
        stLabName = etLabName.getText().toString().trim();
        stLabMobile = etLabMobile.getText().toString().trim();
        stLabAge = etLabAge.getText().toString().trim();
        stLabPass = etLabPass.getText().toString().trim();
        stLabCnfPass = etLabCnfPass.getText().toString().trim();

        stUsrName = etUsrName.getText().toString().trim();
        stUsrEmail = etUsrEmail.getText().toString().trim();
        stUsrMobile = etUsrMobile.getText().toString().trim();
        stUsrAddress = etUsrAddress.getText().toString().trim();
        stUsrPass = etUsrPass.getText().toString().trim();
        stUsrCnfPass = etUsrCnfPass.getText().toString().trim();

        if(role.equals("Labour")) {
            if (TextUtils.isEmpty(stLabEmail)) {
                etLabEmail.setError("Required");
                result = false;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(stLabEmail).matches()) {
                etLabEmail.setError("Enter valid email!");
                result = false;
            } else etLabEmail.setError(null);

            if (TextUtils.isEmpty(stLabName)) {
                etLabName.setError("Required");
                result = false;
            } else etLabName.setError(null);

            if (TextUtils.isEmpty(stLabMobile)) {
                etLabMobile.setError("Required");
                result = false;
            } else etLabMobile.setError(null);

            if (TextUtils.isEmpty(stLabAge)) {
                etLabAge.setError("Required");
                result = false;
            } else etLabAge.setError(null);

            if (TextUtils.isEmpty(stLabPass)) {
                etLabPass.setError("Required");
                result = false;
            } else {
                etLabPass.setError(null);
            }

            if (TextUtils.isEmpty(stLabCnfPass)) {
                etLabCnfPass.setError("Required");
                result = false;
            } else if (!stLabCnfPass.equals(stLabPass)) {
                result = false;
                etLabCnfPass.setError("Password Mismatch!!");
            } else {
                etLabCnfPass.setError(null);
            }
        }
        else{
            if (TextUtils.isEmpty(stUsrEmail)) {
                etUsrEmail.setError("Required");
                result = false;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(stUsrEmail).matches()) {
                etUsrEmail.setError("Enter valid email!");
                result = false;
            } else etUsrEmail.setError(null);

            if (TextUtils.isEmpty(stUsrName)) {
                etUsrName.setError("Required");
                result = false;
            } else etUsrName.setError(null);

            if (TextUtils.isEmpty(stUsrMobile)) {
                etUsrMobile.setError("Required");
                result = false;
            } else etUsrMobile.setError(null);

            if (TextUtils.isEmpty(stUsrAddress)) {
                etUsrAddress.setError("Required");
                result = false;
            } else etUsrAddress.setError(null);

            if (TextUtils.isEmpty(stUsrPass)) {
                etUsrPass.setError("Required");
                result = false;
            } else {
                etUsrPass.setError(null);
            }

            if (TextUtils.isEmpty(stUsrCnfPass)) {
                etUsrCnfPass.setError("Required");
                result = false;
            } else if (!stUsrCnfPass.equals(stUsrPass)) {
                result = false;
                etUsrCnfPass.setError("Password Mismatch!!");
            } else {
                etUsrCnfPass.setError(null);
            }
        }

        return result;
    }

    private void signUp(View view) {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        if(role.equals("User")) {
            mAuth.createUserWithEmailAndPassword(stUsrEmail, stUsrPass)
                    .addOnCompleteListener(task -> {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            onUserAuthSuccess(task.getResult().getUser());
                        } else {
                            Snackbar.make(view, "error: " + task.getException().getMessage(), Snackbar.LENGTH_SHORT)
                                    .setAction("OK", view1 -> {
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.red))
                                    .show();
                        }
                    });
        }
        else {
            mAuth.createUserWithEmailAndPassword(stLabEmail, stLabPass)
                    .addOnCompleteListener(task -> {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            onLabourAuthSuccess(task.getResult().getUser());
                        } else {
                            Snackbar.make(view, "error: " + task.getException().getMessage(), Snackbar.LENGTH_SHORT)
                                    .setAction("OK", view1 -> {
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.red))
                                    .show();
                        }
                    });
        }
    }

    private void onLabourAuthSuccess(FirebaseUser user) {
        // Write new user
        writeNewLabour(user.getUid(), user.getEmail(),stLabName,stLabMobile,"",Integer.parseInt(stLabAge));
        // Go to MainActivity
        Toast.makeText(this,"User account created!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void onUserAuthSuccess(FirebaseUser user) {
        // Write new user
         writeNewUser(user.getUid(), user.getEmail(),stUsrName,stUsrMobile,stUsrAddress);
        // Go to MainActivity
        Toast.makeText(this,"User account created!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    // [START basic_write]
    private void writeNewLabour(String userId,String email, String name, String mobile,String skills, int age) {
        Labour user = new Labour(userId,name, email, mobile, age, skills,"","4.5",true);
        mDatabase.child(collection_labours).child(userId).setValue(user);
    }

    private void writeNewUser(String userId, String email, String name, String mobile, String address) {
        Customer user = new Customer(userId,email, name,mobile, address, true);
        mDatabase.child(collection_customers).child(userId).setValue(user);
    }
}