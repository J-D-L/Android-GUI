package com.example.josh.movie_db_ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.example.josh.movie_db_ui.R.layout.activity_search__results;

public class SearchResults extends Activity
{
    ListView listView;
    String searchVar;
    HttpRequestTask hrt;
    HttpRequestTaskMovie hrtm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(activity_search__results);
        listView=(ListView) findViewById(R.id.listView2);
        Bundle extras = getIntent().getExtras();
        searchVar = extras.getString("searchVarExtra");
        hrt = new HttpRequestTask();
        hrtm = new HttpRequestTaskMovie();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
           {
               Intent intent = new Intent(getBaseContext(), MovieView.class);
               intent.putExtra("movie", getMovie(hrt.resp[i].getImdbID()));
               intent.putExtra("movieArray", hrt.resp);
               intent.putExtra("searchVarExtra", searchVar);
               startActivity(intent);
           }
       });
    }

    private Movie getMovie(String imdbID) {
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
        hrt.searchVar = this.searchVar;
        hrt.execute();

        //new HttpRequestTask().execute();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] values = new String[hrt.resp.length];

        for (int i = 0; i < hrt.resp.length; i++)
        {
            values[i] = (hrt.resp[i].getTitle() + " " + hrt.resp[i].getYear());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
    }
}



class HttpRequestTask extends AsyncTask<Void, Void, Movie>
{


    public Movie[] resp;

    public String searchVar;
    @Override
    protected Movie doInBackground(Void... voids)
    {

        try
        {

            //final String searchVarMovie = "http://10.0.2.2:8080/omdb/";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//            List<Movie> movieList = restTemplate.getForObject(searchVarMovie, List<Movie>.class);
            resp = restTemplate.getForObject(searchVar,Movie[].class);

            return null;
        }
        catch (Exception e)
        {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    protected void onPostExecute(Movie movie)
    {

    }
}

class HttpRequestTaskMovie extends AsyncTask<Void, Void, Movie>
{


    public Movie resp;

    public String searchVar;
    @Override
    protected Movie doInBackground(Void... voids)
    {

        try
        {

            //final String searchVarMovie = "http://10.0.2.2:8080/omdb/";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//            List<Movie> movieList = restTemplate.getForObject(searchVarMovie, List<Movie>.class);
            resp = restTemplate.getForObject(searchVar,Movie.class);

            return null;
        }
        catch (Exception e)
        {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    protected void onPostExecute(Movie movie)
    {

    }
}

class HttpRequestTaskMyMovies extends AsyncTask<Void, Void, Movie>
{
    public Movie[] resp;
    public String searchVar = "http://10.0.2.2:8080/movie";
    @Override
    protected Movie doInBackground(Void... voids)
    {

        try
        {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            resp = restTemplate.getForObject(searchVar,Movie[].class);

            return null;
        }
        catch (Exception e)
        {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    protected void onPostExecute(Movie movie)
    {

    }
}
