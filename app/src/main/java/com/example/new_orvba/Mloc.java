package com.example.new_orvba;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import com.google.firebase.auth.FirebaseAuth;

public class Mloc extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView lattitude, longitude, address, city, country;
    Button getLocation, makeARequestButton;
    private final static int REQUEST_CODE = 100;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloaction);

        lattitude = findViewById(R.id.lattitude);
        longitude = findViewById(R.id.longitude);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        getLocation = findViewById(R.id.getLocation);
        makeARequestButton = findViewById(R.id.makearequest);

        databaseReference = FirebaseDatabase.getInstance().getReference("mechanic_location");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
            }
        });

        makeARequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRequest();
            }
        });
    }

    private void submitRequest() {
        MechanicLocationData mechanicLocationData = new MechanicLocationData();
        mechanicLocationData.latitude = Double.parseDouble(lattitude.getText().toString().substring(10));
        mechanicLocationData.longitude = Double.parseDouble(longitude.getText().toString().substring(11));
        mechanicLocationData.address = address.getText().toString().substring(9);
        mechanicLocationData.city = city.getText().toString().substring(6);
        mechanicLocationData.country = country.getText().toString().substring(9);

        // Fetch the logged-in mechanic's ID from Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String mechanicId = currentUser.getUid();

            // Push the location data to the database under the mechanic id
            databaseReference.child(mechanicId).setValue(mechanicLocationData);

            // Display a success message
            Toast.makeText(Mloc.this, "Location Submitted", Toast.LENGTH_SHORT).show();

            // Navigate to the mechanic's side page
            Intent intent = new Intent(Mloc.this, activity_mechanicsside.class);
            startActivity(intent);
        } else {
            // Handle the case where the user is not logged in
            Toast.makeText(Mloc.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                try {
                                    Geocoder geocoder = new Geocoder(Mloc.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    lattitude.setText("Lattitude: " + addresses.get(0).getLatitude());
                                    longitude.setText("Longitude: " + addresses.get(0).getLongitude());
                                    address.setText("Address: " + addresses.get(0).getAddressLine(0));
                                    city.setText("City: " + addresses.get(0).getLocality());
                                    country.setText("Country: " + addresses.get(0).getCountryName());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(Mloc.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(Mloc.this, "Please provide the required permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}