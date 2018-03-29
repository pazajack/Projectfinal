package com.example.jack_nb.projectfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jack_nb.projectfinal.Models.Data;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    List<Data> dataStatisticList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Log.d("TEST0", "TES12T");
        BarChart chart = findViewById(R.id.bar_chart);
        dataStatisticList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("history");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TEST0", "TEST");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Data dataGraph = new Data(10, "5");
                    dataStatisticList.add(dataGraph);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final ArrayList<BarEntry> barGraph = new ArrayList<>();
        int index = 0;
        for (Data dataStatisticList : dataStatisticList) {
            barGraph.add(new BarEntry(index, dataStatisticList.getNum()));
            index++;
        }

        BarDataSet dataset = new BarDataSet(barGraph, "#");
        dataset.setValueTextSize(8);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); // set the color

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(dataset);

        BarData data = new BarData(dataSets);

        chart.setData(data);  // set data on chart.

        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setLabelRotationAngle(80);

        final XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(12);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d("benznest", "value = " + value);
                if (value < 0 || value >= dataStatisticList.size()) {
                    return "";
                }
                return dataStatisticList.get((int) value).getDate();
            }
        });

        YAxis RightAxis = chart.getAxisRight();
        RightAxis.setEnabled(false);

        chart.animateY(3000);
    }
}

