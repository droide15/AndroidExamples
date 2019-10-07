package com.xfusion.templatemakerx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

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