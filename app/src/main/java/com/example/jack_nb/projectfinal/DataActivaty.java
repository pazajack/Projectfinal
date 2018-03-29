package com.example.jack_nb.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack_nb.projectfinal.Adapter.DataStatistic;
import com.example.jack_nb.projectfinal.Models.Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataActivaty extends AppCompatActivity {

    TextView textviewdata;
    FirebaseDatabase database;
    DatabaseReference myRef;

    RecyclerView mRecycler;
    DataStatistic mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_data);

        mRecycler = findViewById(R.id.showdata);
        mRecycler.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(layoutManager);

//      DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecycler.getContext(), DividerItemDecoration.VERTICAL);
//      mRecycler.addItemDecoration(dividerItemDecoration);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("history/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Data> dataStatisticList = new ArrayList<>();

                 for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                      Data dataNum = postSnapshot.getValue(Data.class);
                      dataStatisticList.add(dataNum);
                   }

                    setAdapter(dataStatisticList);
                }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });
          }

    private void setAdapter(List<Data> datastatisticList) {
        mAdapter = new DataStatistic(this, datastatisticList);
        mRecycler.setAdapter(mAdapter);

        // GO TO PAGE GRAPH
        ImageView btnhome = (ImageView) findViewById(R.id.btnhome);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DataActivaty.this ,MainActivity.class);
                startActivity(i);
            }
        });

        // GO TO PAGE GRAPH
        ImageView btngraph = (ImageView) findViewById(R.id.btngraph);
        btngraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DataActivaty.this ,Graph.class);
                startActivity(i);
            }
        });
    }
}
