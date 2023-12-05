package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class m_login extends AppCompatActivity {
    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView newMechanicTextView;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        loginButton = findViewById(R.id.m_login);
        emailEditText = findViewById(R.id.m_email_id);
        passwordEditText = findViewById(R.id.m_password_id);
        newMechanicTextView = findViewById(R.id.newmechanic);
        progressBar = findViewById(R.id.progressbar);
        newMechanicTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(m_login.this, activity_mechanic_registration.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(emailEditText.getText());
                String password = String.valueOf(passwordEditText.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(m_login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(m_login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    if (currentUser != null) {
                                        // Create a Mechanic object with relevant information
                                        Mechanic mechanic = new Mechanic(
                                                currentUser.getUid(),
                                                currentUser.getEmail(),
                                                "Mechanic First Name", // Set the default first name
                                                "Mechanic Last Name"   // Set the default last name
                                        );

                                        // Store mechanic information in the database
                                        mDatabase.child("mechanics").child(currentUser.getUid()).setValue(mechanic);

                                        Toast.makeText(m_login.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), activity_mechanicsside.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    Toast.makeText(m_login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
