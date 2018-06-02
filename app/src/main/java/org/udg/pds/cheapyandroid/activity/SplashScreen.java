package org.udg.pds.cheapyandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    private ImageView imagesplash;
    private TextView textsplash;
    private CheapyApi mCheapyService;
    private boolean logged;
    private objecteAmb_ID_NOM_CORREU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        textsplash = (TextView) findViewById(R.id.textSplashScreen);
        imagesplash = (ImageView) findViewById(R.id.logoSplash);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.change_animation);
        imagesplash.startAnimation(anim);
        textsplash.startAnimation(anim);
        Call<Boolean> call = mCheapyService.checkAuth();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    objecteAmb_ID_NOM_CORREU = response.body();
                    logged = (objecteAmb_ID_NOM_CORREU!=null);
                    nextWindows();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast toast = Toast.makeText(SplashScreen.this, "ERROR: S'ha rebut una resposta erronia.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void nextWindows(){
        final Intent i;
        if (logged){
           Login.alreadyConnected(ID, NAME, CORREU);
            i = new Intent(this, LlistaProductesActivity.class);
        }
        else i = new Intent(this, Login.class);
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();
    }
}
