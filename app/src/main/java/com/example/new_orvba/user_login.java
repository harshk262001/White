package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class user_login extends AppCompatActivity {
    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView Tv;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    public void onStart() {
        super.onStart();
        // Removed the auto-redirection logic from onStart
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        loginButton = findViewById(R.id.button);
        emailEditText = findViewById(R.id.Username);
        passwordEditText = findViewById(R.id.Password);
        Tv = findViewById(R.id.newuser);

        Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic for handling the click on the TextView (Tv)
                // For example, navigate to the user registration page
                Intent intent = new Intent(user_login.this, user_registration.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(emailEditText.getText());
                String password = String.valueOf(passwordEditText.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(user_login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(user_login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Get the current user
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    if (currentUser != null) {
                                        // Create a User object with relevant information
                                        User user = new User(
                                                currentUser.getUid(),
                                                currentUser.getEmail(),
                                                "First Name", // Set the default first name
                                                "Last Name"   // Set the default last name
                                        );

                                        // Store user information in the database
                                        mDatabase.child("users").child(currentUser.getUid()).setValue(user);

                                        Toast.makeText(user_login.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), home_page.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    Toast.makeText(user_login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}