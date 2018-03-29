package com.example.jack_nb.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack_nb.projectfinal.Models.Data;
import com.example.jack_nb.projectfinal.Models.DataStart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView numCount;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DataStart numreal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("State");
        numCount = findViewById(R.id.numCount);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numreal = dataSnapshot.getValue(DataStart.class);
                numCount.setText(String.valueOf(numreal.getNum()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                if (numreal.getStatus() == 0) {
                    DataStart data = new DataStart(0, 1);
                    databaseReference.setValue(data);
                }
            }
        });

        ImageView btnstop = findViewById(R.id.btnstop);
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numreal.getStatus() == 1) {
                    DataStart data = new DataStart(0, 0);
                    databaseReference.setValue(data);
                }
            }
        });
 }
}
