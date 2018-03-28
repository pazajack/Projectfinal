package com.example.jack_nb.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.jack_nb.projectfinal.Models.DataStart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("State");

        // GO TO PAGE DATA
        ImageView btndata = (ImageView) findViewById(R.id.btndata);
        btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this ,DataActivaty.class);
                startActivity(i);
            }
        });

        // GO TO PAGE GRAPH
        ImageView btngraph =  findViewById(R.id.btngraph);
        btngraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this ,Graph.class);
                startActivity(i);
            }
        });

        ImageView btnstart = findViewById(R.id.btnstart);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TEST", "GG");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("TEST", "GG" + dataSnapshot);
//                        if (dataSnapshot.getValue() == 0) {
//                            DataStart data = new DataStart(0, 1);
//                            databaseReference.setValue(data);
//                        } else {
//                            DataStart data = new DataStart(0, 0);
//                            databaseReference.setValue(data);
//                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
 }
}
