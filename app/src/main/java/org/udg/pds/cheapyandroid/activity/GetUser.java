package org.udg.pds.cheapyandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUser extends Activity {

    CheapyApi mCheapyService;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prova_getuser_info);

        final TextView textView = (TextView) findViewById(R.id.informacio);
        final TextView textView2 = (TextView) findViewById(R.id.informacio2);


        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    mCheapyService = retrofit.create(CheapyApi.class);
    Call<User> call = mCheapyService.getSpecificUser();

    call.enqueue(new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            User usuari = response.body();
            Log.d("id", usuari.getId());
            Log.d("nom", usuari.getNom());
            Log.d("cognoms", usuari.getCognoms());

            String usuariNom = usuari.getNom();
            String usuariCognoms = usuari.getCognoms();

            textView.append(usuariNom);
            textView2.append(usuariCognoms);
        }


        @Override
        public void onFailure(Call<User> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }
}
