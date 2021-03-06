package com.example.camcuz97.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity  {
    ArrayList<Movie> movies;
    MoviesAdapter movieAdapter;
    ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        movies = new ArrayList<>();
        lvItems = (ListView) findViewById(R.id.lvMovies);
        movieAdapter = new MoviesAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        setupListViewListener();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    Log.d("Debug", movies.toString());
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                 super.onFailure(statusCode, headers, responseString, throwable);
            }
        });


//        //1. Get the actual movies
//        ArrayList<Movie> movies = Movie.getFakeMovies();
//
//        //2. Get the ListView that we want to populate
//        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
//
//        //3. Create an ArrayAdapter
//        //ArrayAdapter - Takes list and maps to view
//        //ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
//        MoviesAdapter adapter = new MoviesAdapter(this, movies);
//
//        //4. Associate the adapter with the ListView
//        if(lvMovies != null){
//            lvMovies.setAdapter(adapter);
//        }


    }


    private void setupListViewListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                Log.i("HelloListView", "You clicked: " + id + " at position " + pos);
                Intent i =  new Intent();
                i.setClass(MoviesActivity.this, InfoActivity.class);
                Movie mov = movies.get(pos);
                i.putExtra("title", mov.getTitle());
                i.putExtra("description", mov.getDescription());
                i.putExtra("rating", mov.getRating());
                i.putExtra("votes", mov.getNumVotes());
                i.putExtra("date", mov.getDate());
                i.putExtra("backdrop", mov.getLandscapePath());
                Log.i("ByeListView","Put everything");
                startActivity(i);
            }
        });
    }
}