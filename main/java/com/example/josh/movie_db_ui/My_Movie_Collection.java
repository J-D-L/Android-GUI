package com.example.josh.movie_db_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class My_Movie_Collection extends AppCompatActivity {

    ListView listView;
    HttpRequestTaskMyMovies httpRequestTaskMyMovies;
    HttpRequestTaskMovie hrtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__movie__collection);
        listView=(ListView) findViewById(R.id.listViewMyCollection);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(getBaseContext(), MovieView.class);
                intent.putExtra("movie", getMovie(httpRequestTaskMyMovies.resp[i].getImdbID()));
                intent.putExtra("movieArray", httpRequestTaskMyMovies.resp);
                startActivity(intent);
            }
        });
    }

    private Movie getMovie(String imdbID) {
        hrtm = new HttpRequestTaskMovie();
        hrtm.searchVar = "http://10.0.2.2:8080/omdb/movie/" + imdbID;
        hrtm.execute();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return hrtm.resp;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        httpRequestTaskMyMovies = new HttpRequestTaskMyMovies();
        httpRequestTaskMyMovies.execute();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] values = new String[httpRequestTaskMyMovies.resp.length];

        for (int i = 0; i < httpRequestTaskMyMovies.resp.length; i++)
        {
            values[i] = (httpRequestTaskMyMovies.resp[i].getTitle() + " " + httpRequestTaskMyMovies.resp[i].getYear());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
    }
}
