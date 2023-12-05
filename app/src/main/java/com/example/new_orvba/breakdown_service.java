package com.example.new_orvba;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class breakdown_service extends AppCompatActivity {

    private Button checkoutButton;
    private Button previousButton;
    private CheckBox tyreServiceCheckbox;
    private CheckBox fuelDeliveryCheckbox;
    private CheckBox carTowingCheckbox;
    private CheckBox oilLeakageCheckbox;
    private CheckBox mechanicalFaultsCheckbox;
    private CheckBox carRepairingCheckbox;
    private CheckBox batteryJumpStartCheckbox;
    private CheckBox accidentialRepairCheckbox;
    private EditText vehicleNameEditText;
    private EditText defaultTroubleEditText;

    // Firebase
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakdown_service);

        // Initialize the buttons and other UI components
        checkoutButton = findViewById(R.id.button2);
        previousButton = findViewById(R.id.button3);
        tyreServiceCheckbox = findViewById(R.id.checkBox);
        fuelDeliveryCheckbox = findViewById(R.id.checkBox5);
        carTowingCheckbox = findViewById(R.id.checkBox3);
        oilLeakageCheckbox = findViewById(R.id.checkBox4);
        mechanicalFaultsCheckbox = findViewById(R.id.checkBox6);
        carRepairingCheckbox = findViewById(R.id.checkBox7);
        batteryJumpStartCheckbox = findViewById(R.id.checkBox9);
        accidentialRepairCheckbox = findViewById(R.id.checkBox10);
        vehicleNameEditText = findViewById(R.id.editTextText4);
        defaultTroubleEditText = findViewById(R.id.editTextText5);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("breakdownRequests");

        // Set click listener for the Checkout button
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(breakdown_service.this, Nearby_mechanics.class);
                startActivity(intent);
            }
        });
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from UI components
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String vehicleName = vehicleNameEditText.getText().toString().trim();
                boolean tyreService = tyreServiceCheckbox.isChecked();
                boolean fuelDelivery = fuelDeliveryCheckbox.isChecked();
                boolean carTowing = carTowingCheckbox.isChecked();
                boolean oilLeakage = oilLeakageCheckbox.isChecked();
                boolean mechanicalFaults = mechanicalFaultsCheckbox.isChecked();
                boolean carRepairing = carRepairingCheckbox.isChecked();
                boolean batteryJumpStart = batteryJumpStartCheckbox.isChecked();
                boolean accidentialRepair = accidentialRepairCheckbox.isChecked();
                String defaultTrouble = defaultTroubleEditText.getText().toString().trim();

                // Save data to the database
                String requestId = databaseReference.child(userId).push().getKey();

                BreakdownRequest breakdownRequest = new BreakdownRequest(userId, "", vehicleName, "",
                        tyreService, fuelDelivery, carTowing, oilLeakage,
                        mechanicalFaults, carRepairing, batteryJumpStart, accidentialRepair,
                        defaultTrouble);
                databaseReference.child(userId).child(requestId).setValue(breakdownRequest);

                // Navigate to the user_location activity
                Intent intent = new Intent(breakdown_service.this, activity_user_location.class);
                startActivity(intent);
            }
        });

        // Set click listener for the Previous button
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for handling the Previous button click
                // For example, you can navigate back to the home_page activity
                Intent intent = new Intent(breakdown_service.this, home_page.class);
                startActivity(intent);
            }
        });
    }
}
