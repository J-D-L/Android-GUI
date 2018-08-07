package com.example.josh.movie_db_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity

{
    AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        animation1.setDuration(4000);
        animation1.setRepeatCount(1);
        animation1.setRepeatMode(Animation.REVERSE);
        animation1.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                iv.setAlpha(0f);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        iv = findViewById(R.id.imageViewSplash);

        iv.setAlpha(1f);
        iv.startAnimation(animation1);



    }


}

