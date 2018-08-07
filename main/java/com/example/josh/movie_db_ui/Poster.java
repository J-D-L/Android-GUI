package com.example.josh.movie_db_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Poster extends AppCompatActivity {
    Movie movie;
    Movie[] movieArray;
    String searchVar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        ImageView largePoster = findViewById(R.id.imageViewPosterLarge);
        Bundle extras = getIntent().getExtras();
        movie = (Movie)extras.get("movie");
        movieArray = (Movie[])extras.get("movieArray");
        searchVar = (String)extras.get("searchVar");

        new DownLoadImageTask(largePoster).execute(movie.getPoster());
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(), MovieView.class);
        intent.putExtra("movie", movie);
        intent.putExtra("movieArray", movieArray);
        intent.putExtra("searchVarExtra", searchVar);

        startActivity(intent);
    }
}
