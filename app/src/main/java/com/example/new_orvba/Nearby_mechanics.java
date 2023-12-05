package com.example.new_orvba;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Nearby_mechanics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_mechanics);
        LinearLayout l=findViewById(R.id.ll);

        DatabaseReference mecanic_fetch = FirebaseDatabase.getInstance().getReference("mechanics");
        mecanic_fetch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
String resukt="";

                for(DataSnapshot i : snapshot.getChildren()){
                    resukt+=i.child("mfirstName").getValue(String.class);
                    resukt+="\t";
                    resukt+=i.child("mlastName").getValue(String.class);
                    resukt+="\n\t\t";
                    resukt+=i.child("mphone").getValue(String.class);
                    resukt+="\n\n\n";
TextView t=new TextView(Nearby_mechanics.this);
t.setText(resukt);
t.setTextSize(16);
l.addView(t);resukt="";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}


