package com.example.josh.movie_db_ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{


boolean isFirstItemSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mainActivitySpinner, android.R.layout.simple_spinner_item);


        final EditText textView = findViewById(R.id.textView);
        textView.setOnKeyListener (new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                String searchVar = "http://10.0.2.2:8080/omdb/"+textView.getText();



                //ResponseEntity<String> resp = restTemplate.getForEntity("http://www.omdbapi.com?s=".concat(title).concat("&apikey=eb2d8531"),String.class);

                if (keyCode == 66)
                {
                    Intent intent = new Intent(getBaseContext(), SearchResults.class);
                    intent.putExtra("searchVarExtra", searchVar);
                    startActivity(intent);
                }
                return true;
            }
        });


        Button searchButton = (findViewById(R.id.btnSearch));
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String searchVar = "http://10.0.2.2:8080/omdb/"+textView.getText();
                Intent intent = new Intent(getBaseContext(), SearchResults.class);
                intent.putExtra("searchVarExtra", searchVar);
                startActivity(intent);
            }
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch(i)
                {
                  case 0:
                  {
                      intent = new Intent(getBaseContext(), My_Movie_Collection.class);
                      startActivity(intent);
                      break;
                  }
                  case 1:
                  {
                      intent = new Intent(getBaseContext(), My_Wish_List.class);
                      startActivity(intent);
                      break;
                  }
                  default:
                  {

                  }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
