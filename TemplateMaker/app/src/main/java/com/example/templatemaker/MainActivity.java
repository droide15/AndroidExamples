package com.example.templatemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        Movie movie = new Movie(0, "2015");
        movieList.add(movie);

        movie = new Movie(1, "2015");
        movieList.add(movie);

        movie = new Movie(2, "2015");
        movieList.add(movie);

        movie = new Movie(3, "2015");
        movieList.add(movie);

        movie = new Movie(0, "2015");
        movieList.add(movie);

        movie = new Movie(1, "2015");
        movieList.add(movie);

        movie = new Movie(2, "2009");
        movieList.add(movie);

        movie = new Movie(3, "2009");
        movieList.add(movie);

        movie = new Movie(0, "2014");
        movieList.add(movie);

        movie = new Movie(1, "2008");
        movieList.add(movie);

        movie = new Movie(2, "1986");
        movieList.add(movie);

        movie = new Movie(3, "2000");
        movieList.add(movie);

        movie = new Movie(0, "1985");
        movieList.add(movie);

        movie = new Movie(1, "1981");
        movieList.add(movie);

        movie = new Movie(2, "1965");
        movieList.add(movie);

        movie = new Movie(3, "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    //TODO: https://www.androidhive.info/2016/01/android-working-with-recycler-view/
    //TODO: https://www.journaldev.com/9383/android-internal-storage-example-tutorial
    //TODO: https://android-arsenal.com/details/1/4083#!package
}