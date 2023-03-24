package com.app.skilledlabour.activities;

import static android.content.ContentValues.TAG;

import static com.app.skilledlabour.helpers.common_helper.collection_customers;
import static com.app.skilledlabour.helpers.common_helper.collection_labours;
import static com.app.skilledlabour.helpers.common_helper.getAdminLogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.skilledlabour.R;
import com.app.skilledlabour.activities.Admin.DashboardAdminActivity;
import com.app.skilledlabour.activities.Customer.DashboardUserActivity;
import com.app.skilledlabour.activities.Labour.DashboardLabourActivity;
import com.app.skilledlabour.helpers.BaseActivity;
import com.app.skilledlabour.models.Customer;
import com.app.skilledlabour.models.Labour;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends BaseActivity  {
    Button btnLogin;
    TextView tvCreateAccount;
    EditText etEmail, etPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    RadioGroup rgRole;
    RadioButton rbRole;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.LoginBtn);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvCreateAccount = findViewById(R.id.CreateAccount);

        rgRole = findViewById(R.id.rgRole);
        rbRole = findViewById(rgRole.getCheckedRadioButtonId());
        role = rbRole.getText().toString();

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        rgRole.setOnCheckedChangeListener((group, checkedId) -> {
            rbRole = findViewById(checkedId);
            role = rbRole.getText().toString();
        });

        btnLogin.setOnClickListener(view -> {
            if (!validateForm()) {
                return;
            }
            if (role.equals("Admin") && getAdminLogin(etEmail.getText().toString().trim(),etPassword.getText().toString().trim())) {
                startActivity(new Intent(getApplicationContext(), DashboardAdminActivity.class));
            }else{
                signIn(etEmail.getText().toString().trim(), etPassword.getText().toString().trim(), view);
            }
        });
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child(collection_labours).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Labour Labour =  task.getResult().getValue(com.app.skilledlabour.models.Labour.class);
                        try {
                            assert Labour != null;
                            String uName = Labour.getName();
//                            Toast.makeText(this, "welcome " + uName, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DashboardLabourActivity.class));
                        } catch (Exception ex) {
                            ex.getMessage();
                            mAuth.signOut();
                        }
                    } else {
                        mDatabase.child(collection_customers).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Customer customer = task1.getResult().getValue(Customer.class);
                                try {
                                    assert customer != null;
                                    String uName = customer.getName();
//                                    Toast.makeText(this, "welcome " + uName, Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    ex.getMessage();
                                    mAuth.signOut();
                                }
                            }
                        });
                    }
                });
            }
        };
        tvCreateAccount.setOnClickListener(view->
                startActivity(new Intent(getApplicationContext(), CreateAccountActivity.class))
        );
    }

    private void signIn(String email, String password, View view) {
        Log.d(TAG, "signIn:" + email);
        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithEmail", task.getException());
                        Snackbar.make(view, "Invalid Credentials!!", Snackbar.LENGTH_SHORT)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view1) {
                                    }
                                })
                                .setActionTextColor(getResources().getColor(R.color.red))
                                .show();
                    }
                    else{
                        //check user role
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        if(!role.equals("Admin")) {
                            mDatabase.child(collection_labours).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task0 -> {
                                if (task0.isSuccessful()) {
                                    Labour Labour = task0.getResult().getValue(com.app.skilledlabour.models.Labour.class);
                                    try {
                                        assert Labour != null;
                                        if (Labour.isStatus())
                                            startActivity(new Intent(getApplicationContext(), DashboardLabourActivity.class));
                                        else {
                                            Toast.makeText(this, "user blocked!", Toast.LENGTH_SHORT).show();
                                            mAuth.signOut();
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    } catch (Exception ex) {
                                        ex.getMessage();
                                        mAuth.signOut();
                                    }
                                }else{
                                    mDatabase.child(collection_customers).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Customer customer =  task1.getResult().getValue(com.app.skilledlabour.models.Customer.class);
                                            try {
                                                assert customer != null;
                                                if(customer.isStatus())
                                                    startActivity(new Intent(getApplicationContext(),DashboardUserActivity.class));
                                                else{
                                                    Toast.makeText(this, "invalid User!", Toast.LENGTH_SHORT).show();
                                                    mAuth.signOut();
                                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(intent);
                                                }
                                            } catch (Exception ex) {
                                                ex.getMessage();
                                                mAuth.signOut();
                                            }
                                        }else{
                                            Toast.makeText(this, "user blocked!", Toast.LENGTH_SHORT).show();
                                            mAuth.signOut();
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }

                    }
                    // [START_EXCLUDE]
                    hideProgressDialog();
                    // [END_EXCLUDE]
                });
        // [END sign_in_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required.");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required.");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        return valid;
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]
    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
  }