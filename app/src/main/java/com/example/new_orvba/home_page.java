package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class home_page extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    private Button button1, button2, breakdownServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        // Initialize the images
        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);

        // Initialize the buttons
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        breakdownServiceButton = findViewById(R.id.button);

        // Set click listener for imageView1
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling imageView1 click
            }
        });

        // Set click listener for imageView2
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this, Nearby_mechanics.class);
                startActivity(intent);
            }
        });

        // Set click listener for imageView3
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling imageView3 click
            }
        });

        // Set click listener for imageView4
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling imageView4 click
            }
        });

        // Set click listener for imageView5
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling imageView5 click
            }
        });

        // Set click listener for imageView6
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling imageView6 click
            }
        });

        // Set click listener for button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling button1 click
            }
        });

        // Set click listener for button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling button2 click
            }
        });

        // Set click listener for breakdownServiceButton
        breakdownServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the breakdown_service activity
                Intent intent = new Intent(home_page.this, breakdown_service.class);
                startActivity(intent);
            }
        });
    }
}
