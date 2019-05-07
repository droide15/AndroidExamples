package com.example.templatemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<CaptureField> captureFieldList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CaptureFieldAdapter mAdapter;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CaptureFieldAdapter(captureFieldList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                currentPosition = position;
                mAdapter.setCurrentPosition(position);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        prepareMovieData();
    }

    private void prepareMovieData() {
        currentPosition = -1;
        mAdapter.setCurrentPosition(-1);

        try {
            FileInputStream fin = openFileInput("template.txt");

            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                if (c != '\n') {
                    temp = temp + Character.toString((char)c);
                } else {
                    String[] fields = temp.split(",");
                    captureFieldList.add(new CaptureField(Integer.parseInt(fields[0])));
                    temp="";
                }
            }

            fin.close();
        } catch (Exception e) {
            Log.e("MaintActivity", e.toString());
        }

        mAdapter.notifyDataSetChanged();
    }

    public void add(View view) {
        currentPosition = -1;
        mAdapter.setCurrentPosition(-1);

        CaptureField captureField = new CaptureField(0);
        captureFieldList.add(captureField);

        mAdapter.notifyDataSetChanged();
    }

    public void up(View view) {
        if (currentPosition > 0) {
            Collections.swap(captureFieldList, currentPosition, currentPosition - 1);
            mAdapter.notifyItemMoved(currentPosition, currentPosition - 1);
        }
    }

    public void down(View view) {
        if (currentPosition < captureFieldList.size() - 1) {
            Collections.swap(captureFieldList, currentPosition, currentPosition + 1);
            mAdapter.notifyItemMoved(currentPosition, currentPosition + 1);
        }
    }

    public void remove(View view) {
        captureFieldList.remove(currentPosition);
        mAdapter.notifyItemRemoved(currentPosition);
    }

    public void save(View view) {
        try {
            FileOutputStream fOut = openFileOutput("template.txt",Context.MODE_PRIVATE);

            for (CaptureField captureField : captureFieldList) {
                String str = captureField.getCodeType() + "\n";
                fOut.write(str.getBytes());
            }
            fOut.close();
        } catch (Exception e) {
            Log.e("MaintActivity", e.toString());
        }
    }

    //TODO: https://www.androidhive.info/2016/01/android-working-with-recycler-view/
    //TODO: https://android-arsenal.com/details/1/4083#!package
}