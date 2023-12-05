package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button userLoginButton, mechanicsLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        // Initialize the buttons
        userLoginButton = findViewById(R.id.button6);
        mechanicsLoginButton = findViewById(R.id.button99);

        // Set click listener for userLoginButton
        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling userLoginButton click
                // For example, you can start a new activity for user login
                Intent intent = new Intent(MainActivity.this, user_login.class);
                startActivity(intent);
            }
        });

        // Set click listener for mechanicsLoginButton
        mechanicsLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling the Mechanics Login button click
                // For example, you can navigate to the mechanic login activity
                Intent intent = new Intent(MainActivity.this, m_login.class);
                startActivity(intent);
            }
        });
    }
}
