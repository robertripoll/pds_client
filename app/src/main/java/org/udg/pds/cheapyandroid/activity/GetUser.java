package org.udg.pds.cheapyandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

        final TextView textView = (TextView) findViewById(R.id.nom);
        final TextView textView2 = (TextView) findViewById(R.id.cognom);
        final TextView textView3 = (TextView) findViewById(R.id.dataNaixement);
        final TextView textView4 = (TextView) findViewById(R.id.correu);
        final TextView textView5 = (TextView) findViewById(R.id.vendes);
        final TextView textView6 = (TextView) findViewById(R.id.compres);
        final TextView textView7 = (TextView) findViewById(R.id.valoracions);
        final TextView textView8 = (TextView) findViewById(R.id.mitjanaValoracions);


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
            Log.d("dataNaixement", usuari.getDataNaixement());
            Log.d("correu", usuari.getCorreu());
            Log.d("nombreVendes", String.valueOf(usuari.getNombreVendes()));
            Log.d("nombreCompres", String.valueOf(usuari.getNombreCompres()));
            Log.d("nombreValoracions", String.valueOf(usuari.getValoracions()));
            Log.d("mitjanaValoracions", String.valueOf(usuari.getMitjanaValoracions()));

            String usuariNom = usuari.getNom();
            String usuariCognoms = usuari.getCognoms();
            String usuariData = usuari.getDataNaixement();
            String usuariCorreu = usuari.getCorreu();
            String usuariVendes = String.valueOf(usuari.getNombreVendes());
            String usuariCompres = String.valueOf(usuari.getNombreCompres());
            String usuariNumValoracions = String.valueOf(usuari.getValoracions());
            String usuariValoracions = String.valueOf(usuari.getMitjanaValoracions());

            textView.append(usuariNom);
            textView2.append(usuariCognoms);
            textView3.append(usuariData);
            textView4.append(usuariCorreu);
            textView5.append(usuariVendes);
            textView6.append(usuariCompres);
            textView7.append(usuariNumValoracions);
            textView8.append(usuariValoracions);

        }


        @Override
        public void onFailure(Call<User> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }
}
