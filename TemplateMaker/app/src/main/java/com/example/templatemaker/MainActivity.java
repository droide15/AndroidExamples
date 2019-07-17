package com.example.templatemaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private List<CaptureField> captureFieldList = new ArrayList<>();
    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ViewParent parentView = parent.getParent();
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) ((ViewGroup) parentView).getTag();

            mAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private RecyclerView recyclerView;
    private CaptureFieldAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CaptureFieldAdapter(captureFieldList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemSelectedListener(onItemSelectedListener);

        recyclerView.setAdapter(mAdapter);

        prepareTemplateData();
    }

    private void prepareTemplateData() {
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
            Log.e(TAG, e.toString());
        }

        mAdapter.notifyDataSetChanged();
    }

    public void up(View view) {
        ViewParent parentView = view.getParent();
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) ((ViewGroup) parentView).getTag();
        int position = viewHolder.getAdapterPosition();

        if (position > 0) {
            Collections.swap(captureFieldList, position, position - 1);
            mAdapter.notifyItemMoved(position, position - 1);
        }
    }

    public void down(View view) {
        ViewParent parentView = view.getParent();
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) ((ViewGroup) parentView).getTag();
        int position = viewHolder.getAdapterPosition();

        if (position < captureFieldList.size() - 1) {
            Collections.swap(captureFieldList, position, position + 1);
            mAdapter.notifyItemMoved(position, position + 1);
        }
    }

    public void remove(View view) {
        ViewParent parentView = view.getParent();
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) ((ViewGroup) parentView).getTag();
        int position = viewHolder.getAdapterPosition();

        captureFieldList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void add(View view) {
        CaptureField captureField = new CaptureField(0);
        captureFieldList.add(captureField);

        mAdapter.notifyDataSetChanged();
    }

    public void reload(View view) {
        captureFieldList.clear();
        prepareTemplateData();
    }

    public void clear(View view) {
        captureFieldList.clear();

        mAdapter.notifyDataSetChanged();
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
            Log.e(TAG, e.toString());
        }
    }

    public void captureData(View view) {
        ArrayList<Integer> capturePreviewList = new ArrayList<>();

        for (CaptureField captureField: captureFieldList) {
            capturePreviewList.add(captureField.getCodeType());
        }

        Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
        intent.putExtra("capturePreviewList", capturePreviewList);
        MainActivity.this.startActivity(intent);

        Toast.makeText(getApplicationContext(), "this is a preview", Toast.LENGTH_LONG).show();
    }

    public void close(View view) {
        finish();
    }
}