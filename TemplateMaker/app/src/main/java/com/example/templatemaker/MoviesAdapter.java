package com.example.templatemaker;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>{

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView representation;
        public TextView year;
        public Spinner genre;

        public MyViewHolder(View view) {
            super(view);
            representation = (ImageView) view.findViewById(R.id.representation);
            genre = (Spinner) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        switch (movie.getGenre()) {
            case 0:
                holder.representation.setImageResource(R.drawable.c1d);
                break;
            case 1:
                holder.representation.setImageResource(R.drawable.c2d);
                break;
            case 2:
                holder.representation.setImageResource(R.drawable.text);
                break;
            case 3:
                holder.representation.setImageResource(R.drawable.number);
                break;
        }
        holder.genre.setSelection(movie.getGenre());
        holder.year.setText(movie.getYear());

        holder.genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ViewParent parentView = parent.getParent();
                ImageView representation = (ImageView) ((ViewGroup) parentView).findViewById(R.id.representation);

                switch (position) {
                    case 0:
                        representation.setImageResource(R.drawable.c1d);
                        break;
                    case 1:
                        representation.setImageResource(R.drawable.c2d);
                        break;
                    case 2:
                        representation.setImageResource(R.drawable.text);
                        break;
                    case 3:
                        representation.setImageResource(R.drawable.number);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}