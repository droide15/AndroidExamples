package com.example.templatemaker;

public class Movie {
    private String year;
    private int genre;

    public Movie() {
    }

    public Movie(int genre, String year) {
        this.genre = genre;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}