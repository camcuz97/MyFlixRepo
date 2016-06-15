package com.example.camcuz97.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by camcuz97 on 6/15/16.
 */
public class Movie {
//    public String title;
//    public String posterUrl;
//    public int rating;
//
//    public Movie(String title, String posterUrl, int rating) {
//        this.title = title;
//        this.rating = rating;
//        this.posterUrl = posterUrl;
//    }
//
//    public static ArrayList<Movie> getFakeMovies(){
//        ArrayList<Movie> movies = new ArrayList<>();
//        for(int i = 0; i < 60; i++){
//            movies.add(new Movie("The Social Network", "", 75));
//            movies.add(new Movie("The Internship", "", 50));
//            movies.add(new Movie("The Lion King", "", 100));
//        }
//        return movies;
//    }
//
//    @Override
//    public String toString() {
//        return title + " - " + rating;
//    }
    public String posterPath;
    public String landscapePath;
    public String title;
    public String description;

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("original_title");
        description = jsonObject.getString("overview");
        landscapePath =  jsonObject.getString("backdrop_path");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) throws JSONException {
        ArrayList<Movie> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            results.add(new Movie(array.getJSONObject(i)));
        }
        return results;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLandscapePath() {
        return String.format("http://image.tmdb.org/t/p/w780/%s", landscapePath);
    }
}
