package com.logicshore.recyclerviewtypes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerGridView extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid_view);
        recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(RecyclerGridView.this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList personNames = new ArrayList<>(Arrays.asList("AccidentthematicMap", "Accident", "ActionableIntelligence", "CCTV", "Cell Id search", "Courts", "Crime Detection","Crime Investigation"));
        ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.accidentthemantcmap, R.drawable.accidnet, R.drawable.actionableintelligence, R.drawable.cctv, R.drawable.cellidsearch, R.drawable.courts, R.drawable.crimedetection,R.drawable.crimeinvestigation));
        RecyclerGridAdapter recyclerGridAdapter = new RecyclerGridAdapter(RecyclerGridView.this,personImages,personNames);
        recyclerView.setAdapter(recyclerGridAdapter);
    }
}
