package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class activity_mechanic_registration extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, phoneEditText, addressEditText, passwordEditText;
    private CheckBox tyreServicesCheckBox, engineServicesCheckBox, oilLeakageCheckBox,
            carTowingCheckBox, manualRepairingCheckBox, otherCheckBox;
    private Button nextButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_registration);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        firstNameEditText = findViewById(R.id.FirstName);
        lastNameEditText = findViewById(R.id.LastName);
        emailEditText = findViewById(R.id.Email);
        phoneEditText = findViewById(R.id.Phone);
        addressEditText = findViewById(R.id.Address);
        passwordEditText = findViewById(R.id.Password1);
        tyreServicesCheckBox = findViewById(R.id.checkBox12);
        engineServicesCheckBox = findViewById(R.id.checkBox13);
        oilLeakageCheckBox = findViewById(R.id.checkBox14);
        carTowingCheckBox = findViewById(R.id.checkBox15);
        manualRepairingCheckBox = findViewById(R.id.checkBox16);
        otherCheckBox = findViewById(R.id.checkBox17);

        nextButton = findViewById(R.id.neext);

        // Set click listener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call a method to register the mechanic and store data in Firebase
                registerMechanicAndStoreData();
            }
        });
    }

    private void registerMechanicAndStoreData() {
        // Get user input from EditText fields
        String mfirstName = firstNameEditText.getText().toString().trim();
        String mlastName = lastNameEditText.getText().toString().trim();
        String memail = emailEditText.getText().toString().trim();
        String mphone = phoneEditText.getText().toString().trim();
        String maddress = addressEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Get the state of CheckBoxes
        boolean tyreServices = tyreServicesCheckBox.isChecked();
        boolean engineServices = engineServicesCheckBox.isChecked();
        boolean oilLeakage = oilLeakageCheckBox.isChecked();
        boolean carTowing = carTowingCheckBox.isChecked();
        boolean manualRepairing = manualRepairingCheckBox.isChecked();
        boolean other = otherCheckBox.isChecked();

        if (TextUtils.isEmpty(memail) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(mfirstName) || TextUtils.isEmpty(mlastName)) {
            Toast.makeText(activity_mechanic_registration.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate password strength
        if (password.length() < 6) {
            Toast.makeText(activity_mechanic_registration.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase Authentication to register the mechanic
        mAuth.createUserWithEmailAndPassword(memail, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration success
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Call a method to store mechanic data in the Realtime Database
                            storeMechanicDataInFirebase(user.getUid(), mfirstName, mlastName, memail, mphone, maddress,
                                    tyreServices, engineServices, oilLeakage, carTowing, manualRepairing, other);

                            // Redirect to MechanicLocationActivity or any other activity
                            Intent intent = new Intent(activity_mechanic_registration.this, Mloc.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        // Registration failed
                        Toast.makeText(activity_mechanic_registration.this, "Registration failed. " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void storeMechanicDataInFirebase(String mechanicId, String mfirstName, String mlastName, String memail, String mphone,
                                             String maddress, boolean tyreServices, boolean engineServices,
                                             boolean oilLeakage, boolean carTowing, boolean manualRepairing, boolean other) {
        // Create a reference to the Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("mechanics");

        // Create a MechanicData object
        Mechanic mechanic = new Mechanic(
                mechanicId,
                mfirstName,
                mlastName,
                memail,
                mphone,
                maddress,
                tyreServices,
                engineServices,
                oilLeakage,
                carTowing,
                manualRepairing,
                other
        );

        // Save the mechanic data to the database
        databaseReference.child(mechanicId).setValue(mechanic);
    }
}
