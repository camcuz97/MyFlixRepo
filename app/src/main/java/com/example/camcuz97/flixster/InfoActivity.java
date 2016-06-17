package com.example.camcuz97.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class InfoActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView tvTitle;
    TextView tvDescription;
    TextView tvRate;
    TextView tvDate;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvOverview);
        tvRate = (TextView) findViewById(R.id.tvRate);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvDate = (TextView) findViewById(R.id.tvDate);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        double rating = getIntent().getDoubleExtra("rating",0);
        int numVotes = getIntent().getIntExtra("votes",0);
        String date = getIntent().getStringExtra("date");
        String back = getIntent().getStringExtra("backdrop");
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvRate.setText("" + rating/2 + "/5");
        tvDate.setText("Release Date: " + date);
        ratingBar.setRating((float)rating/2);
        Picasso.with(this.getApplicationContext()).load(back).placeholder(R.drawable.datboi).error(R.drawable.error).transform(new RoundedCornersTransformation(10, 10)).into(ivBack);

    }
}
