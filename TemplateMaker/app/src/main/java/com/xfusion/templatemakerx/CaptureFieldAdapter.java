package com.xfusion.templatemakerx;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

public class CaptureFieldAdapter extends RecyclerView.Adapter<CaptureFieldAdapter.MyViewHolder>{

    private List<CaptureField> captureFieldList;
    private AdapterView.OnItemSelectedListener onItemSelectedListener;
    private AdapterView.OnClickListener onClickListener;

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setOnClickListener(AdapterView.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView codePicture;
        public Spinner codeType;
        public ImageView answers;

        public MyViewHolder(View view) {
            super(view);
            codePicture = (ImageView) view.findViewById(R.id.codePicture);
            codeType = (Spinner) view.findViewById(R.id.codeType);
            answers = (ImageView) view.findViewById(R.id.answers);

            view.setTag(this);
        }
    }

    public CaptureFieldAdapter(List<CaptureField> captureFieldList) {
        this.captureFieldList = captureFieldList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.capturefield_list_row, parent, false);

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
        if (captureField.isAnswering()) {
            holder.answers.setVisibility(View.VISIBLE);
        } else {
            holder.answers.setVisibility(View.GONE);
        }

        holder.codeType.setOnItemSelectedListener(onItemSelectedListener);
        holder.codePicture.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return captureFieldList.size();
    }
}