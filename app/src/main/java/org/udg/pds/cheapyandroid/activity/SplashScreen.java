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

    BroadcastReceiver tokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String token = intent.getStringExtra("token");
            enviarToken(token);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((tokenReceiver),
                new IntentFilter(MyFirebaseInstanceIDService.TOKEN_RECEIVER));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(tokenReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        LocalBroadcastManager.getInstance(this).registerReceiver(tokenReceiver,
                new IntentFilter(TOKEN_RECEIVER));

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
            MyFirebaseInstanceIDService myFireBaseInsID = new MyFirebaseInstanceIDService();
            myFireBaseInsID.onTokenRefresh();
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String userName_connected = prefs.getString("usuari_nom", "usuari_prova");
            long userID_connected = prefs.getLong("usuari_id", -1);
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

    private void enviarToken(String token) {

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        Call<Void> call = mCheapyService.sendToken(token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){
                    Toast toast = Toast.makeText(SplashScreen.this, "Token enviat OK", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(SplashScreen.this, "Token enviat ERROR", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast toast = Toast.makeText(SplashScreen.this, "Error, revisa la connexió a internet", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
