package com.example.josh.movie_db_ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MovieView extends AppCompatActivity {
    Movie movie;
    Movie[] movieArray;
    TextView title;
    TextView year;
    TextView runtime;
    TextView genre;
    TextView rating;
    TextView released;
    ImageView poster;
    URL url;
    Bitmap bitmap;
    TextView plot;
    TextView director;
    TextView writers;
    TextView actors;
    TextView link;
    String searchVarMovie = "http://localhost:8080/omdb/movie/";
    String searchVar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
       movie = (Movie) intent.getSerializableExtra("movie");
       movieArray = (Movie[]) intent.getSerializableExtra("movieArray");
       searchVar = (String)extras.get("searchVar");
       title = findViewById(R.id.txtMovieTitle);
       title.setText(movie.getTitle());
       year = findViewById(R.id.txtMovieYear);
       year.setText(movie.getYear());
       runtime = findViewById(R.id.txtRuntime);
       runtime.setText(movie.getRuntime());
       genre = findViewById(R.id.txtGenre);
       genre.setText(movie.getGenre());
       rating = findViewById(R.id.txtRated);
       rating.setText(movie.getRated());
       poster = findViewById(R.id.imageViewPoster);
        new DownLoadImageTask(poster).execute(movie.getPoster());
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Poster.class);
                intent.putExtra("movie", movie);
                intent.putExtra("movieArray", movieArray);
                intent.putExtra("searchVar", searchVar);
                startActivity(intent);
            }
        });
       plot = findViewById(R.id.txtPlot);
       plot.setText(movie.getPlot());
       director = findViewById(R.id.txtDirector);
       director.setText(movie.getDirector());
       writers = findViewById(R.id.txtWriters);
       writers.setText(movie.getWriter());
       actors = findViewById(R.id.txtActors);
       actors.setText(movie.getActors());
       link = findViewById(R.id.txtMovieLink);
       link.setText(movie.getWebSite());

    }



    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);
    }


}
