package com.example.josh.movie_db_ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Josh on 04/27/18.
 */

public class DownLoadImageTask extends AsyncTask<String, Void, Bitmap>
{
    private final ImageView bmImage;

    public DownLoadImageTask(ImageView bmImage)
{
    this.bmImage = bmImage;
}
    @Override
    protected Bitmap doInBackground(String... urls)
    {
        String urlDisplay = urls[0];
        Bitmap mImage = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            mImage = BitmapFactory.decodeStream(in);
        } catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mImage;
    }

    protected void onPostExecute(Bitmap result)
    {
        bmImage.setImageBitmap(result);
    }
}
