package com.example.templatemaker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class CapturePreviewAdapter extends BaseAdapter {

    private final Activity context;
    private final List<Integer> capturePreviewList;

    public CapturePreviewAdapter(Activity context, List<Integer> capturePreviewList) {
        this.context = context;
        this.capturePreviewList = capturePreviewList;
    }

    @Override
    public int getCount() {
        return capturePreviewList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.capturepreview_list_row, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.previewPicture);

        switch (capturePreviewList.get(position)) {
            case 0:
                imageView.setImageResource(R.drawable.c1d_preview);
                break;
            case 1:
                imageView.setImageResource(R.drawable.c2d_preview);
                break;
            case 2:
                imageView.setImageResource(R.drawable.text_preview);
                break;
            case 3:
                imageView.setImageResource(R.drawable.number_preview);
                break;
        }

        return rowView;
    };
}