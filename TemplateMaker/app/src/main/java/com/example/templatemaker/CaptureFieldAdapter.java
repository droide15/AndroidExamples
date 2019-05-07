package com.example.templatemaker;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class CaptureFieldAdapter extends RecyclerView.Adapter<CaptureFieldAdapter.MyViewHolder>{

    private List<CaptureField> captureFieldList;
    private int currentPosition;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView codePicture;
        public Spinner codeType;

        public MyViewHolder(View view) {
            super(view);
            codePicture = (ImageView) view.findViewById(R.id.codePicture);
            codeType = (Spinner) view.findViewById(R.id.codeType);
        }
    }

    public CaptureFieldAdapter(List<CaptureField> captureFieldList) {
        this.captureFieldList = captureFieldList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.codefield_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CaptureField captureField = captureFieldList.get(position);

        switch (captureField.getCodeType()) {
            case 0:
                holder.codePicture.setImageResource(R.drawable.c1d);
                break;
            case 1:
                holder.codePicture.setImageResource(R.drawable.c2d);
                break;
            case 2:
                holder.codePicture.setImageResource(R.drawable.text);
                break;
            case 3:
                holder.codePicture.setImageResource(R.drawable.number);
                break;
        }
        holder.codeType.setSelection(captureField.getCodeType());

        holder.codeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (currentPosition > -1) {
                    ViewParent parentView = parent.getParent();
                    ImageView codePicture = (ImageView) ((ViewGroup) parentView).findViewById(R.id.codePicture);

                    switch (position) {
                        case 0:
                            codePicture.setImageResource(R.drawable.c1d);
                            break;
                        case 1:
                            codePicture.setImageResource(R.drawable.c2d);
                            break;
                        case 2:
                            codePicture.setImageResource(R.drawable.text);
                            break;
                        case 3:
                            codePicture.setImageResource(R.drawable.number);
                            break;
                    }

                    CaptureField temp = captureFieldList.get(currentPosition);
                    temp.setCodeType(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return captureFieldList.size();
    }

    public void setCurrentPosition(int position) {
        currentPosition = position;
    }
}