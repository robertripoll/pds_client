package org.udg.pds.cheapyandroid.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import org.w3c.dom.Text;
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



        final TextView textView9 = (TextView) findViewById(R.id.latitudCoordenades);
        final TextView textView10 = (TextView) findViewById(R.id.ciutatUbicacio);
        final TextView textView11 = (TextView) findViewById(R.id.paisUbicacio);
        final TextView textView12 = (TextView) findViewById(R.id.longitudCoordenades);
        final ImageView imageView = (ImageView) findViewById(R.id.foto);


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

            String longitud = String.valueOf(usuari.getUbicacio().getCoordenades().getLongitud());
            String latitud = String.valueOf(usuari.getUbicacio().getCoordenades().getLatitud());
            String mitjana = String.valueOf(usuari.getMitjanaValoracions());
            String compres = String.valueOf(usuari.getNombreCompres());
            String vendes = String.valueOf(usuari.getNombreVendes());
            String valoracions = String.valueOf(usuari.getNombreValoracions());

            textView.append(usuari.getNom());
            textView2.append(usuari.getCognoms());
            textView3.append(usuari.getDataNaixement());
            textView4.append(usuari.getCorreu());
            textView5.append(vendes);
            textView6.append(compres);
            textView7.append(valoracions);
            textView8.append(mitjana);
            textView9.append(latitud);
            textView12.append(longitud);
            textView10.append(usuari.getUbicacio().getCiutat());
            textView11.append(usuari.getUbicacio().getPais());
            imageView.setImageURI(Uri.parse(usuari.getImatgeURL()));
        }


        @Override
        public void onFailure(Call<User> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }
}
