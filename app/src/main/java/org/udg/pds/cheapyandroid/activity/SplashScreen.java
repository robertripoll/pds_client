package org.udg.pds.cheapyandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import org.udg.pds.cheapyandroid.R;

public class SplashScreen extends AppCompatActivity {
    private ImageView imagesplash;
    private TextView textsplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        textsplash = (TextView) findViewById(R.id.textSplashScreen);
        imagesplash = (ImageView) findViewById(R.id.logoSplash);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.change_animation);
        imagesplash.startAnimation(anim);
        textsplash.startAnimation(anim);
        final Intent i = new Intent(this, LlistaProductesActivity.class);
        Thread t= new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();
    }
}
