package org.udg.pds.cheapyandroid.activity;

import android.content.*;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.MyFirebaseInstanceIDService;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.udg.pds.cheapyandroid.MyFirebaseInstanceIDService.TOKEN_RECEIVER;
import static org.udg.pds.cheapyandroid.activity.LlistaProductesActivity.PREFS_NAME;

public class SplashScreen extends AppCompatActivity {
    private ImageView imagesplash;
    private TextView textsplash;
    private CheapyApi mCheapyService;
    private boolean isLogged;

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
                    isLogged = response.body();
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
        if (isLogged){
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String userName_connected = prefs.getString("usuari_nom", "usuari_prova");
            int userID_connected = prefs.getInt("usuari_id", -1);
            String userCorreu_connected = prefs.getString("usuari_correo", "prova@mail.com");
            Login.alreadyConnected(userID_connected, userName_connected, userCorreu_connected);
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
