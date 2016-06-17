package com.example.camcuz97.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by camcuz97 on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder{
        TextView name;
        TextView description;
        ImageView img;
    }

    public MoviesAdapter(Context context , ArrayList<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.description = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.ivPoster);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(movie.getTitle());
        viewHolder.description.setText(movie.getDescription());
        viewHolder.img.setImageResource(0);
        // Lookup view for data population
        //TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        //TextView tvDescription = (TextView) convertView.findViewById(R.id.tvOverview);
        //ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
        //ivPoster.setImageResource(0);
        // Populate the data into the template view using the data object
        //tvTitle.setText(movie.getTitle());
        //tvDescription.setText(movie.getDescription());

        Log.d("MoviesAdapter", "Position: " + position);

        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if(isLandscape){
            Picasso.with(getContext()).load(movie.getLandscapePath()).placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.img);
        }else{
            Picasso.with(getContext()).load(movie.getPosterPath()).placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.img);
        }
        //ivPoster.set
        // Return the completed view to render on screen
        return convertView;
    }

    //    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
//        super(context, R.layout.item_movie2, movies);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Get the data item for this position
//        Movie movie = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie2, parent, false);
//        }
//        // Lookup view for data population
//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
//        // Populate the data into the template view using the data object
//        tvTitle.setText(movie.title);
//
//        Log.d("MoviesAdapter", "Position: " + position);
//
//        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
//        Picasso.with(getContext()).load(imageUri).into(ivPoster);
//
//        //ivPoster.set
//        // Return the completed view to render on screen
//        return convertView;
//    }
}
