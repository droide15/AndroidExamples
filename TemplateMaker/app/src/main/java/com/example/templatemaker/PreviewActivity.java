package com.example.templatemaker;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreviewActivity extends AppCompatActivity {
    public static final String TAG = PreviewActivity.class.getSimpleName();
    private CapturePreviewAdapter pAdapter;
    private ListView listView;
    private List<Integer> capturePreviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        capturePreviewList = (List<Integer>) getIntent().getSerializableExtra("capturePreviewList");

        pAdapter = new CapturePreviewAdapter(this, capturePreviewList);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(pAdapter);
    }

    public void goBack(View view) {
        finish();
    }
}